/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса BossFactory.
 *
 * @author Arseniy
 */
public class BossFactoryTest {

    @Test
    public void testCreateBoss_FloorOneBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_ONE_BOSS);
        assertTrue(boss instanceof AshenGatekeeper);
        assertEquals("Ashen Gatekeeper", boss.getName());
        assertEquals(300, boss.getHealth());
        assertEquals(50, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorTwoBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_TWO_BOSS);
        assertTrue(boss instanceof SirenOfOblivion);
        assertEquals("Siren of Oblivion", boss.getName());
        assertEquals(400, boss.getHealth());
        assertEquals(70, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorThreeBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_THREE_BOSS);
        assertTrue(boss instanceof WormOfLivingStone);
        assertEquals("Worm of Living Stone", boss.getName());
        assertEquals(500, boss.getHealth());
        assertEquals(80, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorFourBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_FOUR_BOSS);
        assertTrue(boss instanceof ConfessorOfTheRottingLight);
        assertEquals("Confessor of the Rotting Light", boss.getName());
        assertEquals(550, boss.getHealth());
        assertEquals(85, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorFiveBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_FIVE_BOSS);
        assertTrue(boss instanceof BloodHeirOfTheMark);
        assertEquals("Blood Heir of the Mark", boss.getName());
        assertEquals(600, boss.getHealth());
        assertEquals(90, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorSixBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_SIX_BOSS);
        assertTrue(boss instanceof WardenOfDeathSands);
        assertEquals("Warden of the Death Sands", boss.getName());
        assertEquals(650, boss.getHealth());
        assertEquals(90, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorSevenBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_SEVEN_BOSS);
        assertTrue(boss instanceof BroodmotherOfTheHollowedWeb);
        assertEquals("Broodmother of the Hollowed Web", boss.getName());
        assertEquals(700, boss.getHealth());
        assertEquals(95, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorEightBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_EIGHT_BOSS);
        assertTrue(boss instanceof LordOfLabyrinthineShadows);
        assertEquals("Lord of Labyrinthine Shadows", boss.getName());
        assertEquals(700, boss.getHealth());
        assertEquals(95, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorNineBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_NINE_BOSS);
        assertTrue(boss instanceof ExecutionerOfTheLastCreed);
        assertEquals("Executioner of the Last Creed", boss.getName());
        assertEquals(600, boss.getHealth());
        assertEquals(120, boss.getDamage());
    }

    @Test
    public void testCreateBoss_FloorTenBoss() {
        Boss boss = BossFactory.createBoss(BossType.FLOOR_TEN_BOSS);
        assertTrue(boss instanceof TheUnhallowedArchon);
        assertEquals("The Unhallowed Archon", boss.getName());
        assertEquals(1300, boss.getHealth());
        assertEquals(130, boss.getDamage());
    }

    @Test
    public void testCreateBoss_UnknownBossType() {
        assertThrows(NullPointerException.class, () -> {
            BossFactory.createBoss(null);
        });
    }
}