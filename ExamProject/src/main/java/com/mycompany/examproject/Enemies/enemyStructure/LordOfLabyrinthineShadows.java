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
 * Босс "Lord Of Labyrinthine Shadows" — ловкий противник с повышенными шансами уклонения и блока,
 * уязвимый к дальнобойному оружию и устойчивый к ударам топорами и молотами.
 *
 * <p>Наследует поведение {@link Boss}, обладает уникальным паттерном атак, высокой выживаемостью
 * за счёт защиты, а также индивидуальной системой уязвимостей.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Боевой паттерн сочетает серии тяжёлых и лёгких атак.</li>
 *     <li>Высокий шанс уклонения — 65%.</li>
 *     <li>Высокий шанс блока — 65%.</li>
 *     <li>Уязвимости к типам оружия:
 *         <ul>
 *             <li>{@link Bow} — увеличенный урон.</li>
 *             <li>{@link Axe} и {@link Hammer} — уменьшенный урон.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * LordOfLabyrinthineShadows boss = new LordOfLabyrinthineShadows("Lord Of Labyrinthine Shadows", 580, 70, 4);
 * }</pre>
 *
 * @author Arseniy
 */
public class LordOfLabyrinthineShadows extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Lord Of Labyrinthine Shadows.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
    public LordOfLabyrinthineShadows(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 17_46_44.png";
        this.dodgeP = 0.65;
        this.blockP = 0.65;
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
     * Логика получения урона при тяжёлой атаке. Урон модифицируется в зависимости от оружия:
     * <ul>
     *     <li>{@link Bow} наносит увеличенный урон.</li>
     *     <li>{@link Axe} и {@link Hammer} — снижают получаемый урон.</li>
     * </ul>
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Hammer) {
            damage /= 1.2;
        } else if (selectedWeapon instanceof Bow) {
            damage *= 1.2;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /**
     * Логика получения урона при лёгкой атаке. Урон модифицируется в зависимости от оружия:
     * <ul>
     *     <li>{@link Bow} наносит увеличенный урон.</li>
     *     <li>{@link Axe} и {@link Hammer} — снижают получаемый урон.</li>
     * </ul>
     *
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return строка с информацией о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);

        if (selectedWeapon instanceof Axe || selectedWeapon instanceof Hammer) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Bow) {
            damage *= 1.4;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}