/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.GUI.HealthFullOrNoEstusDialog;
import com.mycompany.examproject.GUI.StaminaIsFullDialog;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Arseniy
 */
public class StaminaPotion extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String size;
    private int staminaRegenAmount;
    
    private String imageURL;
    
    public StaminaPotion() {
        super("Stamina potion", "Regenerates stamina.");
        
        if (new Random().nextBoolean()) {
            this.size = "Big";
            this.staminaRegenAmount = 30;
            imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_27_27.png";
        } else {
            this.size = "Small";
            this.staminaRegenAmount = 15;
            imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_25_39.png";
        }
    }
    
    public String getSize() {
        return size;
    }
    
    public int getStaminaRegenAmount(){
        return this.staminaRegenAmount;
    }
    
    public String getImageURL(){
        return this.imageURL;
    }
    
    public void addStamina(Player player) {
        if (player.getStamina() != player.getMaxStamina()){
            int newStamina = (int) (player.getStamina() + this.staminaRegenAmount);
            if (newStamina > player.getMaxStamina()){
                newStamina = player.getMaxStamina();
            }
            player.setStamina(newStamina);
            Player.getInstance().getInventory().remove(this);
        } else {
            StaminaIsFullDialog staminaIsFullDialog = new StaminaIsFullDialog(null, true);
            staminaIsFullDialog.setVisible(true);
        }
    }
    
    @Override
    public String toString() {
        String result = name + "("+ size +")";
        return result;
    }
    
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}