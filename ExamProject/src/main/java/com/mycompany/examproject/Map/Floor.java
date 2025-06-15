/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Arseniy
 */
public class Floor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int floorNumber;
    private int width, height;
    
    private Room[][] rooms;
    private Room staircaseUpRoom;
    private Room staircaseDownRoom;
    private Room restRoom;
    private Room bossRoom;
    private Room startRoom;

    public Floor(int floorNumber, int width, int height) {
        this.floorNumber = floorNumber;
        this.width = width;
        this.height = height;
        this.rooms = new Room[width][height];
    }

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