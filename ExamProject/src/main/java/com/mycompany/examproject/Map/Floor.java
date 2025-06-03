/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.util.List;

/**
 *
 * @author Arseniy
 */
public class Floor {
    private int floorNumber; // Floor index (1 to 10)
    private int width, height; // Dimensions of the floor
    private Room[][] rooms; // 2D grid of rooms
    private Room staircaseUpRoom; // Room with staircase leading up
    private Room staircaseDownRoom; // Room with staircase leading down
    private Room restRoom; // Room for resting
    private Room bossRoom; // Room with the boss
    private Room startRoom; // Starting room (only on floor 1)

    public Floor(int floorNumber, int width, int height) {
        this.floorNumber = floorNumber;
        this.width = width;
        this.height = height;
        this.rooms = new Room[width][height];
    }

    // Getters and setters
    public Room[][] getRooms() { return rooms; }
    public Room getStaircaseUpRoom() { return staircaseUpRoom; }
    public Room getStaircaseDownRoom() { return staircaseDownRoom; }
    public Room getRestRoom() { return restRoom; }
    public Room getBossRoom() { return bossRoom; }
    public Room getStartRoom() { return startRoom; }
    public void setRoom(int x, int y, Room room) { rooms[x][y] = room; }
    public void setStaircaseUpRoom(Room room) { this.staircaseUpRoom = room; }
    public void setStaircaseDownRoom(Room room) { this.staircaseDownRoom = room; }
    public void setRestRoom(Room room) { this.restRoom = room; }
    public void setBossRoom(Room room) { this.bossRoom = room; }
    public void setStartRoom(Room room) { this.startRoom = room; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFloorNumber() { return floorNumber; }

    // Reset visited status of all rooms for maze generation
    public void resetVisited() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (rooms[x][y] != null) {
                    rooms[x][y].setVisited(false);
                }
            }
        }
    }
}