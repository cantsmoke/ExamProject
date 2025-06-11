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
public class BlackSkeleton extends Enemy implements Skeleton {
    
    private static EntityActionType[] defaultPattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
    private EntityActionType[] pattern;
    private String iconSource;
    
    private double dodgeP;
    private double blockP;
    
    public BlackSkeleton(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "C:\\Users\\Arseniy\\Downloads\\ChatGPT Image 4 июн. 2025 г., 14_08_29.png";
        
        this.dodgeP = 0.15;
        this.blockP = 0.5;
    }
    
    @Override
    public String getIconSource(){
        return this.iconSource;
    }
    
    @Override
    public void setIconSource(String url) {
        this.iconSource = url;
    }

    @Override
    public void setPattern(EntityActionType[] newPattern){
        this.pattern = newPattern;
    }
    
    @Override
    public EntityActionType[] getPattern() {
        return defaultPattern;
    }
    
    @Override
    public double getDodgeP(){
        return this.dodgeP;
    }
    
    @Override
    public double getBlockP(){
        return this.blockP;
    }
    
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }
    
}