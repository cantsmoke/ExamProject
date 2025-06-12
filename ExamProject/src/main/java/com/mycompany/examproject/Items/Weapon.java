/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public abstract class Weapon implements Equipment{
    protected String name;
    protected int weight;
    protected int damage;
    protected int durability;
    protected int maxDurability;
    
    protected String imageURL;

    public Weapon(String name, int weight, int damage, int durability) {
        this.name = name;
        this.weight = weight;
        this.damage = damage;
        this.durability = durability;
        this.maxDurability = durability;
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
        return name;
    }
    
}