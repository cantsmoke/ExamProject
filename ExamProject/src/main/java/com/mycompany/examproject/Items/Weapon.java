/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import com.mycompany.examproject.GUI.WeaponBrokeDialog;
import com.mycompany.examproject.Player;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Абстрактный базовый класс для оружия в RPG-игре.
 * <p>
 * Реализует интерфейсы {@link Equipment} и {@link Serializable}, определяя характеристики
 * оружия, такие как вес, урон, прочность и состояние. Служит основой для всех
 * типов оружия в игре.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public abstract class Weapon implements Equipment, Serializable{
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Название оружия.
     */
    protected String name;
    
    /**
     * Вес оружия.
     */
    protected int weight;
    
    /**
     * Текущий урон оружия.
     */
    protected int damage;
    
    /**
     * Максимальный урон оружия.
     */
    protected int maxDamage;
    
    /**
     * Текущая прочность оружия.
     */
    protected int durability;
    
    /**
     * Максимальная прочность оружия.
     */
    protected int maxDurability;
    
    /**
     * Флаг, указывающий, сломано ли оружие.
     */
    protected boolean isBroken = false;
    
    /**
     * Флаг, указывающий, выбрано ли оружие игроком.
     */
    protected boolean isSelected = false;
    
    /**
     * Флаг, указывающий, был ли показан диалог о поломке оружия.
     */
    protected boolean wasStatusWindowShowed = false;
    
    /**
     * URL изображения оружия.
     */
    protected String imageURL;

    /**
     * Конструктор для создания объекта оружия.
     *
     * @param name Название оружия
     * @param weight Вес оружия
     * @param damage Урон, наносимый оружием
     * @param durability Начальная прочность оружия
     */
    public Weapon(String name, int weight, int damage, int durability) {
        this.name = name;
        this.weight = weight;
        this.damage = damage;
        this.maxDamage = damage;
        this.durability = durability;
        this.maxDurability = durability;
    }
    
    /**
     * Проверяет, является ли оружие сломанным.
     *
     * @return true, если оружие сломано, иначе false
     */
    public boolean isBroken() {
        return isBroken;
    }
    
    /**
     * Проверяет, выбрано ли оружие игроком.
     *
     * @return true, если оружие выбрано, иначе false
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Устанавливает статус выбора оружия.
     *
     * @param selected true, если оружие выбрано, иначе false
     */
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    /**
     * Возвращает название оружия.
     *
     * @return Название оружия
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает вес оружия.
     *
     * @return Вес оружия
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Возвращает текущий урон оружия.
     *
     * @return Текущий урон
     */
    public int getDamage(){
        return this.damage;
    }
    
    /**
     * Возвращает текущую прочность оружия.
     *
     * @return Текущая прочность
     */
    public int getDurability(){
        return this.durability;
    }
    
    /**
     * Устанавливает новую прочность оружия.
     *
     * @param newDurability Новое значение прочности
     */
    public void setDurability(int newDurability){
        this.durability = newDurability;
    }
    
    /**
     * Возвращает максимальную прочность оружия.
     *
     * @return Максимальная прочность
     */
    public int getMaxDurability(){
        return this.maxDurability;
    }
    
    /**
     * Возвращает URL изображения оружия.
     *
     * @return URL изображения
     */
    public String getImageUrl() {
        return imageURL;
    }
    
    /**
     * Возвращает строковое представление оружия.
     * <p>
     * Включает название, статус поломки (b) и выбор (***).
     *
     * @return Строковое представление оружия
     */
    @Override
    public String toString() {
        String result = name;
        if (isBroken) result += " (b)";
        if (isSelected) result += " (***)";
        return result;
    }
    
    /**
     * Проверяет состояние оружия.
     * <p>
     * Если прочность достигла нуля, оружие считается сломанным, урон
     * обнуляется, и отображается диалог о поломке.
     */
    @Override
    public void checkStatus(){
        if(this.durability <= 0){
            this.durability = 0;
            this.isBroken = true;
            if(!wasStatusWindowShowed){
                WeaponBrokeDialog weaponBrokeDialog = new WeaponBrokeDialog(null, true);
                weaponBrokeDialog.setVisible(true);
                this.wasStatusWindowShowed = true;
            }
            this.damage = 0;
        }
    }
    
    /**
     * Ремонтирует оружие.
     * <p>
     * Восстанавливает прочность и урон до максимальных значений,
     * сбрасывает статус поломки.
     */
    public void repair(){
        this.durability = this.maxDurability;
        this.damage = this.maxDamage;
        this.isBroken = false;
    }
    
}