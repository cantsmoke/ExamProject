/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

/**
 * Перечисление, представляющее типы боссов на каждом этаже.
 * <p>
 * Каждый тип соответствует определённому этажу подземелья или уровня.
 * Используется для идентификации и логики поведения боссов в зависимости от их этажа.
 * </p>
 *
 * <ul>
 *     <li>{@link #FLOOR_ONE_BOSS} — Босс первого этажа</li>
 *     <li>{@link #FLOOR_TWO_BOSS} — Босс второго этажа</li>
 *     <li>{@link #FLOOR_THREE_BOSS} — Босс третьего этажа</li>
 *     <li>{@link #FLOOR_FOUR_BOSS} — Босс четвёртого этажа</li>
 *     <li>{@link #FLOOR_FIVE_BOSS} — Босс пятого этажа</li>
 *     <li>{@link #FLOOR_SIX_BOSS} — Босс шестого этажа</li>
 *     <li>{@link #FLOOR_SEVEN_BOSS} — Босс седьмого этажа</li>
 *     <li>{@link #FLOOR_EIGHT_BOSS} — Босс восьмого этажа</li>
 *     <li>{@link #FLOOR_NINE_BOSS} — Босс девятого этажа</li>
 *     <li>{@link #FLOOR_TEN_BOSS} — Босс десятого этажа</li>
 * </ul>
 *
 * @author Arseniy
 */
public enum BossType {
    /** Босс первого этажа. */
    FLOOR_ONE_BOSS,
    /** Босс второго этажа. */
    FLOOR_TWO_BOSS,
    /** Босс третьего этажа. */
    FLOOR_THREE_BOSS,
    /** Босс четвёртого этажа. */
    FLOOR_FOUR_BOSS,
    /** Босс пятого этажа. */
    FLOOR_FIVE_BOSS,
    /** Босс шестого этажа. */
    FLOOR_SIX_BOSS,
    /** Босс седьмого этажа. */
    FLOOR_SEVEN_BOSS,
    /** Босс восьмого этажа. */
    FLOOR_EIGHT_BOSS,
    /** Босс девятого этажа. */
    FLOOR_NINE_BOSS,
    /** Босс десятого этажа. */
    FLOOR_TEN_BOSS
}