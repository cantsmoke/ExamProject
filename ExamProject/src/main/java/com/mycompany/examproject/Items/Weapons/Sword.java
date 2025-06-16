/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 * Класс Sword представляет собой разновидность оружия — меч.
 * Наследуется от базового класса {@link Weapon} и реализует интерфейс {@link Serializable},
 * что позволяет сериализовать объекты этого класса.
 * 
 * У каждого экземпляра {@code Sword} есть имя, вес, урон, прочность и изображение.
 * Изображение задаётся фиксированным URL.
 * 
 * @author Arseniy
 */
public class Sword extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Конструктор для создания нового меча с заданными параметрами.
     *
     * @param name имя меча
     * @param weight вес меча
     * @param damage наносимый урон
     * @param durability текущая прочность меча
     */
    public Sword(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 11_53_15.png";
    }
    
    /**
     * Конструктор копирования. Создаёт новый объект {@code Sword},
     * копируя параметры из другого объекта {@code Sword}.
     *
     * @param other другой объект {@code Sword}, который необходимо скопировать
     */
    public Sword(Sword other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 11_53_15.png";
    }
    
}