/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.Poison;
import com.mycompany.examproject.Items.Weapon;

/**
 *
 * @author Arseniy
 */
public abstract class Enemy {
    
    String name;
    int health;
    private int maxHealth;
    private int damage;

    public Enemy(String name, int health, int damage, int floorNum) {
        this.name = name;
        this.health = health + floorNum * 10;
        this.maxHealth = this.health;
        this.damage = damage + floorNum * 2;
    }
    
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public abstract String getIconSource();
    public abstract void setIconSource(String url);
    
    public abstract void setPattern(EntityActionType[] newPattern);
    
    public abstract double getDodgeP();
    public abstract double getBlockP();
    
    public abstract EntityActionType[] getPattern();

    public abstract String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);
    public abstract String takeLightDamage(Weapon selectedWeapon, int baseDamage);
    
    public abstract int getSouls();

    public void takeBombDamage(Bomb bomb) {
        this.health -= bomb.getDamage();
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void takePoisonDamage(Poison poison) {
        this.health -= poison.getDamage();
        if (this.health < 0) {
            this.health = 0;
        }
    }
 
}