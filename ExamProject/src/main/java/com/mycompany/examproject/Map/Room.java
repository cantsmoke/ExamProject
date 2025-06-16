/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Класс, представляющий комнату на этаже замка в RPG-игре.
 * <p>
 * Реализует интерфейс {@link Serializable} и содержит информацию о комнате,
 * включая её координаты, тип, описание, связи с другими комнатами и статус
 * посещения.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class Room implements Serializable{
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Координата x комнаты.
     */
    private int x;
    
    /**
     * Координата y комнаты.
     */
    private int y;
    
    /**
     * Номер этажа, на котором находится комната.
     */
    private int floor;
    
    /**
     * Тип комнаты (например, обычная, лестница, босс).
     */
    private RoomType type;
    
    /**
     * Список комнат, соединённых с данной.
     */
    private List<Room> connections;
    
    /**
     * Описание комнаты.
     */
    private String description;
    
    /**
     * Флаг, указывающий, была ли комната посещена при генерации карты.
     */
    private boolean visited;
    
    /**
     * Путь к изображению комнаты.
     */
    private String roomPictureSource;
    
    /**
     * Флаг, указывающий, была ли комната посещена игроком.
     */
    private boolean visitedByPlayer;

    /**
     * Флаг, указывающий, была ли комната исследована.
     */
    private boolean wasExplored = false;
    
    /**
     * Конструктор для создания комнаты.
     *
     * @param x координата x
     * @param y координата y
     * @param floor номер этажа
     * @param type тип комнаты
     * @param description описание комнаты
     * @param roomPictureSource путь к изображению комнаты
     */
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

    /**
     * Устанавливает статус исследования комнаты.
     *
     * @param wasExplored true, если комната исследована, иначе false
     */
    public void setExplored(boolean wasExplored){
        this.wasExplored = wasExplored;
    }
    
    /**
     * Проверяет, была ли комната исследована.
     *
     * @return true, если комната исследована, иначе false
     */
    public boolean wasExplored(){
        return this.wasExplored;
    }
    
    /**
     * Проверяет, была ли комната посещена игроком.
     *
     * @return true, если комната посещена игроком, иначе false
     */
    public boolean isVisitedByPlayer() { return visitedByPlayer; }
    
    /**
     * Устанавливает статус посещения комнаты игроком.
     *
     * @param visitedByPlayer true, если комната посещена игроком, иначе false
     */
    public void setVisitedByPlayer(boolean visitedByPlayer) { this.visitedByPlayer = visitedByPlayer; }
    
    /**
     * Возвращает координату x комнаты.
     *
     * @return координата x
     */
    public int getX() { return x; }
    
    /**
     * Возвращает координату y комнаты.
     *
     * @return координата y
     */
    public int getY() { return y; }
    
    /**
     * Возвращает номер этажа комнаты.
     *
     * @return номер этажа
     */
    public int getFloor() { return floor; }
    
    /**
     * Возвращает тип комнаты.
     *
     * @return тип комнаты
     */
    public RoomType getType() { return type; }
    
    /**
     * Возвращает список соединённых комнат с конкретным экземляром комнаты.
     *
     * @return список соединённых комнат
     */
    public List<Room> getConnections() { return connections; }
    
    /**
     * Возвращает описание комнаты.
     *
     * @return описание комнаты
     */
    public String getDescription() { return description; }
    
    /**
     * Проверяет, была ли комната посещена при генерации карты.
     *
     * @return true, если комната посещена, иначе false
     */
    public boolean isVisited() { return visited; }

    /**
     * Устанавливает статус посещения комнаты при генерации карты.
     *
     * @param visited true, если комната посещена, иначе false
     */
    public void setVisited(boolean visited) { this.visited = visited; }
    
    /**
     * Добавляет соединение с другой комнатой.
     *
     * @param room комната для соединения
     */
    public void addConnection(Room room) { connections.add(room); }
    
    /**
     * Возвращает путь к изображению комнаты.
     *
     * @return путь к изображению
     */
    public String getRoomPictureSource(){
        return this.roomPictureSource;
    }
    
}