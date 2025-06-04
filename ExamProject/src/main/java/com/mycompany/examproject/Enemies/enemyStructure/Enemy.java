/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;

/**
 *
 * @author Arseniy
 */
public abstract class Enemy {
    
    private String name;
    private int health;
    private int damage;

    public Enemy(String name, int health, int damage, int floorNum) {
        this.name = name;
        this.health = health + floorNum * 10; // усиливаем от этажа
        this.damage = damage + floorNum * 2;
    }
    
    // Геттеры
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    // Сеттеры
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
    
}