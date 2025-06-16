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
import com.mycompany.examproject.Items.Weapons.Spear;

/**
 * Враг "FireKnight" — Огненный Рыцарь.
 * 
 * <p>Агрессивный противник с неплохими шансами уклонения и блока.</p>
 * 
 * <p>Основные характеристики:</p>
 * <ul>
 *     <li>Шаблон атак: тяж — тяж — легк — тяж — легк.</li>
 *     <li>Шанс уклонения: 35%</li>
 *     <li>Шанс блока: 60%</li>
 *     <li>Награда за победу: 200 душ</li>
 * </ul>
 * 
 * <p>Особенности урона:</p>
 * <ul>
 *     <li>Урон слегка корректируется для разных типов атак.</li>
 * </ul>
 * 
 * @author Arseniy
 */
public class FireKnight extends Enemy implements Knight {

    /** Стандартный шаблон действий. */
    private static final EntityActionType[] defaultPattern = new EntityActionType[] {
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK
    };

    /** Текущий шаблон действий. */
    private EntityActionType[] pattern;

    /** Путь к иконке сущности. */
    private String iconSource;

    /** Шанс уклонения. */
    private double dodgeP;

    /** Шанс блока. */
    private double blockP;

    /**
     * Конструктор Огненного Рыцаря.
     *
     * @param name имя врага
     * @param health здоровье
     * @param damage базовый урон
     * @param floorNum этаж появления
     */
    public FireKnight(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_40_24.png";
        this.dodgeP = 0.35;
        this.blockP = 0.6;
    }

    /** {@inheritDoc} */
    @Override
    public String getIconSource() {
        return this.iconSource;
    }

    /** {@inheritDoc} */
    @Override
    public void setIconSource(String url) {
        this.iconSource = url;
    }

    /** {@inheritDoc} */
    @Override
    public void setPattern(EntityActionType[] newPattern) {
        this.pattern = newPattern;
    }

    /** {@inheritDoc} */
    @Override
    public EntityActionType[] getPattern() {
        return defaultPattern;
    }

    /** {@inheritDoc} */
    @Override
    public double getDodgeP() {
        return this.dodgeP;
    }

    /** {@inheritDoc} */
    @Override
    public double getBlockP() {
        return this.blockP;
    }

    /**
     * Возвращает стандартный шаблон действий.
     *
     * @return массив действий {@link EntityActionType}
     */
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    /**
     * Обработка тяжёлого урона.
     * <p>Урон слегка корректируется в зависимости от типа атаки.</p>
     *
     * @param selectedWeapon выбранное оружие
     * @param baseDamage базовый урон
     * @return сообщение с информацией о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка лёгкого урона.
     * <p>Урон слегка корректируется в зависимости от типа атаки.</p>
     *
     * @param selectedWeapon выбранное оружие
     * @param baseDamage базовый урон
     * @return сообщение с информацией о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Возвращает количество душ за убийство.
     *
     * @return количество душ
     */
    @Override
    public int getSouls() {
        return 200;
    }
}