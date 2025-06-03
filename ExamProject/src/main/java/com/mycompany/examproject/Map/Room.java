/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Arseniy
 */
public class Room {
    private int x, y, floor; // Coordinates and floor number
    private RoomType type;   // Type of the room
    private List<Room> connections; // Adjacent rooms (for navigation)
    private String description; // Textual description of the room
    private boolean visited; // Tracks if the room has been visited
    private String roomPictureSource;

    public Room(int x, int y, int floor, RoomType type, String description, String roomPictureSource) {
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.type = type;
        this.description = description;
        this.connections = new ArrayList<>();
        this.visited = false;
        this.roomPictureSource = roomPictureSource;
    }

    // Getters and setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getFloor() { return floor; }
    public RoomType getType() { return type; }
    public List<Room> getConnections() { return connections; }
    public String getDescription() { return description; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
    public void addConnection(Room room) { connections.add(room); }
    
    public String getRoomPictureSource(){
        return this.roomPictureSource;
    }
}