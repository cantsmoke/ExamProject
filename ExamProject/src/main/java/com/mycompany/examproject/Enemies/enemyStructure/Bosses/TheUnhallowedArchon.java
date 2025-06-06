/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure.Bosses;

import com.mycompany.examproject.EntityActionType;

/**
 *
 * @author Arseniy
 */
public class TheUnhallowedArchon extends Boss{
    
    private EntityActionType[] pattern;
    private String iconSource;
    
    public TheUnhallowedArchon(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] { // заменить на адаптивную систему только для него
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 4 июн. 2025 г., 17_52_25.png";
    }
    
    @Override
    public String getIconSource(){
        return this.iconSource;
    }
    
}