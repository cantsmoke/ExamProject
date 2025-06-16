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
 * Босс "Ashen Gatekeeper" — страж для определённого этажа подземелья.
 * Имеет уникальный набор характеристик, атакующий паттерн, вероятности уклонения и блока.
 *
 * <p>Используется в качестве одного из боссов игры. Наследует базовые характеристики от {@link Boss}, 
 * переопределяет поведение получения урона, а также задаёт собственный паттерн действий.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Имеет фиксированный боевой паттерн из лёгких и тяжёлых атак.</li>
 *     <li>Иконка устанавливается через {@code iconSource} — путь к изображению.</li>
 *     <li>Уникальные вероятности уклонения ({@code dodgeP}) и блока ({@code blockP}).</li>
 *     <li>Получает увеличенный/уменьшенный урон в зависимости от типа оружия:
 *         <ul>
 *             <li>{@link Hammer} — увеличенный урон.</li>
 *             <li>{@link Bow}, {@link Axe} — сниженный урон.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * AshenGatekeeper boss = new AshenGatekeeper("Ashen Gatekeeper", 500, 70, 5);
 * }</pre>
 *
 * @author Arseniy
 */
public class AshenGatekeeper extends Boss {

    /** Боевой паттерн действий босса. */
    private EntityActionType[] pattern;

    /** Путь к иконке босса. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания Ashen Gatekeeper.
     *
     * @param name имя босса
     * @param health начальное здоровье
     * @param damage базовый урон
     * @param floorNum номер этажа, на котором появляется босс
     */
    public AshenGatekeeper(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = new EntityActionType[] {
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.LIGHT_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.HEAVY_ATTACK,
            EntityActionType.LIGHT_ATTACK
        };
        this.iconSource = "/ChatGPT Image 4 июн. 2025 г., 18_10_11 (1).png";
        this.dodgeP = 0.7;
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
     * Логика получения урона при тяжёлой атаке. Урон модифицируется в зависимости от типа оружия.
     * 
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 1.2);
        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Axe) {
            damage /= 1.4;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.4;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }

    /** 
     * Логика получения урона при лёгкой атаке. Урон модифицируется в зависимости от типа оружия.
     * 
     * @param selectedWeapon используемое игроком оружие
     * @param baseDamage базовый урон
     * @return текст о нанесённом уроне
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        int damage = (int) ((selectedWeapon.getDamage() + baseDamage) * 0.8);
        if (selectedWeapon instanceof Bow || selectedWeapon instanceof Axe) {
            damage /= 1.3;
        } else if (selectedWeapon instanceof Hammer) {
            damage *= 1.3;
        }
        this.health -= damage;
        return this.name + " took " + damage + " damage!";
    }
}