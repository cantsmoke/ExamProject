/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Spear;
import com.mycompany.examproject.Items.Weapons.Sword;

/**
 * Враг "InfectedHound" — Заражённый Пёс.
 * 
 * <p>Шанс уклонения выше, шанс блока низкий.</p>
 * 
 * <p>Шаблон атак: тяжёлый — лёгкий — лёгкий — тяжёлый — лёгкий.</p>
 * 
 * <p>Урон варьируется в зависимости от типа оружия.</p>
 * 
 * @author Arseniy
 */
public class InfectedHound extends Enemy implements Hound {

    private static final EntityActionType[] defaultPattern = new EntityActionType[] {
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK
    };

    private EntityActionType[] pattern;
    private String iconSource;
    private double dodgeP;
    private double blockP;

    public InfectedHound(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_30_37.png";
        this.dodgeP = 0.6;
        this.blockP = 0.2;
    }

    @Override
    public String getIconSource() {
        return iconSource;
    }

    @Override
    public void setIconSource(String url) {
        this.iconSource = url;
    }

    @Override
    public void setPattern(EntityActionType[] newPattern) {
        this.pattern = newPattern;
    }

    @Override
    public EntityActionType[] getPattern() {
        return pattern;  // Возвращаем текущий паттерн, а не всегда дефолтный
    }

    @Override
    public double getDodgeP() {
        return dodgeP;
    }

    @Override
    public double getBlockP() {
        return blockP;
    }

    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.3;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.2;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public int getSouls() {
        return 100;
    }
}