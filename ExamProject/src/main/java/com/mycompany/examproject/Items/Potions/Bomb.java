/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;

/**
 *
 * @author Arseniy
 */
public class Bomb extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private int damage;
    
    private String imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_51_32 (1).png";
    
    public Bomb() {
        super("Bomb", "Deals damage to enemy.");
        this.damage = Player.getInstance().getCurrentRoom().getFloor() * 10 + 40;
    }
    
    public String getImageURL(){
        return this.imageURL;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    @Override
    public String toString() {
        String result = name + "(" + damage + ")";
        return result;
    }

    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}