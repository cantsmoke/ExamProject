/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potions.EstusBottle;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Map.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Тесты для класса Player.
 *
 * @author Arseniy
 */
public class PlayerTest {

    private Room room;
    
    @BeforeEach
    public void setUp() {
        Player.resetInstance();
        room = mock(Room.class);
    }

    @Test
    public void testSingletonInstance() {
        Room room1 = mock(Room.class);
        Room room2 = mock(Room.class);
        Player player1 = Player.getInstance(room1);
        Player player2 = Player.getInstance(room2);
        assertSame(player1, player2, "Multiple getInstance calls should return the same instance");

        Room room3 = mock(Room.class);
        Player.resetInstance();
        Player player3 = Player.getInstance(room3);
        assertNotSame(player1, player3, "After reset, a new instance should be created");
    }

    @Test
    public void testInitialValues() {
        Player player = Player.getInstance(room);
        assertEquals(150, player.getHp(), "Initial HP should be 150");
        assertEquals(150, player.getMaxHp(), "Initial max HP should be 150");
        assertEquals(0, player.getRepairComponents(), "Initial repair components should be 0");
        assertEquals(0.7, player.getBlockP(), 0.001, "Initial block probability should be 0.7");
        assertEquals(2, player.getStrength(), "Initial strength should be 2");
        assertEquals(2, player.getAgility(), "Initial agility should be 2");
        assertEquals(2, player.getEndurance(), "Initial endurance should be 2");
        assertFalse(player.hasCounterAttack(), "Counter attack should be initially false");
        assertEquals(0, player.getTotalSoulsAmount(), "Initial total souls should be 0");
        assertEquals(0, player.getCurrentSoulsAmount(), "Initial current souls should be 0");
        assertEquals(1, player.getLevel(), "Initial level should be 1");
        assertSame(room, player.getCurrentRoom(), "Initial room should be the one passed");
        assertEquals(50, player.getBearableWeight(), "Initial bearable weight should be 50");
        assertEquals(10, player.getDamage(), "Initial damage should equal weapon damage (10)");
        assertEquals(21, player.getBaseDamage(), "Initial base damage should be 15 + 2*3 = 21");
        assertEquals(60, player.getStamina(), "Initial stamina should be 50 + 2*5 = 60");
        assertEquals(60, player.getMaxStamina(), "Initial max stamina should be 60");

        List<Equipment> inventory = player.getInventory();
        assertEquals(3, inventory.size(), "Inventory should have 3 items");
        assertTrue(inventory.get(0) instanceof Sword, "First item should be a Sword");
        assertTrue(inventory.get(1) instanceof TrooperArmor, "Second item should be TrooperArmor");
        assertTrue(inventory.get(2) instanceof EstusBottle, "Third item should be an EstusBottle");

        assertSame(inventory.get(0), player.getSelectedWeapon(), "Selected weapon should be the first item");
        assertSame(inventory.get(1), player.getSelectedArmor(), "Selected armor should be the second item");
        assertTrue(player.getSelectedWeapon().isSelected(), "Selected weapon should be marked as selected");
        assertTrue(player.getSelectedArmor().isSelected(), "Selected armor should be marked as selected");

        assertEquals(0.465, player.getDodgeP(), 0.001, "Initial dodgeP should be 0.5 + (2*0.025) - (15*0.005) = 0.475");
        assertEquals(0.030556, player.getCritP(), 0.001, "Initial critP should be 0.025 + 0.05*(6-3)/27 ≈ 0.030556");
    }

    @Test
    public void testAddItemToInventory() {
        Equipment newItem = new EstusBottle();
        Player.getInstance(room).addItemToInventory((Equipment) newItem);
        assertEquals(4, Player.getInstance(room).getInventory().size(), "Inventory size should increase to 4");
        assertSame(newItem, Player.getInstance(room).getInventory().get(3), "New item should be added to inventory");
    }

    @Test
    public void testSetSelectedWeapon() {
        Player player = Player.getInstance(room);
        Weapon newWeapon = new Sword("New Sword", 6, 15, 100);
        player.addItemToInventory(newWeapon);
        player.setSelectedWeapon(newWeapon);
        assertSame(newWeapon, player.getSelectedWeapon(), "New weapon should be selected");
        assertTrue(newWeapon.isSelected(), "New weapon should be marked as selected");
        assertFalse(((Weapon)player.getInventory().get(0)).isSelected(), "Previous weapon should be deselected");
    }

    @Test
    public void testTakeDamage() {
        Player player = Player.getInstance(room);
        int initialHp = player.getHp();
        int initialDurability = player.getSelectedArmor().getDurability();
        int enemyDamage = 50;
        player.takeDamage(enemyDamage);
        assertEquals(initialDurability - 7, player.getSelectedArmor().getDurability(), "Armor durability should decrease by 7");
        assertEquals(initialHp - (int)(enemyDamage * (1 - 0.3)), player.getHp(), "HP should decrease by enemyDamage * (1 - 0.2)");
    }

    @Test
    public void testAddSoulsToTotal_NoLevelUp() {
        Player player = Player.getInstance(room);
        player.addSoulsToTotal(100);
        assertEquals(100, player.getTotalSoulsAmount(), "Total souls should increase by 100");
        assertEquals(1, player.getLevel(), "Level should remain 1");
    }

    @Test
    public void testUpdateParametersOnLevelUp() {
        Player player = Player.getInstance(room);
        int initialMaxHp = player.getMaxHp();
        int initialMaxStamina = player.getMaxStamina();
        int initialBearableWeight = player.getBearableWeight();
        double hpRatio = (double) player.getHp() / player.getMaxHp();
        double staminaRatio = (double) player.getStamina() / player.getMaxStamina();

        player.updateParametrsBasesOnLVL();

        assertEquals(initialMaxHp + 25, player.getMaxHp(), "Max HP should increase by 25");
        assertEquals(initialMaxStamina + 15, player.getMaxStamina(), "Max stamina should increase by 15");
        assertEquals(initialBearableWeight + 4, player.getBearableWeight(), "Bearable weight should increase by 4");
        assertEquals((int)(player.getMaxHp() * hpRatio), player.getHp(), "HP should scale with ratio");
        assertEquals((int)(player.getMaxStamina() * staminaRatio), player.getStamina(), "Stamina should scale with ratio");
    }

    @Test
    public void testBoostStrength() {
        Player player = Player.getInstance(room);
        int initialStrength = player.getStrength();
        int initialBaseDamage = player.getBaseDamage();
        int initialBearableWeight = player.getBearableWeight();
        player.addCurrentSoulsAmount(100);
        player.boostStrength(50);
        assertEquals(initialStrength + 1, player.getStrength(), "Strength should increase by 1");
        assertEquals(initialBaseDamage + 3, player.getBaseDamage(), "Base damage should increase by 3");
        assertEquals(initialBearableWeight + 1, player.getBearableWeight(), "Bearable weight should increase by 1");
        assertEquals(50, player.getCurrentSoulsAmount(), "Souls should decrease by 50");
        assertEquals(0.032407, player.getCritP(), 0.001, "CritP should update to ≈0.032407");
    }

    @Test
    public void testBoostStrength_MaxLevel() {
        Player player = Player.getInstance(room);
        for (int i = 0; i < 10; i++) {
            player.boostStrength(0);
        }
        player.addCurrentSoulsAmount(100);
        player.boostStrength(50);
        assertEquals(10, player.getStrength(), "Strength should not exceed 10");
        assertEquals(50, player.getCurrentSoulsAmount(), "Souls should still be deducted");
    }

    @Test
    public void testUnlockCounterAttack() {
        Player player = Player.getInstance(room);
        player.addCurrentSoulsAmount(100);
        player.unlockCounterAttack(50);
        assertTrue(player.hasCounterAttack(), "Counter attack should be unlocked");
        assertEquals(50, player.getCurrentSoulsAmount(), "Souls should decrease by 50");
    }

    @Test
    public void testSetCurrentRoom() {
        Player player = Player.getInstance(room);
        Room newRoom = mock(Room.class);
        player.setCurrentRoom(newRoom);
        assertSame(newRoom, player.getCurrentRoom(), "Current room should be updated");
    }

    @Test
    public void testGetTotalEquipmentWeight() {
        Player player = Player.getInstance(room);
        int expectedWeight = player.getSelectedWeapon().getWeight() + player.getSelectedArmor().getWeight();
        assertEquals(expectedWeight, player.getTotalEquipmentWeight(), "Total equipment weight should match sum of selected items");
    }

}