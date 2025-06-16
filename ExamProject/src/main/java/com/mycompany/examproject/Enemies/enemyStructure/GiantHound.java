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
import com.mycompany.examproject.Items.Weapons.Sword;

/**
 * Враг "GiantHound" — Гигантский Пёс.
 *
 * <p>Отличается высоким шансом уклонения и низким шансом блока.</p>
 *
 * <p>Шаблон атак: тяжёлый — лёгкий — лёгкий — тяжёлый — лёгкий.</p>
 *
 * <p>Урон варьируется в зависимости от типа оружия.</p>
 *
 * @author Arseniy
 */
public class GiantHound extends Enemy implements Hound {

    /**
     * Стандартный шаблон атак: тяжёлый — лёгкий — лёгкий — тяжёлый — лёгкий.
     */
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

    /**
     * Создаёт нового врага типа GiantHound.
     *
     * @param name     имя врага
     * @param health   количество здоровья
     * @param damage   базовый урон
     * @param floorNum номер этажа появления
     */
    public GiantHound(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_33_26.png";
        this.dodgeP = 0.7;
        this.blockP = 0.2;
    }

    /**
     * Возвращает путь к иконке врага.
     *
     * @return строка с URL изображения
     */
    @Override
    public String getIconSource() {
        return iconSource;
    }

    /**
     * Устанавливает путь к иконке врага.
     *
     * @param url новый путь к изображению
     */
    @Override
    public void setIconSource(String url) {
        this.iconSource = url;
    }

    /**
     * Устанавливает новый шаблон атак.
     *
     * @param newPattern массив с новыми действиями
     */
    @Override
    public void setPattern(EntityActionType[] newPattern) {
        this.pattern = newPattern;
    }

    /**
     * Возвращает текущий шаблон атак врага.
     *
     * @return массив EntityActionType
     */
    @Override
    public EntityActionType[] getPattern() {
        return pattern;
    }

    /**
     * Возвращает шанс уклонения.
     *
     * @return значение от 0 до 1
     */
    @Override
    public double getDodgeP() {
        return dodgeP;
    }

    /**
     * Возвращает шанс блока.
     *
     * @return значение от 0 до 1
     */
    @Override
    public double getBlockP() {
        return blockP;
    }

    /**
     * Возвращает стандартный шаблон атак.
     *
     * @return массив EntityActionType
     */
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    /**
     * Применяет урон от тяжёлой атаки.
     * <p>Множитель урона: 1.2. Модифицируется типом оружия:</p>
     * <ul>
     *     <li>Spear — урон уменьшается (делится на 1.1)</li>
     *     <li>Sword — урон увеличивается (умножается на 1.1)</li>
     * </ul>
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage     базовый урон атаки
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.1;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Применяет урон от лёгкой атаки.
     * <p>Множитель урона: 0.8. Модифицируется типом оружия:</p>
     * <ul>
     *     <li>Spear — урон уменьшается (делится на 1.1)</li>
     *     <li>Sword — урон увеличивается (умножается на 1.1)</li>
     * </ul>
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage     базовый урон атаки
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Spear) {
            damage /= 1.1;
        } else if (selectedWeapon instanceof Sword) {
            damage *= 1.1;
        }

        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Возвращает количество душ, выпадающих после победы над этим врагом.
     *
     * @return количество душ
     */
    @Override
    public int getSouls() {
        return 130;
    }
}