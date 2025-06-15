/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
/**
 *
 * @author Arseniy
 */
public class KingSkeleton extends Enemy implements Skeleton {
    
    private static EntityActionType[] defaultPattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
    private EntityActionType[] pattern;
    private String iconSource;
    
    private double dodgeP;
    private double blockP;
    
    public KingSkeleton(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_10_31.png";
        
        this.dodgeP = 0.4;
        this.blockP = 0.7;
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

    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
    
    @Override
    public int getSouls() {
        return 200;
    }
    
}