/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Arseniy
 */
public class CastleMapGenerator {
    private static final int TOTAL_FLOORS = 10;
    private List<Floor> floors;
    private Random random;

    public CastleMapGenerator() {
        this.floors = new ArrayList<>();
        this.random = new Random();
    }

    // Generates the entire castle map and prints each floor
    public List<Floor> generateMap() {
        for (int floorNum = 1; floorNum <= TOTAL_FLOORS; floorNum++) {
            Floor floor = createFloor(floorNum);
            floors.add(floor);
            printFloor(floor); // Print the ASCII map for this floor
        }
        connectFloors();
        return floors;
    }

    // Creates a single floor based on its number
    private Floor createFloor(int floorNumber) {
        int width, height;
        if (floorNumber <= 3) {
            width = 5;
            height = 7;
        } else if (floorNumber <= 5) {
            width = 5;
            height = 5;
        } else if (floorNumber <= 9) {
            width = 4;
            height = 4;
        } else { // Floor 10
            width = 3;
            height = 1; // Linear layout for 3 rooms
        }

        Floor floor = new Floor(floorNumber, width, height);
        initializeRooms(floor);
        placeSpecialRooms(floor);
        connectRooms(floor);
        return floor;
    }

    // Initializes all rooms as NORMAL with descriptions
    private void initializeRooms(Floor floor) {
        for (int x = 0; x < floor.getWidth(); x++) {
            for (int y = 0; y < floor.getHeight(); y++) {
                String description = generateRoomDescription(floor.getFloorNumber(), x, y);
                floor.setRoom(x, y, new Room(x, y, floor.getFloorNumber(), RoomType.NORMAL, description, roomPictureSource));
            }
        }
    }

    // Places staircase (up/down), rest, boss, and start rooms
    private void placeSpecialRooms(Floor floor) {
        int width = floor.getWidth();
        int height = floor.getHeight();
        int floorNum = floor.getFloorNumber();

        if (floorNum == TOTAL_FLOORS) {
            // Floor 10: Exactly 3 rooms (staircase down, rest, boss)
            floor.setRoom(0, 0, new Room(0, 0, floorNum, RoomType.STAIRCASE_DOWN, "A staircase leading down to the previous floor."));
            floor.setRoom(1, 0, new Room(1, 0, floorNum, RoomType.REST, "A serene chamber offering a moment of respite."));
            floor.setRoom(2, 0, new Room(2, 0, floorNum, RoomType.BOSS, "A foreboding hall where the final boss awaits."));
            floor.setStaircaseDownRoom(floor.getRooms()[0][0]);
            floor.setRestRoom(floor.getRooms()[1][0]);
            floor.setBossRoom(floor.getRooms()[2][0]);
        } else {
            // Place rest room
            int restX = random.nextInt(width);
            int restY = random.nextInt(height);
            floor.setRoom(restX, restY, new Room(restX, restY, floorNum, RoomType.REST, "A quiet room with a restorative aura."));
            floor.setRestRoom(floor.getRooms()[restX][restY]);

            // Place boss room
            int bossX = random.nextInt(width);
            int bossY = random.nextInt(height);
            while (bossX == restX && bossY == restY) {
                bossX = random.nextInt(width);
                bossY = random.nextInt(height);
            }
            floor.setRoom(bossX, bossY, new Room(bossX, bossY, floorNum, RoomType.BOSS, "A dark chamber where a powerful foe lurks."));
            floor.setBossRoom(floor.getRooms()[bossX][bossY]);

            // Place staircase up room adjacent to boss room (except on last floor)
            if (floorNum < TOTAL_FLOORS) {
                int[] staircaseUpPos = getAdjacentPosition(floor, bossX, bossY);
                if (staircaseUpPos != null) {
                    floor.setRoom(staircaseUpPos[0], staircaseUpPos[1], new Room(staircaseUpPos[0], staircaseUpPos[1], floorNum, RoomType.STAIRCASE_UP, "A staircase leading to the next floor."));
                    floor.setStaircaseUpRoom(floor.getRooms()[staircaseUpPos[0]][staircaseUpPos[1]]);
                }
            }

            // Place staircase down room (except on floor 1)
            if (floorNum > 1) {
                int downX = random.nextInt(width);
                int downY = random.nextInt(height);
                while ((downX == restX && downY == restY) || (downX == bossX && downY == bossY) || 
                       (floor.getStaircaseUpRoom() != null && downX == floor.getStaircaseUpRoom().getX() && downY == floor.getStaircaseUpRoom().getY())) {
                    downX = random.nextInt(width);
                    downY = random.nextInt(height);
                }
                floor.setRoom(downX, downY, new Room(downX, downY, floorNum, RoomType.STAIRCASE_DOWN, "A staircase leading down to the previous floor."));
                floor.setStaircaseDownRoom(floor.getRooms()[downX][downY]);
            }

            // Place start room on floor 1
            if (floorNum == 1) {
                int startX = random.nextInt(width);
                int startY = random.nextInt(height);
                while ((startX == restX && startY == restY) || (startX == bossX && startY == bossY) || 
                       (floor.getStaircaseUpRoom() != null && startX == floor.getStaircaseUpRoom().getX() && startY == floor.getStaircaseUpRoom().getY())) {
                    startX = random.nextInt(width);
                    startY = random.nextInt(height);
                }
                floor.setRoom(startX, startY, new Room(startX, startY, floorNum, RoomType.START, "The entrance to the cursed castle."));
                floor.setStartRoom(floor.getRooms()[startX][startY]);
            }
        }
    }

    // Gets a random adjacent position to a given room
    private int[] getAdjacentPosition(Floor floor, int x, int y) {
        List<int[]> possiblePositions = new ArrayList<>();
        if (x > 0) possiblePositions.add(new int[]{x - 1, y});
        if (x < floor.getWidth() - 1) possiblePositions.add(new int[]{x + 1, y});
        if (y > 0) possiblePositions.add(new int[]{x, y - 1});
        if (y < floor.getHeight() - 1) possiblePositions.add(new int[]{x, y + 1});
        if (possiblePositions.isEmpty()) return null;
        return possiblePositions.get(random.nextInt(possiblePositions.size()));
    }

    // Connects rooms to form a maze-like structure using DFS
    private void connectRooms(Floor floor) {
        int width = floor.getWidth();
        int height = floor.getHeight();
        Room[][] rooms = floor.getRooms();

        // Special case for floor 10 (linear structure)
        if (floor.getFloorNumber() == TOTAL_FLOORS) {
            rooms[0][0].addConnection(rooms[1][0]);
            rooms[1][0].addConnection(rooms[0][0]);
            rooms[1][0].addConnection(rooms[2][0]);
            rooms[2][0].addConnection(rooms[1][0]);
            return;
        }

        // Connect the staircase up room to the boss room explicitly
        Room staircaseUpRoom = floor.getStaircaseUpRoom();
        Room bossRoom = floor.getBossRoom();
        if (staircaseUpRoom != null && bossRoom != null) {
            staircaseUpRoom.addConnection(bossRoom);
            bossRoom.addConnection(staircaseUpRoom);
        }

        // Reset visited status for all rooms
        floor.resetVisited();
        // Mark the staircase up room as visited to exclude it from maze generation
        if (staircaseUpRoom != null) {
            staircaseUpRoom.setVisited(true);
        }

        // Start DFS from the start room (floor 1) or a random room (other floors)
        Room startRoom = (floor.getFloorNumber() == 1) ? floor.getStartRoom() : rooms[0][0];
        Stack<Room> stack = new Stack<>();
        stack.push(startRoom);
        startRoom.setVisited(true);

        // Directions for adjacent rooms: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // DFS to create a maze, excluding the staircase up room
        while (!stack.isEmpty()) {
            Room current = stack.peek();
            int x = current.getX();
            int y = current.getY();

            // Find unvisited neighbors, excluding the staircase up room unless it's the boss room connecting to it
            List<int[]> unvisitedNeighbors = new ArrayList<>();
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < width && newY >= 0 && newY < height && rooms[newX][newY] != null) {
                    Room neighbor = rooms[newX][newY];
                    // Include the neighbor if it's unvisited and not the staircase up room,
                    // or if it's the staircase up room and the current room is the boss room
                    if (!neighbor.isVisited() && (neighbor != staircaseUpRoom || current == bossRoom)) {
                        unvisitedNeighbors.add(new int[]{newX, newY});
                    }
                }
            }

            if (!unvisitedNeighbors.isEmpty()) {
                // Choose a random unvisited neighbor
                int[] nextPos = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                Room nextRoom = rooms[nextPos[0]][nextPos[1]];
                current.addConnection(nextRoom);
                nextRoom.addConnection(current);
                nextRoom.setVisited(true);
                stack.push(nextRoom);
            } else {
                stack.pop();
            }
        }

        // Add a few extra connections to make the maze less linear (optional, for variety)
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Room room = rooms[x][y];
                if (room == null || room == staircaseUpRoom) continue; // Skip staircase up room
                // Small chance to add an extra connection
                if (random.nextDouble() < 0.1) { // 10% chance
                    List<Room> possibleConnections = new ArrayList<>();
                    for (int[] dir : directions) {
                        int newX = x + dir[0];
                        int newY = y + dir[1];
                        if (newX >= 0 && newX < width && newY >= 0 && newY < height && rooms[newX][newY] != null) {
                            Room neighbor = rooms[newX][newY];
                            if (neighbor != staircaseUpRoom) { // Exclude staircase up room
                                possibleConnections.add(neighbor);
                            }
                        }
                    }
                    if (!possibleConnections.isEmpty()) {
                        Room extraConnection = possibleConnections.get(random.nextInt(possibleConnections.size()));
                        if (!room.getConnections().contains(extraConnection)) {
                            room.addConnection(extraConnection);
                            extraConnection.addConnection(room);
                        }
                    }
                }
            }
        }
    }

    // Connects floors via staircases (up to next floor, down to previous floor)
    private void connectFloors() {
        for (int i = 0; i < floors.size(); i++) {
            Floor currentFloor = floors.get(i);
            // Connect to the next floor (if not the last floor)
            if (i < floors.size() - 1) {
                Floor nextFloor = floors.get(i + 1);
                Room currentStaircaseUp = currentFloor.getStaircaseUpRoom();
                Room nextStaircaseDown = nextFloor.getStaircaseDownRoom();
                if (currentStaircaseUp != null && nextStaircaseDown != null) {
                    currentStaircaseUp.addConnection(nextStaircaseDown);
                    nextStaircaseDown.addConnection(currentStaircaseUp);
                }
            }
            // Connect to the previous floor (if not the first floor)
            if (i > 0) {
                Floor prevFloor = floors.get(i - 1);
                Room currentStaircaseDown = currentFloor.getStaircaseDownRoom();
                Room prevStaircaseUp = prevFloor.getStaircaseUpRoom();
                if (currentStaircaseDown != null && prevStaircaseUp != null) {
                    currentStaircaseDown.addConnection(prevStaircaseUp);
                    prevStaircaseUp.addConnection(currentStaircaseDown);
                }
            }
        }
    }

    // Generates a description for a normal room
    private String generateRoomDescription(int floor, int x, int y) {
        String[] descriptions = {
            "Ты входишь в пространство забытое временем — каменные своды дрожат под напором тишины. " +
            "Пыль веков оседает на ржавых доспехах, что стоят на страже пустоты. " +
            "Кажется, что-то наблюдает из теней...",

            "Здесь воздух густой от холода и страха. Каменные стены покрыты трещинами, " +
            "словно сам замок кричит от боли. Где-то вдалеке капает вода — но это не вода.",

            "Сводчатый зал, где даже пламя факелов боится гореть ярко. Тени ползут по стенам, " +
            "как будто они живые. Воздух пропитан запахом гнили и старого железа.",

            "Длинный коридор без конца и начала. Стены украшены треснувшими барельефами древних богов, " +
            "их лица искажены скорбью. Пол усыпан осколками чего-то… или кого-то.",

            "Тронный зал, теперь лишь развалины величия. Чёрные балки обрушены, " +
            "а на полу остались следы когтей. Их ведёт к трону, где давным-давно ничего не сидит.",

            "Пыльное хранилище знаний, что забыло свои секреты. Папирусы рассыпаются в прах, " +
            "а книги шепчут слова на языке, который никто не помнит. Но ты всё равно слышишь их.",

            "Подземелье, где эхо отзывается слишком быстро. Стены покрыты цепями, " +
            "все ржавые, кроме одной — она блестит так, словно ею пользовались совсем недавно.",

            "Чердак, где крыша обрушилась под тяжестью времени. Лучи лунного света пробиваются сквозь щели, " +
            "освещая то, что лучше бы оставалось во тьме.",

            "Храм, забытый даже богами. Алтарь разбит, статуи повалены. Однако в центре зала всё ещё теплится огонь — " +
            "неестественно синий и без дыма.",

            "Большая зала, когда-то полная жизни. Теперь здесь только призраки смеха, " +
            "что эхом отдаются в ушах. На стенах висят портреты, глаза которых следят за каждым шагом.",

            "Подвал, наполненный запахом сырости и разложения. Бочки валяются в беспорядке, " +
            "а в углу кто-то явно пытался вырыть выход. Он вёл в никуда.",

            "Галерея, где каждый шаг отзванивается множеством голосов. Зеркала покрыты паутиной, " +
            "а в некоторых отражается не совсем ты."
        };
        return descriptions[random.nextInt(descriptions.length)];
    }

    // Prints an ASCII representation of the floor
    private void printFloor(Floor floor) {
        System.out.println("\n=== Floor " + floor.getFloorNumber() + " ===");
        int width = floor.getWidth();
        int height = floor.getHeight();
        Room[][] rooms = floor.getRooms();

        // Print room grid
        for (int y = 0; y < height; y++) {
            // Print room row
            for (int x = 0; x < width; x++) {
                Room room = rooms[x][y];
                if (room == null) {
                    System.out.print("[   ]");
                } else {
                    switch (room.getType()) {
                        case NORMAL:
                            System.out.print("[ N ]");
                            break;
                        case STAIRCASE_UP:
                            System.out.print("[S+ ]");
                            break;
                        case STAIRCASE_DOWN:
                            System.out.print("[S- ]");
                            break;
                        case REST:
                            System.out.print("[ R ]");
                            break;
                        case START:
                            System.out.print("[ T ]");
                            break;
                        case BOSS:
                            System.out.print("[ B ]");
                            break;
                    }
                }
                // Print horizontal connections
                if (x < width - 1 && room != null && rooms[x + 1][y] != null && room.getConnections().contains(rooms[x + 1][y])) {
                    System.out.print("---");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
            // Print vertical connections
            if (y < height - 1) {
                for (int x = 0; x < width; x++) {
                    Room room = rooms[x][y];
                    if (room != null && rooms[x][y + 1] != null && room.getConnections().contains(rooms[x][y + 1])) {
                        System.out.print("  |  ");
                    } else {
                        System.out.print("     ");
                    }
                    if (x < width - 1) {
                        System.out.print("   ");
                    }
                }
                System.out.println();
            }
        }

        // Print legend
        System.out.println("\nLegend:");
        System.out.println("[ N ]: Normal Room");
        System.out.println("[S+ ]: Staircase Up Room");
        System.out.println("[S- ]: Staircase Down Room");
        System.out.println("[ R ]: Rest Room");
        System.out.println("[ T ]: Start Room");
        System.out.println("[ B ]: Boss Room");
        System.out.println("---: Horizontal Connection");
        System.out.println("|  : Vertical Connection");
        System.out.println();
    }

    // Getter for floors
    public List<Floor> getFloors() {
        return floors;
    }

    public Room getStartRoom() {
        if (floors.isEmpty()) {
            throw new IllegalStateException("Map has not been generated yet! Call generateMap() first.");
        }
        Floor firstFloor = floors.get(0); // Floor 1 is at index 0
        Room startRoom = firstFloor.getStartRoom();
        if (startRoom == null) {
            throw new IllegalStateException("Start room not found on floor 1!");
        }
        return startRoom;
    }
    
    
}