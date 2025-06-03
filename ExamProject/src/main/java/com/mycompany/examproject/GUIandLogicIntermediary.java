/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.GUI.StateAndNavigationForm;
import com.mycompany.examproject.Map.CastleMapGenerator;
import com.mycompany.examproject.Map.Floor;
import com.mycompany.examproject.Map.Room;
import com.mycompany.examproject.Map.RoomType;

/**
 *
 * @author Arseniy
 */
public class GUIandLogicIntermediary {
    
    private static StateAndNavigationForm stateAndNavigationForm;
    private static Player player;
    private static CastleMapGenerator castleMapGenerator;

    private GUIandLogicIntermediary(){}
    
    public static void handleNewGameButtonPressed(){ 
        castleMapGenerator = new CastleMapGenerator();
        castleMapGenerator.generateMap();
        
        player = Player.getInstance(castleMapGenerator.getStartRoom());
        
        stateAndNavigationForm = new StateAndNavigationForm();
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        stateAndNavigationForm.setVisible(true);
    }
    
    public static void handlePLayerGoingSouth() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomSouth(x, y, floorNumber));
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingEast() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToEast(x, y, floorNumber));
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingWest() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToWest(x, y, floorNumber));
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingNorth() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomNorth(x, y, floorNumber));
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerUsingStairs() {
        Room current = player.getCurrentRoom();

        if (current.getType() == RoomType.STAIRCASE_UP) {
            int nextFloorNumber = current.getFloor() + 1;
            Floor nextFloor = CastleMapGenerator.getFloors().get(nextFloorNumber - 1);

            Room targetRoom = nextFloor.getStaircaseDownRoom();
            if (targetRoom != null) {
                player.setCurrentRoom(targetRoom);
                targetRoom.setVisitedByPlayer(true);
            }

        } else if (current.getType() == RoomType.STAIRCASE_DOWN) {
            int previousFloorNumber = current.getFloor() - 1;
            Floor prevFloor = CastleMapGenerator.getFloors().get(previousFloorNumber - 1);

            Room targetRoom = prevFloor.getStaircaseUpRoom();
            if (targetRoom != null) {
                player.setCurrentRoom(targetRoom);
                targetRoom.setVisitedByPlayer(true);
            }
        }

        stateAndNavigationForm.updateLabels();
    }
    
}