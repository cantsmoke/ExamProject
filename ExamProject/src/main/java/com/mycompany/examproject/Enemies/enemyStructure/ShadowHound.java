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
 * Враг "ShadowHound" — Теневой Пёс.
 * 
 * <p>Имеет высокий шанс уклонения и низкий шанс блока.</p>
 * 
 * <p>Шаблон атак: тяжёлый — лёгкий — лёгкий — тяжёлый — лёгкий.</p>
 * 
 * <p>Особенности урона:</p>
 * <ul>
 *     <li>Урон изменяется в зависимости от типа оружия.</li>
 * </ul>
 * 
 * @author Arseniy
 */
public class ShadowHound extends Enemy implements Hound {

    /** Стандартный шаблон действий. */
    private static final EntityActionType[] defaultPattern = new EntityActionType[] {
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK
    };

    /** Текущий шаблон действий. */
    private EntityActionType[] pattern;

    /** Путь к иконке. */
    private String iconSource;

    /** Шанс уклонения. */
    private double dodgeP;

    /** Шанс блока. */
    private double blockP;

    /**
     * Конструктор теневого пса.
     *
     * @param name имя врага
     * @param health здоровье
     * @param damage базовый урон
     * @param floorNum этаж появления
     */
    public ShadowHound(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_28_02.png";
        this.dodgeP = 0.5;
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

    /**
     * Возвращает стандартный шаблон.
     */
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.4;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.3;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    @Override
    public int getSouls() {
        return 70;
    }
}