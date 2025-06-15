/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 *
 * @author Arseniy
 */
public class Sword extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public Sword(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 11_53_15.png";
    }
    
    public Sword(Sword other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 11_53_15.png";
    }
    
}