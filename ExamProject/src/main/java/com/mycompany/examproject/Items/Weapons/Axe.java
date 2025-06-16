/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import com.mycompany.examproject.Items.Weapon;
import java.io.Serializable;

/**
 * Представляет оружие типа {@code Axe} (топор), расширяющее базовый класс {@link Weapon}.
 * Топоры, как правило, обладают высокой силой урона и весом, что может влиять на скорость и уклонение.
 * Каждый экземпляр имеет собственное изображение, применяемое в графическом интерфейсе.
 *
 * <p>Поддерживается сериализация для сохранения состояния в файле.</p>
 * 
 * <p>Пример использования:</p>
 * <pre>{@code
 * Axe axe = new Axe("Brutal War Axe", 35, 70, 100);
 * }</pre>
 * 
 * @author Arseniy
 */
public class Axe extends Weapon implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Создает новый экземпляр {@code Axe} с указанными характеристиками.
     *
     * @param name       Название оружия.
     * @param weight     Вес топора (влияет на уклонение и скорость действий).
     * @param damage     Базовый урон, наносимый этим оружием.
     * @param durability Прочность оружия (до поломки).
     */
    public Axe(String name, int weight, int damage, int durability) {
        super(name, weight, damage, durability);
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_26_14.png";
    }
    
    /**
     * Конструктор копирования — создает новый {@code Axe}, копируя параметры другого.
     *
     * @param other Существующий экземпляр {@code Axe}, параметры которого копируются.
     */
    public Axe(Axe other) {
        super(other.getName(), other.getWeight(), other.getDamage(), other.getDurability());
        this.imageURL = "/ChatGPT Image 12 июн. 2025 г., 13_26_14.png";
    }
    
}