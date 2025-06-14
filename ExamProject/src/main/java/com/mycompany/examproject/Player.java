/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.GUI.LevelUpdateDialog;
import com.mycompany.examproject.Items.Armor;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.HeavyArmor;
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
    private int damage;
    
    private int repairComponents;
    private int baseDamage;
    
    private double dodgeP;
    private double blockP;
    
    private List<Equipment> inventory;
    private Weapon selectedWeapon;
    private Armor selectedArmor;
    private int bearableWeight;
    
    private int totalSoulsAmount;
    private int currentSoulsAmount;
    
    private int level;

    private int strength;
    private int agility;
    private int endurance;

    private Room currentRoom;

    private static Player instance = null;
    
    private Player(Room room) {
        this.hp = 150;
        this.maxHp = 150;
        

        this.repairComponents = 0;
        this.dodgeP = 0.2;
        this.blockP = 0.2;
        
        this.strength = 2;
        this.agility = 2;
        this.endurance = 2;
        
        this.inventory = new ArrayList<>();
        this.selectedWeapon = null;
        this.selectedArmor = null;    
        
        this.totalSoulsAmount = 10000;
        this.currentSoulsAmount = 10000;
        this.level = 1;
        
        this.currentRoom = room;       
        
        setStartingEquipment();
        
        this.bearableWeight = 50;
        
        this.damage = selectedWeapon.getDamage();
        this.baseDamage = 15 + this.strength * 3;
        
        this.stamina = 90 + this.endurance * 5;
        this.maxStamina = stamina;
    }
    
    public int getBearableWeight() {
        return bearableWeight;
    }

    public void setBearableWeight(int bearableWeight) {
        this.bearableWeight = bearableWeight;
    }

    public int getLevel(){
        return this.level;
    }
    
    public void updateLevel(){
        this.level++;
        LevelUpdateDialog levelUpdateDialog = new LevelUpdateDialog(null, true);
        levelUpdateDialog.setVisible(true);
        updateParametrsBasesOnLVL();
    }
    
    public void updateParametrsBasesOnLVL() {
        double hpRatio = (double) this.hp / this.maxHp;
        double staminaRatio = (double) this.stamina / this.maxStamina;

        this.maxHp = this.maxHp + 25 * this.level;
        this.maxStamina = this.maxStamina + 15 * this.level;

        this.hp = (int) (this.maxHp * hpRatio);
        this.stamina = (int) (this.maxStamina * staminaRatio);
        
        this.bearableWeight = this.bearableWeight + 4;
    }
    
    public int getTotalSoulsAmount(){
        return this.totalSoulsAmount;
    }
    
    public void addSoulsToTotal(int souls){
        this.totalSoulsAmount = this.totalSoulsAmount + souls;
        int newLevel = this.totalSoulsAmount / 500; // например, при 1200 душах — это 2
        
        if (newLevel > getLevel()) {
            updateLevel();
        }
    }
    
    public int getCurrentSoulsAmount(){
        return this.currentSoulsAmount;
    }
    
    public void addCurrentSoulsAmount(int souls){
        this.currentSoulsAmount = this.currentSoulsAmount + souls;
        addSoulsToTotal(souls);
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
        
        Sword swordTemplate1 = WeaponsStorage.swords.get(10);
        addItemToInventory(new Sword(swordTemplate1.getName(), swordTemplate1.getWeight(), swordTemplate1.getDamage(), swordTemplate1.getDurability()));
        
        HeavyArmor heavyArmor = ArmorStorage.heavyArmor.get(14);
        addItemToInventory(new HeavyArmor(heavyArmor.getName(), heavyArmor.getWeight(), heavyArmor.getDamageReduction(), heavyArmor.getDurability()));
        
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
        
        //вызов метода, которыйобновляет шанс уклонения
    }

    public Armor getSelectedArmor() {
        return selectedArmor;
    }

    public void setSelectedArmor(Armor selectedArmor) {
        this.selectedArmor.setSelected(false);
        this.selectedArmor = selectedArmor;
        this.selectedArmor.setSelected(true);
        
        //вызов метода, которыйобновляет шанс уклонения
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
    public int getAgility() { return agility; }
    public int getEndurance() { return endurance; }
    
    public void boostStrength(int soulsSpended) {
        this.strength = Math.min(this.strength + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;
        this.baseDamage += 3;
        this.bearableWeight += 1;
    }

    public void boostAgility(int soulsSpended) {
        this.agility = Math.min(this.agility + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;        
        this.dodgeP += 0.025;
    }

    public void boostEndurance(int soulsSpended) {
        this.endurance = Math.min(this.endurance + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;      
        this.stamina += 5;
        this.bearableWeight += 1;
    }

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

    public int getTotalEquipmentWeight(){
        int totalWeight = this.selectedArmor.getWeight() + this.selectedWeapon.getWeight();
        return totalWeight;
    }
    
}