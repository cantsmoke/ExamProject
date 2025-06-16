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

/**
 * Враг "CursedSkeleton" — Проклятый Скелет.
 * 
 * <p>Слабейший противник класса {@link Skeleton}, обладающий повышенной уязвимостью к дробящему урону 
 * и устойчивостью к дальнобойному.</p>
 *
 * <p>Поведение:</p>
 * <ul>
 *     <li>Шаблон атаки: легк — легк — тяж.</li>
 *     <li>Шанс уклонения: 20%</li>
 *     <li>Шанс блока: 40%</li>
 *     <li>Награда за победу: 100 душ</li>
 * </ul>
 *
 * <p>Модификаторы урона:</p>
 * <ul>
 *     <li>Устойчив к {@link Bow} — урон снижается.</li>
 *     <li>Уязвим к {@link Hammer} — урон увеличивается.</li>
 * </ul>
 *
 * @author Arseniy
 */
public class CursedSkeleton extends Enemy implements Skeleton {

    /** Стандартный шаблон действий. */
    private static final EntityActionType[] defaultPattern = new EntityActionType[] {
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.HEAVY_ATTACK
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
     * Конструктор проклятого скелета.
     *
     * @param name имя врага
     * @param health здоровье
     * @param damage базовый урон
     * @param floorNum этаж появления
     */
    public CursedSkeleton(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_03_51.png";
        this.dodgeP = 0.2;
        this.blockP = 0.4;
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
     * <p>Bow — понижение, Hammer — усиление.</p>
     *
     * @param selectedWeapon оружие
     * @param baseDamage базовый урон
     * @return сообщение о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.4;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка лёгкого урона.
     * <p>Bow — понижение, Hammer — усиление.</p>
     *
     * @param selectedWeapon оружие
     * @param baseDamage базовый урон
     * @return сообщение о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Количество душ, выпадающих при победе.
     *
     * @return 100
     */
    @Override
    public int getSouls() {
        return 100;
    }
}
