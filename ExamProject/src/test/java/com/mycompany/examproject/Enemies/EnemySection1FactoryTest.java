/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.CursedSkeleton;
import com.mycompany.examproject.Enemies.enemyStructure.Hound;
import com.mycompany.examproject.Enemies.enemyStructure.Knight;
import com.mycompany.examproject.Enemies.enemyStructure.RottingKnight;
import com.mycompany.examproject.Enemies.enemyStructure.ShadowHound;
import com.mycompany.examproject.Enemies.enemyStructure.Skeleton;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnemySection1FactoryTest {

    private EnemySection1Factory factory;

    @BeforeEach
    public void setUp() {
        factory = new EnemySection1Factory();
    }

    @Test
    public void testCreateSkeleton() {
        Skeleton skeleton = factory.createSkeleton(1);
        assertTrue(skeleton instanceof CursedSkeleton);
        assertEquals("Cursed skeleton", skeleton.getName());
        assertEquals(110, skeleton.getHealth());
        assertEquals(22, skeleton.getDamage());
    }

    @Test
    public void testCreateHound() {
        Hound hound = factory.createHound(1);
        assertTrue(hound instanceof ShadowHound);
        assertEquals("Shadow hound", hound.getName());
        assertEquals(90, hound.getHealth());
        assertEquals(27, hound.getDamage());
    }

    @Test
    public void testCreateKnight() {
        Knight knight = factory.createKnight(1);
        assertTrue(knight instanceof RottingKnight);
        assertEquals("Rotting knight", knight.getName());
        assertEquals(130, knight.getHealth());
        assertEquals(32, knight.getDamage());
    }

}