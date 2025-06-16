/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject.Map;

import com.mycompany.examproject.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

/**
 * Тесты для класса CastleMapGenerator.
 *
 * @author Arseniy
 */
public class CastleMapGeneratorTest {

    private CastleMapGenerator generator;
    private Random randomMock;
    private Player playerMock;
    private Floor floorMock;
    private Room roomMock;

    @BeforeEach
    public void setUp() {
        randomMock = mock(Random.class);
        playerMock = mock(Player.class);
        floorMock = mock(Floor.class);
        roomMock = mock(Room.class);

        generator = Mockito.spy(new CastleMapGenerator());
        try {
            java.lang.reflect.Field randomField = CastleMapGenerator.class.getDeclaredField("random");
            randomField.setAccessible(true);
            randomField.set(generator, randomMock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set Random mock", e);
        }

        CastleMapGenerator.setFloors(null);
    }

    @Test
    public void testConstructor() {
        CastleMapGenerator newGenerator = new CastleMapGenerator();
        assertNotNull(newGenerator.getFloorsInstance(), "Floors list should be initialized");
        assertTrue(newGenerator.getFloorsInstance().isEmpty(), "Floors list should be empty initially");
    }

    @Test
    public void testGetFloorsInstance() {
        List<Floor> floors = generator.getFloorsInstance();
        assertNotNull(floors, "Floors instance should not be null");
        assertTrue(floors.isEmpty(), "Floors instance should be empty initially");
    }

    @Test
    public void testSetAndGetFloors() {
        List<Floor> testFloors = new ArrayList<>();
        testFloors.add(floorMock);
        CastleMapGenerator.setFloors(testFloors);
        assertSame(testFloors, CastleMapGenerator.getFloors(), "Static floors should match the set value");
    }

    @Test
    public void testGetStartRoom_Success() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        try {
            java.lang.reflect.Field floorsField = CastleMapGenerator.class.getDeclaredField("floors");
            floorsField.setAccessible(true);
            floorsField.set(generator, floors);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set floors", e);
        }

        when(floorMock.getStartRoom()).thenReturn(roomMock);

        Room result = generator.getStartRoom();
        assertSame(roomMock, result, "Start room should be returned from the first floor");
    }

    @Test
    public void testGetStartRoom_EmptyFloors() {
        assertThrows(IllegalStateException.class, () -> generator.getStartRoom(),
                "Should throw IllegalStateException if floors list is empty");
    }

    @Test
    public void testGetStartRoom_NoStartRoom() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        try {
            java.lang.reflect.Field floorsField = CastleMapGenerator.class.getDeclaredField("floors");
            floorsField.setAccessible(true);
            floorsField.set(generator, floors);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set floors", e);
        }

        when(floorMock.getStartRoom()).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> generator.getStartRoom(),
                "Should throw IllegalStateException if start room is null");
    }

    @Test
    public void testGetRoomToEast_ValidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[2][1];
        rooms[0][0] = roomMock;
        rooms[1][0] = mock(Room.class);
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getWidth()).thenReturn(2);
        List<Room> connections = new ArrayList<>();
        connections.add(rooms[1][0]);
        when(roomMock.getConnections()).thenReturn(connections);

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomToEast(0, 0, 1);
            assertSame(rooms[1][0], result, "Should return the room to the east");
        }
    }

    @Test
    public void testGetRoomToEast_InvalidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][1];
        rooms[0][0] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getWidth()).thenReturn(1);
        when(roomMock.getConnections()).thenReturn(new ArrayList<>());

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomToEast(0, 0, 1);
            assertNull(result, "Should return null for invalid east move");
        }
    }

    @Test
    public void testGetRoomToWest_ValidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[2][1];
        rooms[0][0] = mock(Room.class);
        rooms[1][0] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getWidth()).thenReturn(2);
        List<Room> connections = new ArrayList<>();
        connections.add(rooms[0][0]);
        when(roomMock.getConnections()).thenReturn(connections);

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomToWest(1, 0, 1);
            assertSame(rooms[0][0], result, "Should return the room to the west");
        }
    }

    @Test
    public void testGetRoomToWest_InvalidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][1];
        rooms[0][0] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getWidth()).thenReturn(1);
        when(roomMock.getConnections()).thenReturn(new ArrayList<>());

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomToWest(0, 0, 1);
            assertNull(result, "Should return null for invalid west move");
        }
    }

    @Test
    public void testGetRoomNorth_ValidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][2];
        rooms[0][0] = mock(Room.class);
        rooms[0][1] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getHeight()).thenReturn(2);
        List<Room> connections = new ArrayList<>();
        connections.add(rooms[0][0]);
        when(roomMock.getConnections()).thenReturn(connections);

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomNorth(0, 1, 1);
            assertSame(rooms[0][0], result, "Should return the room to the north");
        }
    }

    @Test
    public void testGetRoomNorth_InvalidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][1];
        rooms[0][0] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getHeight()).thenReturn(1);
        when(roomMock.getConnections()).thenReturn(new ArrayList<>());

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomNorth(0, 0, 1);
            assertNull(result, "Should return null for invalid north move");
        }
    }

    @Test
    public void testGetRoomSouth_ValidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][2];
        rooms[0][0] = roomMock;
        rooms[0][1] = mock(Room.class);
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getHeight()).thenReturn(2);
        List<Room> connections = new ArrayList<>();
        connections.add(rooms[0][1]);
        when(roomMock.getConnections()).thenReturn(connections);

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomSouth(0, 0, 1);
            assertSame(rooms[0][1], result, "Should return the room to the south");
        }
    }

    @Test
    public void testGetRoomSouth_InvalidMove() {
        List<Floor> floors = new ArrayList<>();
        floors.add(floorMock);
        CastleMapGenerator.setFloors(floors);

        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        Room[][] rooms = new Room[1][1];
        rooms[0][0] = roomMock;
        when(floorMock.getRooms()).thenReturn(rooms);
        when(floorMock.getHeight()).thenReturn(1);
        when(roomMock.getConnections()).thenReturn(new ArrayList<>());

        try (MockedStatic<Player> mockedPlayer = mockStatic(Player.class)) {
            mockedPlayer.when(Player::getInstance).thenReturn(playerMock);

            Room result = CastleMapGenerator.getRoomSouth(0, 0, 1);
            assertNull(result, "Should return null for invalid south move");
        }
    }
}