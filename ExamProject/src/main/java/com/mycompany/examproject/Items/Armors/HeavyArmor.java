/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Armors;

import com.mycompany.examproject.Items.Armor;
import java.io.Serializable;

/**
 * Представляет тяжелую броню в игре, расширяет базовый класс {@link Armor}.
 * Такая броня имеет повышенную защиту и вес, что влияет на подвижность персонажа.
 * Также содержит путь к изображению брони для отображения в интерфейсе.
 * 
 * <p>Пример использования:</p>
 * <pre>{@code
 * HeavyArmor armor = new HeavyArmor("Iron Plate", 80, 0.45, 100);
 * }</pre>
 * 
 * @author Arseniy
 */
public class HeavyArmor extends Armor implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /**
     * Конструктор для создания новой тяжелой брони с заданными параметрами.
     *
     * @param name             Название брони.
     * @param weight           Вес брони (влияет на уклонение и инициативу).
     * @param damageReduction  Коэффициент снижения получаемого урона (0.0–1.0).
     * @param durability       Прочность брони (максимальное значение до поломки).
     */
    public HeavyArmor(String name, int weight, double damageReduction, int durability) {
        super(name, weight, damageReduction, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 1.png";
    }
    
    /**
     * Конструктор копирования для создания новой копии существующей {@code HeavyArmor}.
     *
     * @param other Существующий экземпляр {@code HeavyArmor}, который нужно скопировать.
     */
    public HeavyArmor(HeavyArmor other) {
        super(other.getName(), other.getWeight(), other.getDamageReduction(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 1.png";
    }
    
}