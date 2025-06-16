/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 * Класс Bow представляет собой разновидность оружия — лук.
 * Наследуется от базового класса {@link Weapon} и реализует интерфейс {@link Serializable},
 * что позволяет сериализовать объекты этого класса.
 * 
 * У каждого экземпляра {@code Bow} есть имя, вес, урон, прочность и изображение.
 * По умолчанию изображение задаётся фиксированным URL.
 * 
 * @author Arseniy
 */
public class Bow extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Конструктор для создания нового лука с заданными параметрами.
     *
     * @param name имя лука
     * @param weight вес лука
     * @param damage наносимый урон
     * @param durability текущая прочность лука
     */
    public Bow(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 11_56_08.png";
    }
    
    /**
     * Конструктор копирования. Создаёт новый объект {@code Bow},
     * копируя параметры из другого объекта {@code Bow}.
     *
     * @param other другой объект {@code Bow}, который необходимо скопировать
     */
    public Bow(Bow other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 11_56_08.png";
    }
    
}