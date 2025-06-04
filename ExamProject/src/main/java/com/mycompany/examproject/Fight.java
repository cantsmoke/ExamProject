/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.GUI.BattleForm;

/**
 *
 * @author Arseniy
 */
public class Fight {
    
    Player player;
    Enemy enemy;
    
    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        BattleForm battleForm = new BattleForm();
        battleForm.updateLabels(player, enemy);
        battleForm.setVisible(true);
    }
    
}
