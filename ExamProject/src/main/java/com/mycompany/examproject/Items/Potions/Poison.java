/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;

/**
 * Представляет ядовитое зелье {@code Poison}, которое применяется против врага
 * и наносит периодический урон в течение нескольких ходов.
 * Расширяет базовый класс {@link Potion}.
 *
 * <p>Урон и длительность действия яда зависят от текущего этажа, на котором находится игрок.</p>
 *
 * <p>Формулы:</p>
 * <ul>
 *   <li>{@code damage = 5 + currentFloor}</li>
 *   <li>{@code duration = 5 + (currentFloor - 3)}</li>
 * </ul>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * Poison poison = new Poison();
 * int dmgPerTurn = poison.getDamage();
 * int turns = poison.getDuration();
 * }</pre>
 * 
 * @author Arseniy
 */
public class Poison extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /** Путь к изображению зелья в интерфейсе. */
    private String imageURL = "/ChatGPT Image 14 июн. 2025 г., 17_07_56 (1).png";

    /** Урон, который наносится за один ход. */
    private int damage;

    /** Длительность эффекта яда (в ходах). */
    private int duration;

    /**
     * Создает новый экземпляр {@code Poison} с параметрами, зависящими от текущего этажа:
     * {@code damage = 5 + floor}, {@code duration = 5 + (floor - 3)}.
     */
    public Poison() {
        super("Poison", "Poisons enemy.");
        this.damage = 5 + Player.getInstance().getCurrentRoom().getFloor();
        this.duration = 5 + (Player.getInstance().getCurrentRoom().getFloor() - 3);
    }
    
    /**
     * Возвращает путь к изображению, ассоциированному с этим ядом.
     *
     * @return строка с путем к изображению.
     */
    public String getImageURL() {
        return this.imageURL;
    }

    /**
     * Возвращает урон, который будет наноситься за каждый ход отравления.
     *
     * @return урон в числовом выражении.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Возвращает продолжительность действия яда в ходах.
     *
     * @return количество ходов действия.
     */
    public int getDuration() {
        return this.duration;
    }
    
    /**
     * Возвращает строковое описание объекта в формате:
     * {@code Poison(Damage: X; Duration: Y)}.
     *
     * @return строка с описанием яда.
     */
    @Override
    public String toString() {
        String result = name + "(Damage: " + damage + "; Duration: " + duration + ")";
        return result;
    }

    /**
     * Метод проверки состояния. В текущей реализации не используется.
     *
     * @throws UnsupportedOperationException всегда, так как метод не поддерживается.
     */
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}