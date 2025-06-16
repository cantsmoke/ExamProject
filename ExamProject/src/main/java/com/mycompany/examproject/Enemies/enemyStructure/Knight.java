/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Items.Weapon;

/**
 *
 * @author Arseniy
 */
/**
 * Интерфейс, представляющий рыцаря в игре.
 * <p>
 * Описывает основные методы, которые должен реализовать любой класс-рыцарь:
 * получение имени, обработка урона от тяжелой и легкой атаки.
 * </p>
 *
 * @author Arseniy
 */
public interface Knight {
    /**
     * Возвращает имя рыцаря.
     *
     * @return имя рыцаря
     */
    String getName();

    /**
     * Обрабатывает получение рыцарем тяжелого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage     базовый урон, наносимый атакой
     * @return описание результата нанесенного урона
     */
    String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);

    /**
     * Обрабатывает получение рыцарем легкого урона.
     *
     * @param selectedWeapon используемое оружие
     * @param baseDamage     базовый урон, наносимый атакой
     * @return описание результата нанесенного урона
     */
    String takeLightDamage(Weapon selectedWeapon, int baseDamage);
    
    /**
    * Возвращает текущее количество здоровья рыцаря.
    *
    * @return текущее здоровье
    */
   int getHealth();

   /**
    * Возвращает значение базового урона рыцаря.
    *
    * @return базовый урон
    */
   int getDamage();
}