/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

/**
 * Перечисление типов действий сущности в игре.
 * <p>
 * Используется для задания возможных действий персонажа или сущности:
 * </p>
 * <ul>
 *     <li>{@link #LIGHT_ATTACK} - легкая атака, быстрая, но наносит меньше урона</li>
 *     <li>{@link #HEAVY_ATTACK} - тяжелая атака, медленнее, но наносит больше урона</li>
 *     <li>{@link #BLOCK} - блокировка атак противника</li>
 *     <li>{@link #DODGE} - уклонение от атаки</li>
 * </ul>
 *
 * @author Arseniy
 */
public enum EntityActionType {
    /**
     * Легкая атака.
     * <p>
     * Быстрое действие с небольшим уроном.
     */
    LIGHT_ATTACK,

    /**
     * Тяжелая атака.
     * <p>
     * Медленное действие с большим уроном.
     */
    HEAVY_ATTACK,

    /**
     * Блок.
     * <p>
     * Защищает от входящих атак.
     */
    BLOCK,

    /**
     * Уклонение.
     * <p>
     * Позволяет избежать атаки противника.
     */
    DODGE,
}