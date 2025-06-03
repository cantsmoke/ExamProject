/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import com.mycompany.examproject.Player;
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
    private static List<Floor> floors;
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
                String descriptionAndURL = generateRoomDescription(floor.getFloorNumber(), x, y);
                String[] parts = descriptionAndURL.split("///");
                String roomDescription = parts[0];
                String roomPictureSource = parts[1];
                floor.setRoom(x, y, new Room(x, y, floor.getFloorNumber(), RoomType.NORMAL, roomDescription, roomPictureSource));
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
            floor.setRoom(0, 0, new Room(0, 0, floorNum, RoomType.STAIRCASE_DOWN, "A spiral of broken stone descends into the abyss below. The air rises thick with the scent of blood long dried. \n" +
                "Each step echoes like a heartbeat — a reminder that what lies beneath remembers your presence.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
            floor.setRoom(1, 0, new Room(1, 0, floorNum, RoomType.REST, "A forgotten sanctuary untouched by decay. A pale light filters through unseen cracks, warming the cold flagstones. \n" +
                "Here, the weight of the curse lifts — if only for a moment. But silence is never eternal in these halls.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
            floor.setRoom(2, 0, new Room(2, 0, floorNum, RoomType.BOSS, "A vast hall of blackened marble, where time itself seems to halt. The air hums with dread anticipation. \n" +
                "This is where the cycle must end — or begin anew. The very walls whisper of inevitable confrontation.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
            floor.setStaircaseDownRoom(floor.getRooms()[0][0]);
            floor.setRestRoom(floor.getRooms()[1][0]);
            floor.setBossRoom(floor.getRooms()[2][0]);
        } else {
            // Place rest room
            int restX = random.nextInt(width);
            int restY = random.nextInt(height);
            floor.setRoom(restX, restY, new Room(restX, restY, floorNum, RoomType.REST, "A chamber of stillness and ancient grace. Moss creeps gently over statues of forgotten saints, \n" +
                "and a faint glow lingers around the altar. It soothes not the body, but the soul — a rare mercy in this cursed place.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
            floor.setRestRoom(floor.getRooms()[restX][restY]);

            // Place boss room
            int bossX = random.nextInt(width);
            int bossY = random.nextInt(height);
            while (bossX == restX && bossY == restY) {
                bossX = random.nextInt(width);
                bossY = random.nextInt(height);
            }
            floor.setRoom(bossX, bossY, new Room(bossX, bossY, floorNum, RoomType.BOSS, "A void without stars, where even shadows fear to tread. Something waits here — something aware. \n" +
                "The air shivers with malice. Whatever sleeps in this darkness is no mere beast. It dreams of your death.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
            floor.setBossRoom(floor.getRooms()[bossX][bossY]);

            // Place staircase up room adjacent to boss room (except on last floor)
            if (floorNum < TOTAL_FLOORS) {
                int[] staircaseUpPos = getAdjacentPosition(floor, bossX, bossY);
                if (staircaseUpPos != null) {
                    floor.setRoom(staircaseUpPos[0], staircaseUpPos[1], new Room(staircaseUpPos[0], staircaseUpPos[1], floorNum, RoomType.STAIRCASE_UP, "A narrow ascent carved from jagged bone. The steps tremble underfoot, as if the castle itself resists your climb. \n" +
                        "Beyond lies more than just another floor — it is a deeper wound upon the world.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
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
                floor.setRoom(downX, downY, new Room(downX, downY, floorNum, RoomType.STAIRCASE_DOWN, "A spiral of broken stone descends into the abyss below. The air rises thick with the scent of blood long dried. \n" +
                    "Each step echoes like a heartbeat — a reminder that what lies beneath remembers your presence.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
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
                floor.setRoom(startX, startY, new Room(startX, startY, floorNum, RoomType.START, "A gate of rusted iron, forged in an age when men still believed they could keep horrors at bay. \n" +
                    "The stones weep, the wind howls names you almost remember. This is where the nightmare begins — again.", "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png"));
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
            "You enter a space forgotten by time — the stone vaults tremble under the pressure of silence. " +
            "The dust of centuries settles on the rusty armor that guards the void. " +
            "It seems that something is watching from the shadows...  ///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_11_01.png",

            "The air here is thick with cold and fear. The stone walls are covered with cracks, " +
            "It's like the castle itself is screaming in pain. ///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_34_41.png",

            "A vaulted hall where even the flame of torches is afraid to burn brightly. Shadows creep across the walls, " +
            "It's like they're alive. The air is saturated with the smell of rot and old iron. ///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_38_37.png" ,

            "A long corridor with no end and no beginning. The walls are decorated with cracked bas-reliefs of ancient gods, " +
            "their faces are distorted with grief. The floor is strewn with shards of something...///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_45_28.png",

            "The throne room is now just a ruin of grandeur. Black beams collapsed, " +
            "and there were claw marks on the floor. They lead to a throne where no one has sat for a long time.///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_46_14.png",

            "A dusty repository of knowledge, keeping its secrets. Papyri crumble to dust, " +
            "and books whisper words in a language that has long been forgotten...///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_51_31.png",

            "A dungeon where the echo reverberates too fast. The walls are covered with chains, " +
            "all are rusty, except for one — it glitters as if it had been used recently.///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_55_11.png",

            "An attic where the roof collapsed under the weight of time. Rays of moonlight break through the cracks, " +
            "illuminating what would have been better left in the dark.///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 10_57_06.png",

            "A temple forgotten even by the gods. The altar is smashed, the statues are toppled. However, there is still a fire burning in the center of the hall — " +
            "unnaturally blue and smoke-free.///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 11_12_35.png",

            "A large hall, once full of life. There are only ghosts here now, " +
            "flying in search of peace. There are portraits on the walls, their eyes following every step.///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 11_15_01.png",

            "The basement is filled with the smell of dampness and decay, barrels are lying in disarray." +
            "///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 11_20_01.png",

            "A gallery where every step is echoed by many voices. The mirrors are covered with cobwebs, " +
            "and the reflections in them don't look like you at all...///C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 3 июн. 2025 г., 11_21_50.png"
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
    public static List<Floor> getFloors() {
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
    
    public static Room getRoomToEast(int x, int y, int currentFloor) {
        Floor currentFloorObj = floors.get(currentFloor - 1);
        Room[][] rooms = currentFloorObj.getRooms();
        int newX = x + 1;
        int newY = y;
        if (newX < currentFloorObj.getWidth() && rooms[newX][newY] != null && Player.getInstance().getCurrentRoom().getConnections().contains(rooms[newX][newY])) {
            return rooms[newX][newY];
        }
        return null;
    }

    public static Room getRoomToWest(int x, int y, int currentFloor) {
        Floor currentFloorObj = floors.get(currentFloor - 1);
        Room[][] rooms = currentFloorObj.getRooms();
        int newX = x - 1;
        int newY = y;
        if (newX >= 0 && rooms[newX][newY] != null && Player.getInstance().getCurrentRoom().getConnections().contains(rooms[newX][newY])) {
            return rooms[newX][newY];
        }
        return null;
    }

    public static Room getRoomNorth(int x, int y, int currentFloor) {
        Floor currentFloorObj = floors.get(currentFloor - 1);
        Room[][] rooms = currentFloorObj.getRooms();
        int newX = x;
        int newY = y - 1;
        if (newY >= 0 && rooms[newX][newY] != null && Player.getInstance().getCurrentRoom().getConnections().contains(rooms[newX][newY])) {
            return rooms[newX][newY];
        }
        return null;
    }

    public static Room getRoomSouth(int x, int y, int currentFloor) {
        Floor currentFloorObj = floors.get(currentFloor - 1);
        Room[][] rooms = currentFloorObj.getRooms();
        int newX = x;
        int newY = y + 1;
        if (newY < currentFloorObj.getHeight() && rooms[newX][newY] != null && Player.getInstance().getCurrentRoom().getConnections().contains(rooms[newX][newY])) {
            return rooms[newX][newY];
        }
        return null;
    }
    
}