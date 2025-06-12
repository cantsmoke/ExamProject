/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import com.mycompany.examproject.Player;
import javax.swing.JOptionPane;

/**
 *
 * @author Arseniy
 */
public abstract class Weapon implements Equipment{
    protected String name;
    protected int weight;
    protected int damage;
    protected int maxDamage;
    protected int durability;
    protected int maxDurability;
    protected boolean isBroken = false;
    
    protected boolean isSelected = false;
    
    protected boolean wasStatusWindowShowed = false;
    
    protected String imageURL;

    public Weapon(String name, int weight, int damage, int durability) {
        this.name = name;
        this.weight = weight;
        this.damage = damage;
        this.maxDamage = damage;
        this.durability = durability;
        this.maxDurability = durability;
    }
    
    public boolean isBroken() {
        return isBroken;
    }
    
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getDamage(){
        return this.damage;
    }
    
    public int getDurability(){
        return this.durability;
    }
    
    public void setDurability(int newDurability){
        this.durability = newDurability;
    }
    
    public int getMaxDurability(){
        return this.maxDurability;
    }
    
    public String getImageUrl() {
        return imageURL;
    }
    
    @Override
    public String toString() {
        String result = name;
        if (isBroken) result += " (b)";
        if (isSelected) result += " (***)";
        return result;
    }
    
    @Override
    public void checkStatus(){
        if(this.durability <= 0){
            this.durability = 0;
            this.isBroken = true;
            if(!wasStatusWindowShowed){
                JOptionPane.showMessageDialog(null, "Weapon broke!");
                this.wasStatusWindowShowed = true;
            }
            this.damage = 0;
        }
    }
    
    public void repair(){
        this.durability = this.maxDurability;
        this.damage = this.maxDamage;
    }
    
}