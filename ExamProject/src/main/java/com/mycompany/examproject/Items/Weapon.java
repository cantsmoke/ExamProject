/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

/**
 *
 * @author Arseniy
 */
public class Weapon extends Item {
    private int damage;
    private double bossDamageBonus; // E.g., +10% damage against bosses

    public Weapon(String name, int durability, int weight, int damage, double bossDamageBonus, String description) {
        super(name, ItemType.WEAPON, durability, weight, description);
        this.damage = damage;
        this.bossDamageBonus = bossDamageBonus;
    }

    public int getDamage() { return damage; }
    public double getBossDamageBonus() { return bossDamageBonus; }
}