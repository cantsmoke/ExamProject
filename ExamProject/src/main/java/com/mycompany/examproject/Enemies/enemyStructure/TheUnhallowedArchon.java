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
 * Финальный босс "TheUnhallowedArchon" — архонт-запретник, воплощение чистого презрения к жизни.
 * Представляет собой высшую угрозу: почти неуязвим из-за колоссальных вероятностей защиты и уклонения.
 *
 * <p>Наследует {@link Boss} и реализует уникальные штрафы к урону от большинства типов оружия:</p>
 * <ul>
 *     <li>{@link Spear}, {@link Hammer}, {@link Bow} — пониженный урон как для лёгких, так и для тяжёлых атак.</li>
 * </ul>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Чрезвычайно высокий шанс уклонения — 95%.</li>
 *     <li>Чрезвычайно высокий шанс блока — 95%.</li>
 *     <li>Боевой паттерн: две лёгкие атаки, затем тяжёлая.</li>
 *     <li>Планируется замена паттерна на адаптивную боевую систему.</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * TheUnhallowedArchon archon = new TheUnhallowedArchon("The Unhallowed Archon", 500, 120, 10);
 * }</pre>
 *
 * @author Arseniy
 */
public class TheUnhallowedArchon extends Boss {

    /** Боевой паттерн Архонта. В будущем — адаптивный ИИ. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор создания Архонта.
     *
     * @param name имя босса
     * @param health здоровье
     * @param damage урон
     * @param floorNum номер этажа, где встречается
     */
    public TheUnhallowedArchon(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17_52_25.png";
        this.dodgeP = 0.95;
        this.blockP = 0.95;
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
     * Обработка получения урона от тяжёлой атаки.
     * Урон снижается, если используется {@link Spear}, {@link Hammer} или {@link Bow}.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием нанесённого урона
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Spear || selectedWeapon instanceof Hammer || selectedWeapon instanceof Bow) {
            damage /= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка получения урона от лёгкой атаки.
     * Урон снижается, если используется {@link Spear}, {@link Hammer} или {@link Bow}.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием нанесённого урона
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Spear || selectedWeapon instanceof Hammer || selectedWeapon instanceof Bow) {
            damage /= 1.1;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}