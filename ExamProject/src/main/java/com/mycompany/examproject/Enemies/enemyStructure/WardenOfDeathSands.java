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
import com.mycompany.examproject.Items.Weapons.Sword;

/**
 * Босс "WardenOfDeathSands" — Страж Песков Смерти. 
 * Обитает в проклятых пустынных залах, где время и плоть рассыпаются в прах.
 *
 * <p>Наследует {@link Boss} и определяет собственные модификаторы урона в зависимости от типа оружия:</p>
 * <ul>
 *     <li>{@link Bow}, {@link Hammer} — пониженный урон.</li>
 *     <li>{@link Sword} — повышенный урон.</li>
 * </ul>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Шанс уклонения — 50%.</li>
 *     <li>Шанс блока — 40%.</li>
 *     <li>Боевой паттерн: тяжёлая — лёгкая — лёгкая — тяжёлая атака.</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * WardenOfDeathSands warden = new WardenOfDeathSands("Warden of Death Sands", 300, 100, 7);
 * }</pre>
 *
 * @author Arseniy
 */
public class WardenOfDeathSands extends Boss {

    /** Шаблон атак босса. */
    private EntityActionType[] pattern;

    /** Иконка босса. */
    private String iconSource;

    /** Вероятность уклонения. */
    private double dodgeP;

    /** Вероятность блока. */
    private double blockP;

    /**
     * Конструктор создания босса Warden of Death Sands.
     *
     * @param name имя босса
     * @param health количество здоровья
     * @param damage урон
     * @param floorNum этаж, на котором появляется
     */
    public WardenOfDeathSands(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17 (1).png";
        this.dodgeP = 0.5;
        this.blockP = 0.4;
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
     * Обработка тяжёлого урона с модификаторами от оружия.
     * 
     * @param selectedWeapon оружие
     * @param baseDamage базовый урон
     * @return строка отчёта о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Hammer) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка лёгкого урона с модификаторами от оружия.
     * 
     * @param selectedWeapon оружие
     * @param baseDamage базовый урон
     * @return строка отчёта о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Hammer) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}