/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Spear;
/**
 *
 * @author Arseniy
 */
public class BroodmotherOfTheHollowedWeb extends Boss{
    
    private EntityActionType[] pattern;
    private String iconSource;
    
    private double dodgeP;
    private double blockP;
    
    public BroodmotherOfTheHollowedWeb(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17_42_12.png";
        
        this.dodgeP = 0.8;
        this.blockP = 0.5;
    }
    
    @Override
    public EntityActionType[] getPattern() {
        return pattern;
    }
    
    @Override
    public String getIconSource(){
        return this.iconSource;
    }
    
    @Override
    public double getDodgeP(){
        return this.dodgeP;
    }
    
    @Override
    public double getBlockP(){
        return this.blockP;
    }
    
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Hammer || selectedWeapon instanceof Axe) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Hammer || selectedWeapon instanceof Axe) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.4;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
    
}