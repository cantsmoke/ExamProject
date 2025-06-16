/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Armors;

import com.mycompany.examproject.Items.Armor;
import java.io.Serializable;

import java.io.Serializable;

/**
 * Представляет легкую броню в игре, расширяющую базовый класс {@link Armor}.
 * Легкая броня обеспечивает умеренную защиту, но имеет низкий вес,
 * что повышает мобильность и шанс уклонения игрока.
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * LightArmor armor = new LightArmor("Leather Vest", 20, 0.15, 60);
 * }</pre>
 * 
 * @author Arseniy
 */
public class LightArmor extends Armor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Конструктор для создания новой легкой брони с заданными параметрами.
     *
     * @param name             Название брони.
     * @param weight           Вес брони (влияет на шанс уклонения и скорость).
     * @param damageReduction  Коэффициент снижения урона (0.0–1.0).
     * @param durability       Прочность брони (до износа).
     */
    public LightArmor(String name, int weight, double damageReduction, int durability) {
        super(name, weight, damageReduction, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_28_15.png";
    }

    /**
     * Конструктор копирования для создания новой копии существующего экземпляра {@code LightArmor}.
     *
     * @param other Объект {@code LightArmor}, свойства которого будут скопированы.
     */
    public LightArmor(LightArmor other) {
        super(other.getName(), other.getWeight(), other.getDamageReduction(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_28_15.png";
    }
}