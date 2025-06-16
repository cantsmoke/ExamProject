/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Sword;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тесты для класса CursedSkeleton.
 *
 * @author Arseniy
 */
public class CursedSkeletonTest {

    private CursedSkeleton skeleton;
    private Weapon mockBow;
    private Weapon mockHammer;
    private Weapon mockSword;

    @BeforeEach
    public void setUp() {
        skeleton = new CursedSkeleton("Test Skeleton", 100, 20, 1);
        mockBow = mock(Bow.class);
        mockHammer = mock(Hammer.class);
        mockSword = mock(Sword.class);
        when(mockBow.getDamage()).thenReturn(10);
        when(mockHammer.getDamage()).thenReturn(10);
        when(mockSword.getDamage()).thenReturn(10);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Skeleton", skeleton.getName());
        assertEquals(110, skeleton.getHealth());
        assertEquals(22, skeleton.getDamage());
        assertArrayEquals(CursedSkeleton.getDefaultPattern(), skeleton.getPattern());
        assertEquals("/ChatGPT Image 4 июн. 2025 г., 14_03_51.png", skeleton.getIconSource());
        assertEquals(0.2, skeleton.getDodgeP(), 0.001);
        assertEquals(0.4, skeleton.getBlockP(), 0.001);
    }

    @Test
    public void testTakeHeavyDamageWithBow() {
        int baseDamage = 15;
        String result = skeleton.takeHeavyDamage(mockBow, baseDamage);
        assertEquals("Test Skeleton took 21 damage!", result);
        assertEquals(89, skeleton.getHealth());
    }

    @Test
    public void testTakeHeavyDamageWithHammer() {
        int baseDamage = 15;
        String result = skeleton.takeHeavyDamage(mockHammer, baseDamage);
        assertEquals("Test Skeleton took 42 damage!", result);
        assertEquals(68, skeleton.getHealth());
    }

    @Test
    public void testTakeHeavyDamageWithSword() {
        int baseDamage = 15;
        String result = skeleton.takeHeavyDamage(mockSword, baseDamage);
        assertEquals("Test Skeleton took 30 damage!", result);
        assertEquals(80, skeleton.getHealth());
    }

    @Test
    public void testTakeLightDamageWithBow() {
        int baseDamage = 15;
        String result = skeleton.takeLightDamage(mockBow, baseDamage);
        assertEquals("Test Skeleton took 15 damage!", result);
        assertEquals(95, skeleton.getHealth());
    }

    @Test
    public void testTakeLightDamageWithHammer() {
        int baseDamage = 15;
        String result = skeleton.takeLightDamage(mockHammer, baseDamage);
        assertEquals("Test Skeleton took 26 damage!", result);
        assertEquals(84, skeleton.getHealth());
    }

    @Test
    public void testTakeLightDamageWithSword() {
        int baseDamage = 15;
        String result = skeleton.takeLightDamage(mockSword, baseDamage);
        assertEquals("Test Skeleton took 20 damage!", result);
        assertEquals(90, skeleton.getHealth());
    }

    @Test
    public void testGetSouls() {
        assertEquals(100, skeleton.getSouls());
    }

    @Test
    public void testGetDefaultPattern() {
        EntityActionType[] pattern = CursedSkeleton.getDefaultPattern();
        assertArrayEquals(new EntityActionType[]{EntityActionType.LIGHT_ATTACK, EntityActionType.LIGHT_ATTACK, EntityActionType.HEAVY_ATTACK}, pattern);
    }
}