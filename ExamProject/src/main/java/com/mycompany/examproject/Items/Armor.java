/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import com.mycompany.examproject.GUI.ArmorBrokeDialog;
import java.io.Serializable;
/**
 *
 * @author Arseniy
 */
public abstract class Armor implements Equipment, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected int weight;
    protected double damageReduction;
    protected double maxDamageReduction;
    protected int durability;
    protected int maxDurability;
    protected boolean isBroken = false;
    protected boolean isSelected = false;
    protected boolean wasStatusWindowShowed = false;
    protected String imageURL;

    public Armor(String name, int weight, double damageReduction, int durability) {
        this.name = name;
        this.weight = weight;
        this.damageReduction = damageReduction;
        this.maxDamageReduction = damageReduction;
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

    public double getDamageReduction(){
        return this.damageReduction;
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
                ArmorBrokeDialog armorBrokeDialog = new ArmorBrokeDialog(null, true);
                armorBrokeDialog.setVisible(true);
                this.wasStatusWindowShowed = true;
            }
            this.damageReduction = 0;
        }
    }
    
    public void repair(){
        this.durability = this.maxDurability;
        this.damageReduction = this.maxDamageReduction;
        this.isBroken = false;
    }
    
}