/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.EnemySection1Factory;
import com.mycompany.examproject.Enemies.EnemySection2Factory;
import com.mycompany.examproject.Enemies.EnemySection3Factory;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.Map.Room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса GUIandLogicIntermediary.
 *
 * @author Arseniy
 */
public class GUIandLogicIntermediaryTest {

    private Player playerMock;
    private Room roomMock;
    private EnemySection1Factory enemySection1FactoryMock;
    private EnemySection2Factory enemySection2FactoryMock;
    private EnemySection3Factory enemySection3FactoryMock;

    @BeforeEach
    public void setUp() throws Exception {
        var playerField = GUIandLogicIntermediary.class.getDeclaredField("player");
        var enemy1Field = GUIandLogicIntermediary.class.getDeclaredField("enemySection1Factory");
        var enemy2Field = GUIandLogicIntermediary.class.getDeclaredField("enemySection2Factory");
        var enemy3Field = GUIandLogicIntermediary.class.getDeclaredField("enemySection3Factory");

        playerField.setAccessible(true);
        enemy1Field.setAccessible(true);
        enemy2Field.setAccessible(true);
        enemy3Field.setAccessible(true);

        playerMock = mock(Player.class);
        roomMock = mock(Room.class);
        enemySection1FactoryMock = mock(EnemySection1Factory.class);
        enemySection2FactoryMock = mock(EnemySection2Factory.class);
        enemySection3FactoryMock = mock(EnemySection3Factory.class);

        playerField.set(null, playerMock);
        enemy1Field.set(null, enemySection1FactoryMock);
        enemy2Field.set(null, enemySection2FactoryMock);
        enemy3Field.set(null, enemySection3FactoryMock);
    }

    @Test
    public void testGenerateBasicEnemy_Floor1to3() {
        when(playerMock.getCurrentRoom()).thenReturn(roomMock);
        when(roomMock.getFloor()).thenReturn(2);

        Enemy enemyMock = mock(Enemy.class);
        when(enemySection1FactoryMock.createRandomEnemy(2)).thenReturn(enemyMock);

        Enemy result = GUIandLogicIntermediary.generateBasicEnemy();

        assertSame(enemyMock, result);
    }
    
}