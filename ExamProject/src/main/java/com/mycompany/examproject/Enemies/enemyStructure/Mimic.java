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
 * Враг "Mimic" — скелетообразное существо, маскирующееся под безобидный сундук.
 * Имеет агрессивный боевой паттерн, с упором на тяжёлые атаки и умеренные параметры защиты.
 *
 * <p>Наследует поведение от {@link Enemy} и реализует интерфейс {@link Skeleton}.</p>
 *
 * <p>Особенности:</p>
 * <ul>
 *     <li>Боевой паттерн состоит преимущественно из тяжёлых атак.</li>
 *     <li>Низкий шанс уклонения — 30%.</li>
 *     <li>Средний шанс блока — 50%.</li>
 *     <li>Уязвимости к типам оружия:
 *         <ul>
 *             <li>{@link Hammer} — увеличенный урон.</li>
 *             <li>{@link Bow} — уменьшенный урон.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Пример создания:</p>
 * <pre>{@code
 * Mimic mimic = new Mimic("Mimic", 300, 60, 3);
 * }</pre>
 *
 * @author Arseniy
 */
public class Mimic extends Enemy implements Skeleton {

    /** Стандартный шаблон боевых действий. */
    private static final EntityActionType[] defaultPattern = new EntityActionType[] {
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.LIGHT_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.HEAVY_ATTACK,
        EntityActionType.LIGHT_ATTACK
    };

    /** Текущий шаблон действий врага. */
    private EntityActionType[] pattern;

    /** Путь к иконке врага. */
    private String iconSource;

    /** Вероятность уклонения (0..1). */
    private double dodgeP;

    /** Вероятность блока (0..1). */
    private double blockP;

    /**
     * Конструктор для создания объекта типа Mimic.
     *
     * @param name имя врага
     * @param health здоровье
     * @param damage урон
     * @param floorNum этаж, на котором появляется враг
     */
    public Mimic(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.pattern = defaultPattern;
        this.iconSource = "/ChatGPT Image 15 июн. 2025 г., 15_48_48 (1).png";
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

    /**
     * Устанавливает новый шаблон поведения врага.
     *
     * @param newPattern новый паттерн действий
     */
    @Override
    public void setPattern(EntityActionType[] newPattern) {
        this.pattern = newPattern;
    }

    /**
     * Возвращает стандартный шаблон поведения (игнорируя индивидуальные изменения).
     *
     * @return массив действий
     */
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
     * Получение стандартного боевого шаблона (может использоваться извне).
     *
     * @return шаблон действий
     */
    public static EntityActionType[] getDefaultPattern() {
        return defaultPattern;
    }

    /**
     * Логика получения урона при тяжёлой атаке. Урон модифицируется в зависимости от типа оружия:
     * <ul>
     *     <li>{@link Hammer} — увеличенный урон.</li>
     *     <li>{@link Bow} — уменьшенный урон.</li>
     * </ul>
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием полученного урона
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
     * Логика получения урона при лёгкой атаке. Урон модифицируется в зависимости от типа оружия:
     * <ul>
     *     <li>{@link Hammer} — увеличенный урон.</li>
     *     <li>{@link Bow} — уменьшенный урон.</li>
     * </ul>
     *
     * @param selectedWeapon оружие игрока
     * @param baseDamage базовое значение урона
     * @return строка с описанием полученного урона
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
     * Количество душ, получаемое за победу над данным врагом.
     *
     * @return количество душ
     */
    @Override
    public int getSouls() {
        return 350;
    }
}