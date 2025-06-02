/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public class Item {
    private String name;
    private ItemType type;
    private int durability;
    private int maxDurability;
    private int weight;
    private String description;

    public Item(String name, ItemType type, int durability, int weight, String description) {
        this.name = name;
        this.type = type;
        this.durability = durability;
        this.maxDurability = durability;
        this.weight = weight;
        this.description = description;
    }

    public String getName() { return name; }
    public ItemType getType() { return type; }
    public int getDurability() { return durability; }
    public int getWeight() { return weight; }
    public String getDescription() { return description; }
    public void reduceDurability(int amount) { 
        durability = Math.max(0, durability - amount); 
    }
    public void repair() { durability = maxDurability; }
    public boolean isBroken() { return durability <= 0; }
}