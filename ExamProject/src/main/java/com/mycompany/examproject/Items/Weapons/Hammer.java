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
public class Hammer extends Weapon{
    
    public Hammer(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 12_02_48.png";
    }
    
    public Hammer(Hammer other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 12_02_48.png";
    }
    
}