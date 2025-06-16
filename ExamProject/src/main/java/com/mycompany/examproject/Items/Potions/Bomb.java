/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;

/**
 * Представляет боевой предмет типа {@code Bomb}, расширяющий базовый класс {@link Potion}.
 * Бомба используется для нанесения фиксированного урона врагу и не восстанавливает характеристики игрока.
 * Урон зависит от текущего этажа подземелья, на котором находится игрок.
 *
 * <p>Формула урона: <b>{@code damage = currentFloor * 10 + 40}</b></p>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * Bomb bomb = new Bomb();
 * int dmg = bomb.getDamage();
 * }</pre>
 * 
 * @author Arseniy
 */
public class Bomb extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /**
     * Количество урона, которое наносит бомба. Вычисляется при создании на основе текущего этажа.
     */
    private int damage;

    /**
     * Путь к изображению бомбы, используемому в интерфейсе.
     */
    private String imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_51_32 (1).png";

    /**
     * Создает экземпляр {@code Bomb}, устанавливая имя, описание и вычисляя урон.
     * Урон зависит от текущего этажа: {@code floor * 10 + 40}.
     */
    public Bomb() {
        super("Bomb", "Deals damage to enemy.");
        this.damage = Player.getInstance().getCurrentRoom().getFloor() * 10 + 40;
    }

    /**
     * Возвращает путь к изображению, связанному с этой бомбой.
     *
     * @return строка с URL изображения.
     */
    public String getImageURL() {
        return this.imageURL;
    }

    /**
     * Возвращает вычисленное значение урона, которое наносит бомба.
     *
     * @return урон в числовом виде.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Возвращает текстовое представление бомбы в формате: {@code Bomb(урон)}.
     *
     * @return строковое описание бомбы.
     */
    @Override
    public String toString() {
        return this.name + "(" + damage + ")";
    }

    /**
     * Метод проверки состояния (например, износа или срока годности предмета).
     * В {@code Bomb} пока не реализован и вызывает исключение.
     *
     * @throws UnsupportedOperationException всегда, поскольку метод не поддерживается.
     */
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}