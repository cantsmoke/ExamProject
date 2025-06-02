/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public class Consumable extends Item {
    private String effect; // E.g., "heal 20 HP"

    public Consumable(String name, int weight, String effect, String description) {
        super(name, ItemType.CONSUMABLE, 1, weight, description);
        this.effect = effect;
    }

    public String getEffect() { return effect; }
}