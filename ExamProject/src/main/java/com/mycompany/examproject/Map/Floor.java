/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

import java.io.Serializable;
import java.util.List;

/**
 * Класс, представляющий этаж замка в RPG-игре.
 * <p>
 * Реализует интерфейс {@link Serializable} и хранит информацию об этаже,
 * включая его номер, размеры, комнаты и специальные комнаты, такие как лестницы,
 * комнаты отдыха, боссов и начальная комната.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class Floor implements Serializable{
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Номер этажа.
     */
    private int floorNumber;
    
    /**
     * Ширина этажа (количество комнат по горизонтали).
     */
    private int width;
    
    /**
     * Высота этажа (количество комнат по вертикали).
     */
    private int height;
    
    /**
     * Двумерный массив комнат этажа.
     */
    private Room[][] rooms;
    
    /**
     * Комната с лестницей наверх.
     */
    private Room staircaseUpRoom;
    
    /**
     * Комната с лестницей вниз.
     */
    private Room staircaseDownRoom;
    
    /**
     * Комната отдыха.
     */
    private Room restRoom;
    
    /**
     * Комната с боссом.
     */
    private Room bossRoom;
    
    /**
     * Начальная комната (входной зал).
     */
    private Room startRoom;

    /**
     * Конструктор для создания этажа.
     *
     * @param floorNumber номер этажа
     * @param width ширина этажа
     * @param height высота этажа
     */
    public Floor(int floorNumber, int width, int height) {
        this.floorNumber = floorNumber;
        this.width = width;
        this.height = height;
        this.rooms = new Room[width][height];
    }

    /**
     * Возвращает массив комнат этажа.
     *
     * @return двумерный массив комнат
     */
    public Room[][] getRooms() { return rooms; }
    
    /**
     * Возвращает комнату с лестницей наверх.
     *
     * @return комната с лестницей наверх
     */
    public Room getStaircaseUpRoom() { return staircaseUpRoom; }
    
    /**
     * Возвращает комнату с лестницей вниз.
     *
     * @return комната с лестницей вниз
     */
    public Room getStaircaseDownRoom() { return staircaseDownRoom; }
    
    /**
     * Возвращает комнату отдыха.
     *
     * @return комната отдыха
     */
    public Room getRestRoom() { return restRoom; }
    
    /**
     * Возвращает комнату с боссом.
     *
     * @return комната с боссом
     */
    public Room getBossRoom() { return bossRoom; }
    
    /**
     * Возвращает начальную комнату.
     *
     * @return начальная комната
     */
    public Room getStartRoom() { return startRoom; }
    
    /**
     * Устанавливает комнату в указанной позиции.
     *
     * @param x координата x
     * @param y координата y
     * @param room комната для установки
     */
    public void setRoom(int x, int y, Room room) { rooms[x][y] = room; }
    
    /**
     * Устанавливает комнату с лестницей наверх.
     *
     * @param room комната с лестницей наверх
     */
    public void setStaircaseUpRoom(Room room) { this.staircaseUpRoom = room; }
    
    /**
     * Устанавливает комнату с лестницей вниз.
     *
     * @param room комната с лестницей вниз
     */
    public void setStaircaseDownRoom(Room room) { this.staircaseDownRoom = room; }
    
    /**
     * Устанавливает комнату отдыха.
     *
     * @param room комната отдыха
     */
    public void setRestRoom(Room room) { this.restRoom = room; }
    
    /**
     * Устанавливает комнату с боссом.
     *
     * @param room комната с боссом
     */
    public void setBossRoom(Room room) { this.bossRoom = room; }
    
    /**
     * Устанавливает начальную комнату.
     *
     * @param room начальная комната
     */
    public void setStartRoom(Room room) { this.startRoom = room; }
    
    /**
     * Возвращает ширину этажа.
     *
     * @return ширина этажа
     */
    public int getWidth() { return width; }
    
    /**
     * Возвращает высоту этажа.
     *
     * @return высота этажа
     */
    public int getHeight() { return height; }
    
    /**
     * Возвращает номер этажа.
     *
     * @return номер этажа
     */
    public int getFloorNumber() { return floorNumber; }

    /**
     * Сбрасывает статус посещения всех комнат на этаже.
     * <p>
     * Устанавливает флаг посещения в false для всех комнат.
     */
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