/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure.Bosses;

import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.EntityActionType;

/**
 *
 * @author Arseniy
 */
public class Boss extends Enemy{

    public Boss(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
    }

    @Override
    public String getIconSource() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setPattern(EntityActionType[] newPattern) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setIconSource(String url) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
