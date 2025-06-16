/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Map;

/**
 * Перечисление, представляющее различные типы комнат в игре.
 * <p>
 * Используется для задания типа комнаты в игровом уровне или подземелье.
 * </p>
 * 
 * Доступные типы комнат:
 * <ul>
 *   <li>{@link #NORMAL} — обычная комната, может содержать врагов, лут или ловушки.</li>
 *   <li>{@link #STAIRCASE_UP} — лестница на верхний этаж или в следующую зону.</li>
 *   <li>{@link #STAIRCASE_DOWN} — лестница на нижний этаж или в предыдущую зону.</li>
 *   <li>{@link #REST} — комната отдыха, где игрок может восстановить здоровье и запасы эстуса.</li>
 *   <li>{@link #ENTRANCE_HALL} — входной зал, начальная точка в игре.</li>
 *   <li>{@link #BOSS} — комната с основным (боссом) противником этажа.</li>
 * </ul>
 * 
 * @author Arseniy
 */
public enum RoomType {
    /** Обычная комната. */
    NORMAL,
    /** Лестница наверх. */
    STAIRCASE_UP,
    /** Лестница вниз. */
    STAIRCASE_DOWN,
    /** Комната отдыха. */
    REST,
    /** Входной зал или стартовая комната. */
    ENTRANCE_HALL,
    /** Комната с боссом. */
    BOSS
}