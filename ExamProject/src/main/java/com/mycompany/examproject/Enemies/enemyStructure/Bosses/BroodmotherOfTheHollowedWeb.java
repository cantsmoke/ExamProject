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
public class BroodmotherOfTheHollowedWeb extends Boss{
    
    private EntityActionType[] pattern;
    private String iconSource;
    
    public BroodmotherOfTheHollowedWeb(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 4 июн. 2025 г., 17_42_12.png";
    }
    
    @Override
    public String getIconSource(){
        return this.iconSource;
    }
    
}
