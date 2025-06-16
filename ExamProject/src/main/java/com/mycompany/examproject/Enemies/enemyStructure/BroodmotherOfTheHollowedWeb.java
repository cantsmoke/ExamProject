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
 * Босс "Broodmother Of The Hollowed Web" — крайне ловкий противник с уклоном на уклонение и усиленные уязвимости к типам оружия.
 * Использует агрессивный боевой паттерн с доминированием тяжёлых атак.
 *
 * <p>Наследует поведение {@link Boss}, имеет собственный паттерн действий, иконку, вероятности уклонения и блока.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Преобладание тяжёлых атак в бою.</li>
 *     <li>Очень высокий шанс уклонения — 80%.</li>
 *     <li>Имеет модификаторы урона в зависимости от типа оружия:</li>
 *     <li>{@link Spear} — увеличенный урон.</li>
 *     <li>{@link Hammer} и {@link Axe} — пониженный урон.</li>
 *     <li>Иконка задаётся через {@code iconSource} — путь к изображению.</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * BroodmotherOfTheHollowedWeb boss = new BroodmotherOfTheHollowedWeb("Broodmother Of The Hollowed Web", 500, 65, 4);
 * }</pre>
 *
 * @author Arseniy
 */
public class BroodmotherOfTheHollowedWeb extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Broodmother Of The Hollowed Web.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
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
        if (selectedWeapon instanceof Hammer || selectedWeapon instanceof Axe) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.1;
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
        if (selectedWeapon instanceof Hammer || selectedWeapon instanceof Axe) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Spear) {
            damage *= 1.4;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}