/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public abstract class Armor implements Equipment{
    protected String name;
    protected int weight;
    protected double damageReduction;
    protected int durability;
    protected int maxDurability;
    
    protected String imageURL;

    public Armor(String name, int weight, double damageReduction, int durability) {
        this.name = name;
        this.weight = weight;
        this.damageReduction = damageReduction;
        this.durability = durability;
        this.maxDurability = durability;
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
        return name;
    }
    
}