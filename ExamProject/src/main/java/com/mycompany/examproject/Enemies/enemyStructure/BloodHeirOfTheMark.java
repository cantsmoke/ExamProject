/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Spear;
import com.mycompany.examproject.Items.Weapons.Sword;

/**
 * Босс "Blood Heir Of The Mark" — быстрый и агрессивный противник с уникальной системой сопротивляемости урону.
 * Использует чередование лёгких и тяжёлых атак в бою.
 *
 * <p>Наследует поведение {@link Boss}, имеет собственный паттерн действий, иконку, вероятности уклонения и блока.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Фиксированный боевой паттерн из чередующихся атак.</li>
 *     <li>Уникальные модификаторы урона в зависимости от типа оружия:</li>
 *     <li>{@link Sword} — повышенный урон;</li>
 *     <li>{@link Spear} и {@link Axe} — сниженный урон.</li>
 *     <li>Иконка задаётся через {@code iconSource} — путь к изображению.</li>
 *     <li>Средние вероятности уклонения и блока — по 40%.</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * BloodHeirOfTheMark boss = new BloodHeirOfTheMark("Blood Heir Of The Mark", 450, 60, 3);
 * }</pre>
 *
 * @author Arseniy
 */
public class BloodHeirOfTheMark extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Blood Heir Of The Mark.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
    public BloodHeirOfTheMark(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17.png";
        this.dodgeP = 0.4;
        this.blockP = 0.4;
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
        if (selectedWeapon instanceof Spear || selectedWeapon instanceof Axe) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Sword) {
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
        if (selectedWeapon instanceof Spear || selectedWeapon instanceof Axe) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}