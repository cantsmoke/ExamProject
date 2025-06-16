/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;

/**
 * Противник "BlackSkeleton" — Чёрный Скелет. 
 * Продвинутый вариант обычного скелета.
 *
 * <p>Реализует {@link Skeleton} и наследует {@link Enemy}, обладая усиленной защитой и неплохой атакой.</p>
 *
 * <p>Модификаторы урона:</p>
 * <ul>
 *     <li>{@link Bow} — наносит пониженный урон (особенно тяжёлый).</li>
 *     <li>{@link Hammer} — наносит повышенный урон.</li>
 * </ul>
 *
 * <p>Характеристики:</p>
 * <ul>
 *     <li>Шанс уклонения: 30%</li>
 *     <li>Шанс блока: 50%</li>
 *     <li>Боевой паттерн: легк — легк — тяж.</li>
 *     <li>Души за победу: 150</li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * BlackSkeleton skeleton = new BlackSkeleton("Black Skeleton", 120, 40, 2);
 * }</pre>
 *
 * @author Arseniy
 */
public class BlackSkeleton extends Enemy implements Skeleton {

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
     * Конструктор Чёрного Скелета.
     *
     * @param name имя врага
     * @param health здоровье
     * @param damage урон
     * @param floorNum номер этажа, на котором появляется
     */
    public BlackSkeleton(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 14_08_29.png";
        this.dodgeP = 0.3;
        this.blockP = 0.5;
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
     * Возвращает шаблон атаки по умолчанию.
     *
     * @return массив действий
     */
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    /**
     * Применение тяжёлого урона по врагу.
     * <p>Урон увеличен для {@link Hammer}, снижен для {@link Bow}.</p>
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовый урон
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Применение лёгкого урона по врагу.
     * <p>Урон увеличен для {@link Hammer}, снижен для {@link Bow}.</p>
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовый урон
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Bow) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Количество душ, выпадающих за победу над врагом.
     *
     * @return количество душ
     */
    @Override
    public int getSouls() {
        return 150;
    }
}