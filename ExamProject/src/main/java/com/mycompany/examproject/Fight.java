/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.GUI.PotionAlreadyUsedDialog;
import com.mycompany.examproject.Enemies.enemyStructure.SirenOfOblivion;
import com.mycompany.examproject.Enemies.enemyStructure.Skeleton;
import com.mycompany.examproject.GUI.*;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.HeavyArmor;
import com.mycompany.examproject.Items.Armors.LightArmor;
import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.Poison;
import com.mycompany.examproject.Items.Potions.StaminaPotion;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Spear;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Items.Weapons.WeaponsStorage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
/**
 *
 * @author Arseniy
 */
public class Fight {

    private Player player;
    private Enemy enemy;
    private static BattleForm battleForm;
    private int currentEnemyActionPattern = 0;
    
    private int poisonDuration = 0;
    private Poison poison = null;
    
    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        Random random = new Random();
        
        BattleForm battleForm = new BattleForm();
        battleForm.updateLabels(player, enemy);
        battleForm.setVisible(true);
        
        this.battleForm = battleForm;
    }
    
    public void hideBattleForm(){
        this.battleForm.setVisible(false);
        this.battleForm.updateLabels(player, enemy);
    }
    
    public void showBattleForm(){
        this.battleForm.setVisible(true);
        this.battleForm.updateLabels(player, enemy);
    }
    
    public static double calculateEquipmentDodgePenalty(double currentWeight) {
        double minWeight = 6.0;
        double maxWeight = 80.0;
        double maxPenalty = 0.3;
        if (currentWeight <= minWeight) {
            return 0.0;
        }
        if (currentWeight >= maxWeight) {
            return maxPenalty;
        }
        double penaltyPerKg = maxPenalty / (maxWeight - minWeight);
        return (currentWeight - minWeight) * penaltyPerKg;
    }
    
    public void handlePlayerAttackAction() {
        AttackVariantsDialog attackVariantDialog = new AttackVariantsDialog(battleForm, true);
        attackVariantDialog.setVisible(true);
        
        EntityActionType playerAttackType = attackVariantDialog.getAttackType();
        
        if(playerAttackType == EntityActionType.LIGHT_ATTACK){
            handlePlayerLightAttack();
        } else if (playerAttackType == EntityActionType.HEAVY_ATTACK){
            handlePlayerHeavyAttack();
        }
    }
    
    private EntityActionType chooseEnemyActionForPlayersAttack() {
        EntityActionType[] actions = {
            EntityActionType.DODGE,
            EntityActionType.BLOCK,
            enemy.getPattern()[currentEnemyActionPattern],
            enemy.getPattern()[currentEnemyActionPattern]
        };
        int idx = new java.util.Random().nextInt(actions.length);
        if (idx == 2 || idx == 3) {
            currentEnemyActionPattern++;
            if (currentEnemyActionPattern >= enemy.getPattern().length) {
                currentEnemyActionPattern = 0;
            }
        }
        EntityActionType randomAction = actions[idx];
        return randomAction;
    }
    
    private void handlePlayerLightAttack() {
        EntityActionType enemyActionForPlayersAttack = chooseEnemyActionForPlayersAttack();
        
        if (player.getStamina() < 5){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 5);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        player.setStamina(player.getStamina() - 5);
        
        player.getSelectedWeapon().setDurability(player.getSelectedWeapon().getDurability() - 2);
        player.getSelectedWeapon().checkStatus();
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() + calculateEquipmentDodgePenalty(player.getTotalEquipmentWeight());
            if(Math.random() < enemyDodgePossibility){
                String logPart = enemy.getName() + " dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to dodge!";
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() + 0.15;
            if(Math.random() < enemyBlockPossibility){
                String logPart = enemy.getName() + " blocked your light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to block!";
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            String logPart = "Both decided to use light attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            if (Math.random() < player.getCritP()){
                String logPart1 = enemy.getName() + " used heavy attack, but your light attack was faster!";
                String logPart2 = "You landed critical hit on the " + enemy.getName() + " when he tried to use heavy attack!";
                String logPart3 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                battleForm.appendToLogArea(logPart1);
                battleForm.appendToLogArea(logPart2);
                battleForm.appendToLogArea(logPart3);
            } else {
                String logPart1 = enemy.getName() + " used heavy attack, but your light attack was faster!";
                String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart1);
                battleForm.appendToLogArea(logPart2);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    private void handlePlayerHeavyAttack() {
        EntityActionType enemyActionForPlayersAttack = chooseEnemyActionForPlayersAttack();
        
        if (player.getStamina() < 10){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 10);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        player.setStamina(player.getStamina() - 10);
        
        player.getSelectedWeapon().setDurability(player.getSelectedWeapon().getDurability() - 4);
        player.getSelectedWeapon().checkStatus();
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() + 0.1 + calculateEquipmentDodgePenalty(player.getTotalEquipmentWeight());
            if(Math.random() < enemyDodgePossibility){
                String logPart = enemy.getName() + " dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to dodge!";
                    String logPart2 = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() - 0.05;
            if(Math.random() < enemyBlockPossibility){
                String logPart = enemy.getName() + " blocked your heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to block!";
                    String logPart2 = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            String logPart = "Both decided to use heavy attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You used heavy attack but " + enemy.getName() + " light attack was faster!" + "\n" + "You took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerBlockAction() {
        
        if (player.getStamina() < 10){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 10);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        EntityActionType enemyActionForPlayersBlock = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        player.setStamina(player.getStamina() - 10);
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        if (enemyActionForPlayersBlock == EntityActionType.HEAVY_ATTACK){
            double playerBlockPossibility = player.getBlockP() - 0.05;
            if(Math.random() < playerBlockPossibility){

                if(player.hasCounterAttack() && player.getStamina() >= 5 && player.getTotalEquipmentWeight() < player.getBearableWeight() * 1.5){
                    String logPart1 = "You blocked " + enemy.getName() + " heavy attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.3));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = "You blocked " + enemy.getName() + " heavy attack!";
                    battleForm.appendToLogArea(logPart);
                }
                
            } else {
                player.takeDamage((int) (enemy.getDamage() * 1.2));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersBlock == EntityActionType.LIGHT_ATTACK){
            double playerBlockPossibility = player.getBlockP() + 0.1;
            if(Math.random() < playerBlockPossibility){
                
                if(player.hasCounterAttack() && player.getStamina() >= 5 && player.getTotalEquipmentWeight() < player.getBearableWeight() * 1.5){
                    String logPart1 = "You blocked " + enemy.getName() + " light attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.2));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    player.takeDamage((int) (enemy.getDamage() * 0.1));
                    String logPart = "You blocked " + enemy.getName() + " light attack!";
                    battleForm.appendToLogArea(logPart);
                }
                
            } else {
                player.takeDamage((int) (enemy.getDamage() * 0.8));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerDodgeAction() {
        EntityActionType enemyActionForPlayersDodge = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        player.setStamina(player.getStamina() + 5);
        if (player.getStamina() >= player.getMaxStamina()){
            player.setStamina(player.getMaxStamina());
        }
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            double playerDodgePossibility = player.getDodgeP() + 0.1;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged " + enemy.getName() + " heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                player.takeDamage((int) (enemy.getDamage() * 1.2 * 1.5));
                String logPart = "You couldn't dodge and took " + (int)(enemy.getDamage() * 1.2 * 1.5 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            double playerDodgePossibility = player.getDodgeP() - 0.05;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged " + enemy.getName() + " light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                player.takeDamage((int) (enemy.getDamage() * 0.8 * 1.5));
                String logPart = "You couldn't dodge and took " + (int)(enemy.getDamage() * 0.8 * 1.5 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }
    
    private void checkPlayerStamina(){
        battleForm.updateButtonAvaibility(player);
    }
 
    private void checkWinLoseConditions(){
        if(enemy.getHealth() <= 0){
            FightWinDialog winDialog = new FightWinDialog(battleForm, true);
            winDialog.setVisible(true);
            
            processEnemyItemDrop();

            battleForm.dispose();
            
            player.setStamina(player.getMaxStamina());
            
            player.addCurrentSoulsAmount(enemy.getSouls());
            
            if(enemy instanceof Boss){
                player.setRepairComponents(player.getRepairComponents() + 12);
            } else {
                player.setRepairComponents(player.getRepairComponents() + 6);
            }
            
            this.poisonDuration = 0;
            this.poison = null;
            
            if(enemy instanceof Boss){
                GUIandLogicIntermediary.autoSave(player);
            }
            
            GUIandLogicIntermediary.showNavigationForm();
        } else if (player.getHp() <= 0){
            battleForm.setVisible(false);
            FightLoseForm loseForm = new FightLoseForm();
            loseForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loseForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    battleForm.dispose();
                }
            });
            loseForm.setVisible(true);
        }
    }
    
    private void processEnemyItemDrop() {
        if (enemy instanceof Boss){
            
            ArrayList<Equipment> foundItems = processBossItemDrop(player.getCurrentRoom().getFloor());
            BossItemDropDialog bossItemDropDialog = new BossItemDropDialog(null, true, foundItems);
            bossItemDropDialog.setVisible(true);
            
        } else if (Math.random() <= 0.7){
            
            Potion foundItem = processDefaultEnemyItemDrop();
            DefaultEnemyDropDialog defaultEnemyDropDialog = new DefaultEnemyDropDialog(null, true, foundItem);
            defaultEnemyDropDialog.setVisible(true);
            
        }
    }
    
    private static Potion processDefaultEnemyItemDrop() {
        Potion foundItem = null;
        boolean foundBomb = false;
        boolean foundStamina = false;
        boolean foundPoison = false;
        if (Math.random() < 0.20) {
            foundBomb = true;
        }
        if (Math.random() < 0.35) {
            foundStamina = true;
        }
        if (Math.random() < 0.5) {
            foundPoison = true;
        }

        ArrayList<String> foundTypes = new ArrayList<>();
        if (foundBomb) foundTypes.add("Bomb");
        if (foundStamina) foundTypes.add("StaminaPotion");
        if (foundPoison) foundTypes.add("PoisonPotion");

        if (foundTypes.isEmpty()) {
            foundTypes.add("PoisonPotion");
        }

        while (foundTypes.size() > 1) {
            foundTypes.remove((int)(Math.random() * foundTypes.size()));
        }

        String type = foundTypes.get(0);
        foundItem = createPotionByType(type);
        
        addItemToInventory(foundItem);
        
        return foundItem;
    }
    
    private static void addItemToInventory(Potion item) {
        Player player = Player.getInstance();
        player.addItemToInventory(item);
    }

    private static Potion createPotionByType(String type) {
        switch (type) {
            case "Bomb":
                return new Bomb();
            case "StaminaPotion":
                return new StaminaPotion();
            case "PoisonPotion":
                return new Poison();
            default:
                throw new IllegalArgumentException("Unknown potion type!");
        }
    }

    void handlePlayerSkipAction() {
        EntityActionType enemyActionForPlayersDodge = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        
        player.setStamina(player.getStamina() + 10);
        
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 1.2));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerUsingBomb(Bomb bomb) {
        enemy.takeBombDamage(bomb);
        
        String logPart = "You used bomb; " + enemy.getName() + " took " + bomb.getDamage() + " damage!";
        battleForm.appendToLogArea(logPart);
        String logPart1 = enemy.getName() + " skips turn because of bomb!";
        battleForm.appendToLogArea(logPart1);
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerUsingPoison(Poison poison) {
        if (this.poison == null){
            if (enemy instanceof Skeleton || enemy instanceof SirenOfOblivion){
                String logPart = "You used poison but " + enemy.getName() + " is immune to it!";
                battleForm.appendToLogArea(logPart);
            } else {
                this.poisonDuration = poison.getDuration() - 1;
                this.poison = poison;
                enemy.takePoisonDamage(poison);
                String logPart = "You used poison! " + enemy.getName() + " took " + poison.getDamage() + " damage from poison!";
                battleForm.appendToLogArea(logPart);
                String logPart1 = enemy.getName() + " skips turn because of poison!";
                battleForm.appendToLogArea(logPart1);
                checkWinLoseConditions();
            }
            Player.getInstance().getInventory().remove((Poison) poison);
        } else {
            PotionAlreadyUsedDialog potionAlreadyUsedDialog = new PotionAlreadyUsedDialog(null,true);
            potionAlreadyUsedDialog.setVisible(true);
        }
    }
    
    void checkPoison(){
        if (this.poisonDuration != 0){
            this.poisonDuration--;
            enemy.takePoisonDamage(this.poison);
            String logPart = enemy.getName() + " took " + poison.getDamage() + " damage from poison!";
            battleForm.appendToLogArea(logPart);
        } else {
            this.poisonDuration = 0;
            this.poison = null;
        }
    }

    Enemy getEnemy() {
        return this.enemy;
    }

    boolean isPoisoned() {
        boolean isPoisoned = false;
        if (this.poison == null){
            isPoisoned = false;
        } else if (this.poison != null){
            isPoisoned = true;
        }
        return isPoisoned;
    }

    public static ArrayList<Equipment> processBossItemDrop(int floor) {
        ArrayList<Equipment> foundItems = new ArrayList<>();
        Random random = new Random();

        ArrayList<String> possibleTypes = new ArrayList<>(Arrays.asList(
                "Bomb", "PoisonPotion", "StaminaPotion",
                "LightArmor", "TrooperArmor", "HeavyArmor",
                "Sword", "Bow", "Spear", "Hammer", "Axe"
        ));

        boolean giveArmor = random.nextBoolean();

        String chosenType = null;
        if (giveArmor) {
            String[] armorTypes = {"LightArmor", "TrooperArmor", "HeavyArmor"};
            chosenType = armorTypes[random.nextInt(armorTypes.length)];
        } else {
            String[] weaponTypes = {"Sword", "Bow", "Spear", "Hammer", "Axe"};
            chosenType = weaponTypes[random.nextInt(weaponTypes.length)];
        }
        
        foundItems.add(getEquipmentSampleByTypeAndFloor(chosenType, floor, random));
        
        possibleTypes.remove(chosenType);

        int totalItems = 3;
        while (foundItems.size() < totalItems && !possibleTypes.isEmpty()) {
            int idx = random.nextInt(possibleTypes.size());
            String nextType = possibleTypes.remove(idx);
            foundItems.add(getEquipmentSampleByTypeAndFloor(nextType, floor, random));
        }

        addItemsToInventory(foundItems);
        
        return foundItems;
    }

    private static Equipment getEquipmentSampleByTypeAndFloor(String type, int floor, Random random) {
        int idxInZone = getRandomIndexForFloor(floor, random);
        switch (type) {
            case "LightArmor":
                return new LightArmor(ArmorStorage.lightArmor.get(idxInZone));
            case "TrooperArmor":
                return new TrooperArmor(ArmorStorage.trooperArmor.get(idxInZone));
            case "HeavyArmor":
                return new HeavyArmor(ArmorStorage.heavyArmor.get(idxInZone));
            case "Sword":
                return new Sword(WeaponsStorage.swords.get(idxInZone));
            case "Bow":
                return new Bow(WeaponsStorage.bows.get(idxInZone));
            case "Spear":
                return new Spear(WeaponsStorage.spears.get(idxInZone));
            case "Hammer":
                return new Hammer(WeaponsStorage.hammers.get(idxInZone));
            case "Axe":
                return new Axe(WeaponsStorage.axes.get(idxInZone));
            case "Bomb":
                return new Bomb();
            case "PoisonPotion":
                return new Poison();
            case "StaminaPotion":
                return new StaminaPotion();
            default:
                throw new IllegalArgumentException("Unknown equipment type: " + type);
        }
    }

    private static int getRandomIndexForFloor(int floor, Random random) {
        if (floor <= 3) {
            return random.nextInt(5);
        } else if (floor <= 5) {
            return 5 + random.nextInt(5);
        } else {
            return 10 + random.nextInt(5);
        }
    }
    
    private static void addItemsToInventory(List<? extends Equipment> items) {
        Player player = Player.getInstance();
        for (Equipment e : items) {
            player.addItemToInventory(e);
        }
    }

}