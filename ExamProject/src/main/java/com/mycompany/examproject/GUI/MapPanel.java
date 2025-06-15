/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.GUI;

/**
 *
 * @author Arseniy
 */
import com.mycompany.examproject.Map.CastleMapGenerator;
import com.mycompany.examproject.Map.Floor;
import com.mycompany.examproject.Map.Room;
import com.mycompany.examproject.Player;
import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Room currentRoom = Player.getInstance().getCurrentRoom();
        int currentFloorNumber = currentRoom.getFloor();
        Floor floor = CastleMapGenerator.getFloors().get(currentFloorNumber - 1);
        Room[][] rooms = floor.getRooms();

        final int ROOM_SIZE = 30;
        final int GAP = 10;

        g.setColor(Color.BLACK);

        for (int x = 0; x < floor.getWidth(); x++) {
            for (int y = 0; y < floor.getHeight(); y++) {
                Room room = rooms[x][y];

                int drawX = x * (ROOM_SIZE + GAP);
                int drawY = y * (ROOM_SIZE + GAP);

                if (room == currentRoom) {
                    g.setColor(Color.RED);
                } else if (room.isVisitedByPlayer()) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.BLACK);
                }

                g.fillRect(drawX, drawY, ROOM_SIZE, ROOM_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, ROOM_SIZE, ROOM_SIZE);

                g.drawString(room.getType().name().substring(0, 1), drawX + 10, drawY + 20);
            }
        }
        
        for (int x = 0; x < floor.getWidth(); x++) {
            for (int y = 0; y < floor.getHeight(); y++) {
                Room room = rooms[x][y];
                if (room == null || !room.isVisitedByPlayer()) continue;

                int startX = x * (ROOM_SIZE + GAP) + ROOM_SIZE / 2;
                int startY = y * (ROOM_SIZE + GAP) + ROOM_SIZE / 2;

                for (Room connected : room.getConnections()) {
                    if (!connected.isVisitedByPlayer()) continue;
                    
                    if (connected.getFloor() != currentFloorNumber) continue;

                    int endX = connected.getX() * (ROOM_SIZE + GAP) + ROOM_SIZE / 2;
                    int endY = connected.getY() * (ROOM_SIZE + GAP) + ROOM_SIZE / 2;

                    if (room != currentRoom && connected != currentRoom) {
                        g.setColor(Color.GRAY);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    g.drawLine(startX, startY, endX, endY);
                }
            }
        }
    }
    
}