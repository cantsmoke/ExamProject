/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;

/**
 *
 * @author Arseniy
 */
public class Poison extends Potion{

    private String imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 14 июн. 2025 г., 17_07_56 (1).png";
    
    private int damage;
    private int duration;
    
    public Poison() {
        super("Poison", "Poisons enemy.");
        this.damage = 5 + Player.getInstance().getCurrentRoom().getFloor();
        this.duration = 5 + (Player.getInstance().getCurrentRoom().getFloor() - 3);
    }
    
    public String getImageURL(){
        return this.imageURL;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    public int getDuration(){
        return this.duration;
    }
    
    @Override
    public String toString() {
        String result = name + "(Damage: " + damage + "; Duration: " + duration + ")";
        return result;
    }

    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
