/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject;

import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.examproject.Fight;
import org.junit.jupiter.api.Test;

public class FightTest {

    @Test
    public void testDodgePenalty_MinWeight() {
        double penalty = Fight.calculateEquipmentDodgePenalty(6.0);
        assertEquals(0.0, penalty, 0.0001);
    }

    @Test
    public void testDodgePenalty_MaxWeight() {
        double penalty = Fight.calculateEquipmentDodgePenalty(100.0);
        assertEquals(0.3, penalty, 0.0001);
    }

    @Test
    public void testDodgePenalty_BelowMinWeight() {
        double penalty = Fight.calculateEquipmentDodgePenalty(0.0);
        assertEquals(0.0, penalty, 0.0001);
    }

    @Test
    public void testDodgePenalty_AboveMaxWeight() {
        double penalty = Fight.calculateEquipmentDodgePenalty(150.0);
        assertEquals(0.3, penalty, 0.0001);
    }

    @Test
    public void testDodgePenalty_MiddleWeight() {
        double penalty = Fight.calculateEquipmentDodgePenalty(53.0);
        double expectedPenalty = (53.0 - 6.0) * (0.3 / 94.0);
        assertEquals(expectedPenalty, penalty, 0.0001);
    }
    
}