/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Items.Armor;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potions.EstusBottle;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Items.Weapons.WeaponsStorage;
import com.mycompany.examproject.Map.Floor;
import com.mycompany.examproject.Map.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Arseniy
 */
public class Player {
    // Core stats
    private int hp;
    private int maxHp;
    private int stamina;
    private int maxStamina;
    private int souls;
    private int level;
    private int damage;
    
    private int repairComponents;
    private int baseDamage;
    
    private double dodgeP;
    private double blockP;
    
    private List<Equipment> inventory;
    private Weapon selectedWeapon;
    private Armor selectedArmor;

    // Skills
    private int strength;
    private int dexterity;
    private int endurance;

    // Inventory and equipment
    
    // Position tracking
    private Room currentRoom;

   
    
    private static Player instance = null;
    
    private Player(Room room) {
        this.hp = 150;
        this.maxHp = 150;
        this.stamina = 100;
        this.maxStamina = 100;
        
        this.baseDamage = 15;
        this.repairComponents = 0;
        this.souls = 0;
        this.level = 1;   
        this.dodgeP = 0.2;
        this.blockP = 0.2;
        
        this.strength = 5;
        this.dexterity = 5;
        this.endurance = 5;
        
        this.inventory = new ArrayList<>();
        this.selectedWeapon = null;
        this.selectedArmor = null;        
        
        this.currentRoom = room;       
        
        setStartingEquipment();
        
        this.damage = selectedWeapon.getDamage();
    }
    
    public int getRepairComponents() {
        return repairComponents;
    }

    public void setRepairComponents(int repairComponents) {
        this.repairComponents = repairComponents;
    }
    
    public void setStartingEquipment(){
        Sword swordTemplate = WeaponsStorage.swords.get(0);
        addItemToInventory(new Sword(swordTemplate.getName(), swordTemplate.getWeight(), swordTemplate.getDamage(), swordTemplate.getDurability()));      
        TrooperArmor trooperArmorTemplate = ArmorStorage.trooperArmor.get(0);
        addItemToInventory(new TrooperArmor(trooperArmorTemplate.getName(), trooperArmorTemplate.getWeight(), trooperArmorTemplate.getDamageReduction(), trooperArmorTemplate.getDurability())); 
        this.selectedWeapon = (Weapon) inventory.get(0);
        this.selectedArmor = (Armor) inventory.get(1);
        this.selectedArmor.setSelected(true);
        this.selectedWeapon.setSelected(true);
        
        Sword swordTemplate1 = WeaponsStorage.swords.get(5);
        addItemToInventory(new Sword(swordTemplate1.getName(), swordTemplate1.getWeight(), swordTemplate1.getDamage(), swordTemplate1.getDurability()));
        
        addItemToInventory(new EstusBottle());
    }
    
    public void setBaseDamage(int newBaseDamage){
        this.baseDamage = newBaseDamage;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
    
    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(Weapon selectedWeapon) {
        this.selectedWeapon.setSelected(false);
        this.selectedWeapon = selectedWeapon;
        this.selectedWeapon.setSelected(true);
    }

    public Armor getSelectedArmor() {
        return selectedArmor;
    }

    public void setSelectedArmor(Armor selectedArmor) {
        this.selectedArmor.setSelected(false);
        this.selectedArmor = selectedArmor;
        this.selectedArmor.setSelected(true);
    }
    
    public void takeDamage(int enemyDamage){
        this.getSelectedArmor().setDurability(this.getSelectedArmor().getDurability() - 7);
        this.getSelectedArmor().checkStatus();
        this.setHp((int) (this.hp - enemyDamage*(1 - this.getSelectedArmor().getDamageReduction())));
    }
    
    public static Player getInstance(Room room) {
        if (instance == null) {
            instance = new Player(room);
        }
        return instance;
    }
    
    public static Player getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Player is not initialized! Call getInstance(Room) first.");
        }
        return instance;
    }
    
    public static void resetInstance() {
        instance = null;
    }
    
    public int getHp() { return hp; }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getMaxHp() { return maxHp; }
    public int getStamina() { return stamina; }
    public void setStamina(int stamina){
        this.stamina = stamina;
    }
    public int getMaxStamina() { return maxStamina; }
    public int getSouls() { return souls; }
    public int getLevel() { return level; }
    public int getDamage(){
        return damage;
    }

    public double getDodgeP(){
        return this.dodgeP;
    }
    
    public double getBlockP(){
        return this.blockP;
    }
    
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getEndurance() { return endurance; }
    
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    public Room getCurrentRoom() { return currentRoom; }
    
    
    public void addItemToInventory(Equipment equipment) {
        inventory.add(equipment);
    }
    
    public List<Equipment> getInventory() {
        return inventory;
    }

}