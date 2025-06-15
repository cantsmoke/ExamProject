/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Armors;

import com.mycompany.examproject.Items.Armor;
import java.io.Serializable;

/**
 *
 * @author Arseniy
 */
public class TrooperArmor extends Armor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public TrooperArmor(String name, int weight, double damageReduction, int durability) {
        super(name, weight, damageReduction, durability);
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 13_29_40.png";
    }
    
    public TrooperArmor(TrooperArmor other) {
        super(other.getName(), other.getWeight(), other.getDamageReduction(), other.getDurability());
        this.imageURL = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 12 июн. 2025 г., 13_29_40.png";
    }
    
}