/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;

/**
 *
 * @author Arseniy
 */
public class Spear extends Weapon{
    
    public Spear(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 13_45_57 (1).png";
    }
    
    public Spear(Spear other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 13_45_57 (1).png";
    }
    
}