/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.BossFactory;
import com.mycompany.examproject.Enemies.EnemySection1Factory;
import com.mycompany.examproject.Enemies.EnemySection2Factory;
import com.mycompany.examproject.Enemies.EnemySection3Factory;
import com.mycompany.examproject.Enemies.enemyStructure.Bosses.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.Bosses.BossType;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.GUI.EnemyEncounteredDialog;
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
    
    private static EnemySection1Factory enemySection1Factory;
    private static EnemySection2Factory enemySection2Factory;
    private static EnemySection3Factory enemySection3Factory;

    private GUIandLogicIntermediary(){}
    
    public static void handleNewGameButtonPressed(){ 
        castleMapGenerator = new CastleMapGenerator();
        castleMapGenerator.generateMap();
        
        enemySection1Factory = new EnemySection1Factory();
        enemySection2Factory = new EnemySection2Factory();
        enemySection3Factory = new EnemySection3Factory();
        
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
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingEast() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToEast(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingWest() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToWest(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
    }
    
    public static void handlePLayerGoingNorth() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomNorth(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
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
    
    private static void handleEnemyEncounter(Room currentRoom) {
        double encounterProbability = 0.2 + currentRoom.getFloor() * 0.05;
        double randomValue = Math.random();
        
        if (encounterProbability > randomValue && currentRoom.getType() != RoomType.STAIRCASE_DOWN
        && currentRoom.getType() != RoomType.STAIRCASE_UP && currentRoom.getType() != RoomType.REST 
        && currentRoom.getType() != RoomType.BOSS && currentRoom.getFloor() != 10 && currentRoom.getType() != RoomType.ENTRANCE_HALL
        && currentRoom.isVisitedByPlayer() == false){
            
            EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
            enemyEncounteredDialog.setVisible(true);
            Enemy enemy = generateBasicEnemy();
            Fight fight = new Fight(player, enemy);
            
        } else if (currentRoom.getType() == RoomType.BOSS && currentRoom.isVisitedByPlayer() == false){
            int floor = currentRoom.getFloor();
            if (floor >= 1 && floor <= 10) {
                EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
                enemyEncounteredDialog.setVisible(true);
                BossType bossType = BossType.values()[floor - 1];
                Boss boss = BossFactory.createBoss(bossType);
                Fight fight = new Fight(player, boss);
            }
        }
    }
    
    private static Enemy generateBasicEnemy(){
        Enemy enemy = null;
        if (player.getCurrentRoom().getFloor() <= 3) {
            enemy = enemySection1Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        } else if (player.getCurrentRoom().getFloor() <= 5) {
            enemy = enemySection2Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        } else if (player.getCurrentRoom().getFloor() <= 9) {
            enemy = enemySection3Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        }
        return enemy;
    }
    
}