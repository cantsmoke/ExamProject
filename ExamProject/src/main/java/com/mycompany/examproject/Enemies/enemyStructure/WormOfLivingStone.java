/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Sword;

/**
 * Босс "WormOfLivingStone" — Червь Живого Камня. 
 * Огромный подземный зверь, пробуждённый дрожью глубин. Известен своей непробиваемой каменной бронёй.
 *
 * <p>Наследует {@link Boss} и задаёт уникальные модификаторы урона в зависимости от типа оружия:</p>
 * <ul>
 *     <li>{@link Sword} и {@link Bow} — наносят пониженный урон.</li>
 *     <li>{@link Hammer} — наносит повышенный урон.</li>
 * </ul>
 *
 * <p>Характеристики:</p>
 * <ul>
 *     <li>Шанс уклонения: 45%</li>
 *     <li>Шанс блока: 80% — один из самых высоких среди боссов.</li>
 *     <li>Боевой паттерн: тяж — легк — тяж — легк — тяж — тяж.</li>
 * </ul>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * WormOfLivingStone worm = new WormOfLivingStone("Worm of Living Stone", 420, 95, 6);
 * }</pre>
 *
 * @author Arseniy
 */
public class WormOfLivingStone extends Boss {

    /** Шаблон атаки босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Шанс уклонения. */
    private double dodgeP;

    /** Шанс блока. */
    private double blockP;

    /**
     * Конструктор Червя Живого Камня.
     *
     * @param name имя босса
     * @param health здоровье
     * @param damage урон
     * @param floorNum этаж появления
     */
    public WormOfLivingStone(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 18_31_30.png";
        this.dodgeP = 0.45;
        this.blockP = 0.8;
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
     * Обработка тяжёлого урона с учетом типа оружия.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовый урон
     * @return строка с отчётом об уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Sword || selectedWeapon instanceof Bow) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Обработка лёгкого урона с учетом типа оружия.
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовый урон
     * @return строка с отчётом об уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Sword || selectedWeapon instanceof Bow) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}