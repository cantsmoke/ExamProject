/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Armors;

import com.mycompany.examproject.Items.Armor;
import java.io.Serializable;

import java.io.Serializable;

/**
 * Представляет армейскую броню (Trooper Armor), расширяющую базовый класс {@link Armor}.
 * Балансирует между защитой и весом, предлагая умеренную защиту при приемлемой подвижности.
 * Может использоваться как универсальный тип брони для различных классов персонажей.
 * 
 * <p>Имеет привязанное изображение для отображения в интерфейсе пользователя.</p>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * TrooperArmor armor = new TrooperArmor("Trooper Set", 40, 0.3, 80);
 * }</pre>
 * 
 * @author Arseniy
 */
public class TrooperArmor extends Armor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Создает новый экземпляр {@code TrooperArmor} с указанными характеристиками.
     *
     * @param name             Название брони.
     * @param weight           Вес брони (влияет на мобильность).
     * @param damageReduction  Коэффициент снижения урона (0.0–1.0).
     * @param durability       Прочность брони (до поломки).
     */
    public TrooperArmor(String name, int weight, double damageReduction, int durability) {
        super(name, weight, damageReduction, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_29_40.png";
    }

    /**
     * Конструктор копирования — создает копию другой {@code TrooperArmor}.
     *
     * @param other Исходная броня, свойства которой копируются.
     */
    public TrooperArmor(TrooperArmor other) {
        super(other.getName(), other.getWeight(), other.getDamageReduction(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_29_40.png";
    }
}