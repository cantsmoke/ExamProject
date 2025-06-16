/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 * Класс Hammer представляет собой разновидность оружия — молот.
 * Наследуется от базового класса {@link Weapon} и реализует интерфейс {@link Serializable},
 * что позволяет сериализовать объекты этого класса.
 * 
 * У каждого экземпляра {@code Hammer} есть имя, вес, урон, прочность и изображение.
 * Изображение задаётся фиксированным URL.
 * 
 * @author Arseniy
 */
public class Hammer extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Конструктор для создания нового молота с заданными параметрами.
     *
     * @param name имя молота
     * @param weight вес молота
     * @param damage наносимый урон
     * @param durability текущая прочность молота
     */
    public Hammer(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 12_02_48.png";
    }
    
    /**
     * Конструктор копирования. Создаёт новый объект {@code Hammer},
     * копируя параметры из другого объекта {@code Hammer}.
     *
     * @param other другой объект {@code Hammer}, который необходимо скопировать
     */
    public Hammer(Hammer other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 12_02_48.png";
    }
    
}