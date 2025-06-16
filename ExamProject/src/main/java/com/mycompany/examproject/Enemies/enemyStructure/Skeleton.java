/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Items.Weapon;

/**
 * Интерфейс, представляющий скелета в игре.
 * <p>
 * Описывает основные методы, которые должен реализовать любой класс-скелет:
 * получение имени и обработка получения легкого или тяжелого урона.
 * </p>
 *
 * @author Arseniy
 */
public interface Skeleton {
    /**
     * Возвращает имя скелета.
     *
     * @return имя скелета
     */
    String getName();

    /**
     * Обрабатывает получение скелетом тяжелого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage базовый урон, наносимый атакой
     * @return описание результата получения урона
     */
    String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);

    /**
     * Обрабатывает получение скелетом легкого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage базовый урон, наносимый атакой
     * @return описание результата получения урона
     */
    String takeLightDamage(Weapon selectedWeapon, int baseDamage);
    
    /**
    * Возвращает текущее количество здоровья скелета.
    *
    * @return текущее здоровье
    */
   int getHealth();

   /**
    * Возвращает базовый урон скелета.
    *
    * @return базовый урон
    */
   int getDamage();
}