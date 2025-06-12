/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.GUI.AttackVariantsDialog;
import com.mycompany.examproject.GUI.BattleForm;
import com.mycompany.examproject.GUI.FightLoseForm;
import com.mycompany.examproject.GUI.FightWinDialog;
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
    
    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        Random random = new Random();
        
        BattleForm battleForm = new BattleForm();
        battleForm.updateLabels(player, enemy);
        battleForm.setVisible(true);
        
        this.battleForm = battleForm;
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
            enemy.getPattern()[currentEnemyActionPattern]
        };
        int idx = new java.util.Random().nextInt(3);
        if (idx == 2) {
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
        player.setStamina(player.getStamina() - 5);
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() - 0.05;
            if(Math.random() < enemyDodgePossibility){
                String logPart = "Enemy dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 0.8));
                String logPart = "Enemy took " + player.getDamage() * 0.8 + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() + 0.1;
            if(Math.random() < enemyBlockPossibility){
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 0.2));
                String logPart = "Enemy blocked your attack!" + "\n" + "Enemy took " + (player.getDamage() * 0.2) + " damage!";
                battleForm.appendToLogArea(logPart);
            } else {
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 0.8));
                String logPart = "Enemy took " + player.getDamage() * 0.8 + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            String logPart = "Both decided to use light attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 0.8));
            String logPart = "Enemy used heavy attack but your light attack was faster!" + "\n" + "Enemy took " + (player.getDamage() * 0.8) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    private void handlePlayerHeavyAttack() {
        EntityActionType enemyActionForPlayersAttack = chooseEnemyActionForPlayersAttack();
         player.setStamina(player.getStamina() - 10);
         
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() + 0.1;
            if(Math.random() < enemyDodgePossibility){
                String logPart = "Enemy dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 1.2));
                String logPart = "Enemy took " + player.getDamage() * 1.2 + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() - 0.05;
            if(Math.random() < enemyBlockPossibility){
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 0.5));
                String logPart = "Enemy blocked your attack!" + "\n" + "Enemy took " + (player.getDamage() * 0.5) + " damage!";
                battleForm.appendToLogArea(logPart);
            } else {
                enemy.setHealth((int) (enemy.getHealth() - player.getDamage() * 1.2));
                String logPart = "Enemy took " + player.getDamage() * 1.2 + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            String logPart = "Both decided to use heavy attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.8));
            
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You used heavy attack but enemy light attack was faster!" + "\n" + "You took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    void handlePlayerBlockAction() {
        EntityActionType enemyActionForPlayersBlock = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        
        player.setStamina(player.getStamina() - 7);
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersBlock == EntityActionType.HEAVY_ATTACK){
            double playerBlockPossibility = player.getBlockP() - 0.05;
            if(Math.random() < playerBlockPossibility){
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.2));
                
                player.takeDamage((int) (enemy.getDamage() * 0.2));
                String logPart = "You blocked enemy heavy attack and took " + (int)(enemy.getDamage() * 0.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            } else {
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 1.2));
                
                player.takeDamage((int) (enemy.getDamage() * 1.2));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersBlock == EntityActionType.LIGHT_ATTACK){
            double playerBlockPossibility = player.getBlockP() + 0.1;
            if(Math.random() < playerBlockPossibility){
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.1));
                
                player.takeDamage((int) (enemy.getDamage() * 0.1));
                String logPart = "You blocked enemy light attack and took " + (int)(enemy.getDamage() * 0.1 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            } else {
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.8));
                
                player.takeDamage((int) (enemy.getDamage() * 0.8));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
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
        
        player.setStamina(player.getStamina() - 10);
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            double playerDodgePossibility = player.getDodgeP() + 0.1;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged enemy heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 1.2));
                
                player.takeDamage((int) (enemy.getDamage() * 1.2));
                String logPart = "You took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            double playerDodgePossibility = player.getDodgeP() - 0.05;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged enemy light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.8));
                
                player.takeDamage((int) (enemy.getDamage() * 0.8));
                String logPart = "You took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
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
        
        player.setStamina(player.getStamina() + 20);
        
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            //player.setHp((int) (player.getHp() - enemy.getDamage() * 1.2));
            
            player.takeDamage((int) (enemy.getDamage() * 1.2));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            //player.setHp((int) (player.getHp() - enemy.getDamage() * 0.8));
            
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }
    
}