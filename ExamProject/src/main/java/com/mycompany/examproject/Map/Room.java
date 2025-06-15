/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Arseniy
 */
public class Room implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int x, y, floor;
    private RoomType type;
    private List<Room> connections;
    private String description;
    private boolean visited;
    private String roomPictureSource;
    private boolean visitedByPlayer;

    private boolean wasExplored = false;
    
    public Room(int x, int y, int floor, RoomType type, String description, String roomPictureSource) {
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.type = type;
        this.description = description;
        this.connections = new ArrayList<>();
        this.visited = false;
        this.roomPictureSource = roomPictureSource;
        this.visitedByPlayer = false;
    }

    public void setExplored(boolean wasExplored){
        this.wasExplored = wasExplored;
    }
    
    public boolean wasExplored(){
        return this.wasExplored;
    }
    
    public boolean isVisitedByPlayer() { return visitedByPlayer; }
    public void setVisitedByPlayer(boolean visitedByPlayer) { this.visitedByPlayer = visitedByPlayer; }
    
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