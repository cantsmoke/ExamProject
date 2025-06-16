/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 * Класс Spear представляет собой разновидность оружия — копьё.
 * Наследуется от базового класса {@link Weapon} и реализует интерфейс {@link Serializable},
 * что позволяет сериализовать объекты этого класса.
 * 
 * У каждого экземпляра {@code Spear} есть имя, вес, урон, прочность и изображение.
 * Изображение задаётся фиксированным URL.
 * 
 * @author Arseniy
 */
public class Spear extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Конструктор для создания нового копья с заданными параметрами.
     *
     * @param name имя копья
     * @param weight вес копья
     * @param damage наносимый урон
     * @param durability текущая прочность копья
     */
    public Spear(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_45_57 (1).png";
    }
    
    /**
     * Конструктор копирования. Создаёт новый объект {@code Spear},
     * копируя параметры из другого объекта {@code Spear}.
     *
     * @param other другой объект {@code Spear}, который необходимо скопировать
     */
    public Spear(Spear other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_45_57 (1).png";
    }
    
}