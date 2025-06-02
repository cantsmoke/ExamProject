/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public class Armor extends Item {
    private int defense;

    public Armor(String name, int durability, int weight, int defense, String description) {
        super(name, ItemType.ARMOR, durability, weight, description);
        this.defense = defense;
    }

    public int getDefense() { return defense; }
}