/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.Enemies.enemyStructure.PotionAlreadyUsedDialog;
import com.mycompany.examproject.Enemies.enemyStructure.SirenOfOblivion;
import com.mycompany.examproject.Enemies.enemyStructure.Skeleton;
import com.mycompany.examproject.GUI.AttackVariantsDialog;
import com.mycompany.examproject.GUI.BattleForm;
import com.mycompany.examproject.GUI.FightLoseForm;
import com.mycompany.examproject.GUI.FightWinDialog;
import com.mycompany.examproject.GUI.NotEnoughStaminaDialog;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.Poison;
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
        
//        Player.getInstance().addItemToInventory(new Poison());
//        Player.getInstance().addItemToInventory(new Poison());
//        
//        Player.getInstance().addItemToInventory(new Bomb());
//        Player.getInstance().addItemToInventory(new Bomb());
//        Player.getInstance().addItemToInventory(new Bomb());
//        Player.getInstance().addItemToInventory(new Bomb());
//        Player.getInstance().addItemToInventory(new Bomb());
        
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
            double enemyDodgePossibility = enemy.getDodgeP() - 0.1  + calculateEquipmentDodgePenalty(player.getTotalEquipmentWeight());
            if(Math.random() < enemyDodgePossibility){
                String logPart = "Enemy dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() + 0.1;
            if(Math.random() < enemyBlockPossibility){
                //String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                String logPart = "Enemy blocked your light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            String logPart = "Both decided to use light attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
            battleForm.appendToLogArea(logPart);
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
                String logPart = "Enemy dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() - 0.05;
            if(Math.random() < enemyBlockPossibility){
                //String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                String logPart = "Enemy blocked your heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            String logPart = "Both decided to use heavy attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You used heavy attack but enemy light attack was faster!" + "\n" + "You took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
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
                    String logPart1 = "You blocked enemy heavy attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.3));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    //player.takeDamage((int) (enemy.getDamage() * 0.2));
                    String logPart = "You blocked enemy heavy attack!"/* + " and took " + (int)(enemy.getDamage() * 0.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!"*/;
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
                    String logPart1 = "You blocked enemy light attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.2));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    player.takeDamage((int) (enemy.getDamage() * 0.1));
                    String logPart = "You blocked enemy light attack!"/* + " and took " + (int)(enemy.getDamage() * 0.1 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!"*/;
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
                String logPart = "You dodged enemy heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                player.takeDamage((int) (enemy.getDamage() * 1.2 * 1.5));
                String logPart = "You couldn't dodge and took " + (int)(enemy.getDamage() * 1.2 * 1.5 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            double playerDodgePossibility = player.getDodgeP() - 0.05;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged enemy light attack!";
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
            
            GUIandLogicIntermediary.showNavigationForm();
        } else if (player.getHp() <= 0){
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
        
        String logPart = "You used bomb; enemy took " + bomb.getDamage() + " damage!";
        battleForm.appendToLogArea(logPart);
        String logPart1 = "Enemy skips turn because of bomb!";
        battleForm.appendToLogArea(logPart1);
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerUsingPoison(Poison poison) {
        if (this.poison == null){
            if (enemy instanceof Skeleton || enemy instanceof SirenOfOblivion){
                String logPart = "You used poison but enemy is immune to it!";
                battleForm.appendToLogArea(logPart);
            } else {
                this.poisonDuration = poison.getDuration() - 1;
                this.poison = poison;
                enemy.takePoisonDamage(poison);
                String logPart = "You used poison! Enemy took " + poison.getDamage() + " damage from poison!";
                battleForm.appendToLogArea(logPart);
                String logPart1 = "Enemy skips turn because of poison!";
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
            String logPart = "Enemy took " + poison.getDamage() + " damage from poison!";
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
    
}