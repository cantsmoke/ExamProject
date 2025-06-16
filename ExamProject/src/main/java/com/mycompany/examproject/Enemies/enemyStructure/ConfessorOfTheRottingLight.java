/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Spear;

/**
 * Босс "Confessor Of The Rotting Light" — противник со сбалансированными шансами блока и уклонения, 
 * обладающий усиленной уязвимостью к топорам и слабостью против копий и луков.
 *
 * <p>Наследует поведение {@link Boss}, имеет индивидуальный паттерн действий, иконку, вероятности уклонения и блока.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Боевой паттерн чередует тяжелые и легкие атаки.</li>
 *     <li>Средний шанс уклонения — 60%.</li>
 *     <li>Средний шанс блока — 60%.</li>
 *     <li>Уязвимости к типам оружия:
 *         <ul>
 *             <li>{@link Bow} и {@link Spear} — уменьшенный урон.</li>
 *             <li>{@link Axe} — увеличенный урон.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * ConfessorOfTheRottingLight boss = new ConfessorOfTheRottingLight("Confessor Of The Rotting Light", 550, 75, 5);
 * }</pre>
 *
 * @author Arseniy
 */
public class ConfessorOfTheRottingLight extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Confessor Of The Rotting Light.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
    public ConfessorOfTheRottingLight(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 18_34_45.png";
        this.dodgeP = 0.6;
        this.blockP = 0.6;
    }

    /** {@inheritDoc} */
    @Override
    public EntityActionType[] getPattern() {
        return pattern;
    }

    /** {@inheritDoc} */
    @Override
    public String getIconSource(){
        return this.iconSource;
    }

    /** {@inheritDoc} */
    @Override
    public double getDodgeP(){
        return this.dodgeP;
    }

    /** {@inheritDoc} */
    @Override
    public double getBlockP(){
        return this.blockP;
    }

    /**
     * Логика получения урона при тяжёлой атаке. Урон зависит от типа оружия.
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);
        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Spear) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Axe) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Логика получения урона при лёгкой атаке. Урон зависит от типа оружия.
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);
        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Spear) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Axe) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}