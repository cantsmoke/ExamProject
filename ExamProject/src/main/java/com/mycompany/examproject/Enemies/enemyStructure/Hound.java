/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Items.Weapon;

/**
 * Интерфейс, представляющий гончую в игре.
 * <p>
 * Описывает основные методы, которые должен реализовать любой класс-гончая:
 * получение имени и обработка получения легкого или тяжелого урона.
 * </p>
 *
 * @author Arseniy
 */
public interface Hound {
    /**
     * Возвращает имя гончей.
     *
     * @return имя гончей
     */
    String getName();

    /**
     * Обрабатывает получение гончей тяжелого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage базовый урон, наносимый атакой
     * @return описание результата получения урона
     */
    String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);

    /**
     * Обрабатывает получение гончей легкого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage базовый урон, наносимый атакой
     * @return описание результата получения урона
     */
    String takeLightDamage(Weapon selectedWeapon, int baseDamage);
    
    /**
    * Возвращает текущее количество здоровья гончей.
    *
    * @return текущее здоровье
    */
   int getHealth();

   /**
    * Возвращает значение базового урона гончей.
    *
    * @return базовый урон
    */
   int getDamage();
}