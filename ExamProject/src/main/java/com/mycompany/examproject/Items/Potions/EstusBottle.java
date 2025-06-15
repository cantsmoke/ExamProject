/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.GUI.HealthFullOrNoEstusDialog;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Arseniy
 */

public class EstusBottle extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private double healRegenPercentage;   // Количество восстанавливаемого HP
    private int maxUses;      // Лимит глотков на обновление (как в Dark Souls)
    private int usesLeft;     // Осталось глотков
    
    private String imageURL = "C:\\Users\\Arseniy\\Downloads\\imgonline-com-ua-BrightnessContrast-DWdgxay1HD3Nqm.png";

    public EstusBottle() {
        super("Estus", "Regens your health.");
        this.healRegenPercentage = 0.25;
        this.maxUses = 10;
        this.usesLeft = 5;
    }

    public double getHealthRegenPercentage(){
        return this.healRegenPercentage;
    }
    
    public void refill() {
        this.usesLeft = maxUses;
    }

    public int getUsesLeft() { return usesLeft; }
    public int getMaxUses() { return maxUses; }
    
    @Override
    public String toString() {
        String result = name + "(" + usesLeft + "/" + maxUses + ")";
        return result;
    }
    
    public String getImageURL(){
        return this.imageURL;
    }

    public void heal(Player player) {
        if (player.getHp() != player.getMaxHp() && usesLeft != 0){
            int newHp = (int) (player.getHp() + player.getMaxHp() * healRegenPercentage);
            if (newHp > player.getMaxHp()){
                newHp = player.getMaxHp();
            }
            player.setHp(newHp);
            this.usesLeft--;
        } else {
            HealthFullOrNoEstusDialog healthFullOrNoEstusDialog = new HealthFullOrNoEstusDialog(null, true);
            healthFullOrNoEstusDialog.setVisible(true);
        }
    }
    
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}