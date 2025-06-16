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
 * Босс "SirenOfOblivion" — обманчивая и смертоносная сирена, скрывающаяся во тьме.
 * Характеризуется высокой скоростью (высокий шанс уклонения) и смертоносной ловкостью.
 *
 * <p>Наследует {@link Boss} и реализует собственные модификаторы урона в зависимости от типа оружия:</p>
 * <ul>
 *     <li>{@link Spear} — увеличенный урон.</li>
 *     <li>{@link Axe}, {@link Hammer} — сниженный урон.</li>
 * </ul>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Очень высокий шанс уклонения — 90%.</li>
 *     <li>Низкий шанс блока — 40%.</li>
 *     <li>Боевой паттерн — 3 лёгкие атаки и 1 тяжёлая.</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * SirenOfOblivion siren = new SirenOfOblivion("Siren of Oblivion", 250, 70, 5);
 * }</pre>
 *
 * @author Arseniy
 */
public class SirenOfOblivion extends Boss {

    /** Массив действий босса в бою. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Сирены Забвения.
     *
     * @param name имя босса
     * @param health здоровье
     * @param damage урон
     * @param floorNum номер этажа, где встречается босс
     */
    public SirenOfOblivion(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17_14_31.png";
        this.dodgeP = 0.9;
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
     * Обработка получения урона от тяжёлой атаки.
     * Урон увеличивается при использовании {@link Spear}, снижается при использовании {@link Axe} или {@link Hammer}.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием нанесённого урона
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Hammer) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка получения урона от лёгкой атаки.
     * Урон увеличивается при использовании {@link Spear}, снижается при использовании {@link Axe} или {@link Hammer}.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием нанесённого урона
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Hammer) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}