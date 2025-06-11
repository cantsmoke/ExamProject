/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Items.Item;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Armor;
import com.mycompany.examproject.Items.Consumable;
import com.mycompany.examproject.Items.ItemType;
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
    
    private double dodgeP;
    private double blockP;

    // Skills
    private int strength;
    private int dexterity;
    private int endurance;

    // Inventory and equipment
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    
    // Position tracking
    private Room currentRoom;

    // Abilities
    private boolean hasCounterattack; // Unlocked ability: counterattack after successful block

    // Combat-related
    private Random random;
    private double baseDodgeChance; // Base chance modified by dexterity and weight
    private double baseAttackSpeed; // Base speed modified by weight

    // Quests
    private List<Quest> activeQuests;

    // Ghost system
    private List<String> messagesForFuture; // Messages left for future playthroughs
    private List<String> pastActions; // Actions recorded for ghost system on death
    
    private static Player instance = null;
    
    private Player(Room room) {
        // Initial stats
        this.hp = 200;
        this.maxHp = 200;
        this.stamina = 100;
        this.maxStamina = 100;
        this.damage = 45;
        this.souls = 0;
        this.level = 1;
        
        this.dodgeP = 0.2;
        this.blockP = 0.2;

        // Initial skills
        this.strength = 5;
        this.dexterity = 5;
        this.endurance = 5;

        // Inventory setup
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
        
        // Set initial position
        this.currentRoom = room;

        // Abilities
        this.hasCounterattack = false;

        // Combat setup
        this.random = new Random();
        this.baseDodgeChance = 0.1; // 10% base dodge chance
        this.baseAttackSpeed = 1.0; // Normal speed

        // Quests and ghost system
        this.activeQuests = new ArrayList<>();
        this.messagesForFuture = new ArrayList<>();
        this.pastActions = new ArrayList<>();

//        // Starting equipment
//        addItemToInventory(new Weapon("Rusty Sword", 50, 5, 10, 0.0, "A rusty but reliable sword."));
//        addItemToInventory(new Armor("Leather Armor", 50, 3, 5, "Light armor offering basic protection."));
//        equipItem("Rusty Sword");
//        equipItem("Leather Armor");
    }
    
    public static Player getInstance(Room room) {
        if (instance == null) {
            instance = new Player(room);
        }
        return instance;
    }
    
    // Перегруженный метод если нужно обращаться к уже созданному одиночке без передачи комнаты:
    public static Player getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Player is not initialized! Call getInstance(Room) first.");
        }
        return instance;
    }
    
    public static void resetInstance() {
        instance = null;
    }

    
    // Getters for core stats
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
    
    // Getters for skills
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getEndurance() { return endurance; }
    
    // Getters for position
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    public Room getCurrentRoom() { return currentRoom; }

    // Combat actions
//    public int performLightAttack(Enemy enemy) {
//        if (stamina < 10) return 0; // Not enough stamina
//        stamina -= 10;
//        recordAction("Performed a light attack");
//        int damage = (int)(equippedWeapon.getDamage() + strength * 0.5);
//        if (enemy.isBoss() && equippedWeapon.getBossDamageBonus() > 0) {
//            damage = (int)(damage * (1 + equippedWeapon.getBossDamageBonus()));
//        }
//        if (random.nextDouble() < (dexterity * 0.01)) { // Critical hit chance based on dexterity
//            damage *= 2;
//            recordAction("Landed a critical hit!");
//        }
//        equippedWeapon.reduceDurability(1);
//        if (equippedWeapon.isBroken()) {
//            equippedWeapon = null;
//            recordAction("Weapon broke during attack!");
//        }
//        return damage;
//    }
//
//    public int performHeavyAttack(Enemy enemy) {
//        if (stamina < 20) return 0; // Not enough stamina
//        stamina -= 20;
//        recordAction("Performed a heavy attack");
//        int damage = (int)(equippedWeapon.getDamage() * 1.5 + strength * 0.75);
//        if (enemy.isBoss() && equippedWeapon.getBossDamageBonus() > 0) {
//            damage = (int)(damage * (1 + equippedWeapon.getBossDamageBonus()));
//        }
//        if (random.nextDouble() < (dexterity * 0.01)) {
//            damage *= 2;
//            recordAction("Landed a critical hit!");
//        }
//        equippedWeapon.reduceDurability(2);
//        if (equippedWeapon.isBroken()) {
//            equippedWeapon = null;
//            recordAction("Weapon broke during attack!");
//        }
//        return damage;
//    }
//
//    public int block() {
//        if (stamina < 15) return 0; // Not enough stamina
//        stamina -= 15;
//        recordAction("Blocked an attack");
//        int damageReduction = equippedArmor != null ? equippedArmor.getDefense() : 0;
//        if (equippedArmor != null) {
//            equippedArmor.reduceDurability(1);
//            if (equippedArmor.isBroken()) {
//                equippedArmor = null;
//                recordAction("Armor broke during block!");
//            }
//        }
//        return damageReduction;
//    }
//
//    public boolean dodge() {
//        if (stamina < 10) return false; // Not enough stamina
//        stamina -= 10;
//        double dodgeChance = baseDodgeChance + (dexterity * 0.02);
//        int totalWeight = (equippedWeapon != null ? equippedWeapon.getWeight() : 0) + 
//                         (equippedArmor != null ? equippedArmor.getWeight() : 0);
//        dodgeChance -= totalWeight * 0.01; // Weight reduces dodge chance
//        boolean success = random.nextDouble() < Math.max(0, dodgeChance);
//        recordAction(success ? "Dodged an attack" : "Failed to dodge");
//        return success;
//    }

//    public String useItem(String itemName) {
//        Consumable item = (Consumable) inventory.stream()
//            .filter(i -> i.getName().equals(itemName) && i.getType() == ItemType.CONSUMABLE)
//            .findFirst()
//            .orElse(null);
//        if (item == null) return "Item not found!";
//        inventory.remove(item);
//        recordAction("Used item: " + itemName);
//        if (item.getEffect().startsWith("heal")) {
//            int healAmount = Integer.parseInt(item.getEffect().split(" ")[1]);
//            hp = Math.min(maxHp, hp + healAmount);
//            return "Healed for " + healAmount + " HP!";
//        }
//        // Add more effects (e.g., bombs) as needed
//        return "Used " + itemName + "!";
//    }
//
//    // Inventory management
//    public void addItemToInventory(Item item) {
//        inventory.add(item);
//    }
//
//    public void equipItem(String itemName) {
//        Item item = inventory.stream()
//            .filter(i -> i.getName().equals(itemName))
//            .findFirst()
//            .orElse(null);
//        if (item == null) return;
//        if (item.getType() == ItemType.WEAPON) {
//            equippedWeapon = (Weapon) item;
//        } else if (item.getType() == ItemType.ARMOR) {
//            equippedArmor = (Armor) item;
//        }
//        recordAction("Equipped " + itemName);
//    }
//
//    public void repairItem(String itemName) {
//        Item item = inventory.stream()
//            .filter(i -> i.getName().equals(itemName))
//            .findFirst()
//            .orElse(null);
//        if (item != null) {
//            item.repair();
//            recordAction("Repaired " + itemName);
//        }
//    }

    public List<Item> getInventory() { return inventory; }
    public Weapon getEquippedWeapon() { return equippedWeapon; }
    public Armor getEquippedArmor() { return equippedArmor; }

    // Progression and leveling
//    public void gainSouls(int amount) {
//        souls += amount;
//        recordAction("Gained " + amount + " souls");
//    }
//
//    public void levelUp() {
//        if (souls < level * 100) return; // Simple soul requirement for leveling
//        souls -= level * 100;
//        level++;
//        maxHp += 10;
//        hp = maxHp;
//        maxStamina += 5;
//        stamina = maxStamina;
//        recordAction("Leveled up to level " + level);
//        if (level == 5) { // Example: unlock counterattack at level 5
//            hasCounterattack = true;
//            recordAction("Unlocked ability: Counterattack");
//        }
//    }
//
//    public void allocateSouls(String skill, int amount) {
//        if (souls < amount * 10) return; // Simple cost: 10 souls per point
//        souls -= amount * 10;
//        switch (skill.toLowerCase()) {
//            case "strength":
//                strength += amount;
//                recordAction("Increased Strength to " + strength);
//                break;
//            case "dexterity":
//                dexterity += amount;
//                recordAction("Increased Dexterity to " + dexterity);
//                break;
//            case "endurance":
//                endurance += amount;
//                maxStamina += amount * 5;
//                stamina = maxStamina;
//                recordAction("Increased Endurance to " + endurance);
//                break;
//        }
//    }

//    // Combat support
//    public void takeDamage(int damage, boolean blocked) {
//        int finalDamage = blocked && equippedArmor != null ? Math.max(0, damage - equippedArmor.getDefense()) : damage;
//        hp -= finalDamage;
//        recordAction("Took " + finalDamage + " damage");
//        if (hp <= 0) {
//            hp = 0;
//            recordAction("Player died!");
//        }
//    }
//
//    public int counterattack(Enemy enemy) {
//        if (!hasCounterattack || stamina < 10) return 0;
//        stamina -= 10;
//        int damage = (int)(equippedWeapon.getDamage() * 0.8 + strength * 0.4);
//        recordAction("Performed a counterattack");
//        return damage;
//    }

//    public void restoreStamina() {
//        stamina = maxStamina; // Full restore after combat
//        recordAction("Stamina fully restored");
//    }
//
//    public void skipTurn() {
//        stamina = Math.min(maxStamina, stamina + 10); // Partial restore
//        recordAction("Skipped turn, restored 10 stamina");
//    }
//
//    public boolean isAlive() { return hp > 0; }
//
//    // Quest handling
//    public void acceptQuest(Quest quest) {
//        activeQuests.add(quest);
//        recordAction("Accepted quest: " + quest.getDescription());
//    }
//
//    public void updateQuestProgress(String enemyType) {
//        for (Quest quest : activeQuests) {
//            if (quest.getDescription().contains(enemyType)) {
//                quest.incrementProgress();
//                recordAction("Quest progress: " + quest.getCurrentCount() + "/" + quest.getTargetCount());
//            }
//        }
//    }

//    public List<Quest> getActiveQuests() { return activeQuests; }

//    // Ghost system
//    public void leaveMessage(String message) {
//        messagesForFuture.add(message);
//        recordAction("Left message: " + message);
//    }
//
//    private void recordAction(String action) {
//        pastActions.add(action);
//    }
//
//    public List<String> getPastActions() { return pastActions; }
//    public List<String> getMessagesForFuture() { return messagesForFuture; }
}