/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Spear;

/**
 * Босс "Executioner Of The Last Creed" — противник с высоким шансом уклонения и блока,
 * обладающий усиленной уязвимостью к молотам и слабостью против топоров и копий.
 *
 * <p>Наследует поведение {@link Boss}, имеет индивидуальный боевой паттерн, путь к иконке,
 * а также уникальные вероятности уклонения и блока.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Боевой паттерн в основном состоит из тяжёлых атак с редкими легкими вставками.</li>
 *     <li>Высокий шанс уклонения — 50%.</li>
 *     <li>Высокий шанс блока — 60%.</li>
 *     <li>Уязвимости к типам оружия:
 *         <ul>
 *             <li>{@link Axe} и {@link Spear} — ослабленный урон.</li>
 *             <li>{@link Hammer} — усиленный урон.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * ExecutionerOfTheLastCreed boss = new ExecutionerOfTheLastCreed("Executioner Of The Last Creed", 600, 80, 6);
 * }</pre>
 *
 * @author Arseniy
 */
public class ExecutionerOfTheLastCreed extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Executioner Of The Last Creed.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
    public ExecutionerOfTheLastCreed(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17_49_13.png";
        this.dodgeP = 0.5;
        this.blockP = 0.6;
    }

    /** {@inheritDoc} */
    @Override
    public EntityActionType[] getPattern() {
        return pattern;
    }

    /** {@inheritDoc} */
    @Override
    public String getIconSource() {
        return this.iconSource;
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
     * Логика получения урона при тяжёлой атаке. Урон зависит от типа оружия:
     * <ul>
     *     <li>{@link Hammer} наносит увеличенный урон.</li>
     *     <li>{@link Axe} и {@link Spear} — уменьшенный урон.</li>
     * </ul>
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Spear) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Логика получения урона при лёгкой атаке. Урон зависит от типа оружия:
     * <ul>
     *     <li>{@link Hammer} наносит увеличенный урон.</li>
     *     <li>{@link Axe} и {@link Spear} — уменьшенный урон.</li>
     * </ul>
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Spear) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}