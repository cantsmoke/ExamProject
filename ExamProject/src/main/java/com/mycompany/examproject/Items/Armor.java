/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import com.mycompany.examproject.GUI.ArmorBrokeDialog;
import java.io.Serializable;

/**
 * Абстрактный базовый класс для брони в RPG-игре.
 * <p>
 * Реализует интерфейсы {@link Equipment} и {@link Serializable}, определяя характеристики
 * брони, такие как вес, снижение урона, прочность и состояние. Служит основой для всех
 * типов брони в игре.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public abstract class Armor implements Equipment, Serializable{
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Название брони.
     */
    private String name;
    
    /**
     * Вес брони.
     */
    protected int weight;
    
    /**
     * Текущее значение снижения урона.
     */
    protected double damageReduction;
    
    /**
     * Максимальное значение снижения урона.
     */
    protected double maxDamageReduction;
    
    /**
     * Текущая прочность брони.
     */
    protected int durability;
    
    /**
     * Максимальная прочность брони.
     */
    protected int maxDurability;
    
    /**
     * Флаг, указывающий, сломана ли броня.
     */
    protected boolean isBroken = false;
    
    /**
     * Флаг, указывающий, выбрана ли броня игроком.
     */
    protected boolean isSelected = false;
    
    /**
     * Флаг, указывающий, был ли показан диалог о поломке брони.
     */
    protected boolean wasStatusWindowShowed = false;
    
    /**
     * URL изображения брони.
     */
    protected String imageURL;

    /**
     * Конструктор для создания объекта брони.
     *
     * @param name название брони
     * @param weight вес брони
     * @param damageReduction снижение урона, обеспечиваемое броней
     * @param durability начальная прочность брони
     */
    public Armor(String name, int weight, double damageReduction, int durability) {
        this.name = name;
        this.weight = weight;
        this.damageReduction = damageReduction;
        this.maxDamageReduction = damageReduction;
        this.durability = durability;
        this.maxDurability = durability;
    }
    
    /**
     * Проверяет, сломана ли броня.
     *
     * @return true, если броня сломана, иначе false
     */
    public boolean isBroken() {
        return isBroken;
    }
    
    /**
     * Проверяет, выбрана ли броня игроком.
     *
     * @return true, если броня выбрана, иначе false
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Устанавливает статус выбора брони.
     *
     * @param selected true, если броня выбрана, иначе false
     */
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    /**
     * Возвращает название брони.
     *
     * @return название брони
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает вес брони.
     *
     * @return вес брони
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Возвращает текущее значение снижения урона.
     *
     * @return снижение урона
     */
    public double getDamageReduction(){
        return this.damageReduction;
    }
    
    /**
     * Возвращает текущую прочность брони.
     *
     * @return текущая прочность
     */
    public int getDurability(){
        return this.durability;
    }
    
    /**
     * Устанавливает новую прочность брони.
     *
     * @param newDurability новое значение прочности
     */
    public void setDurability(int newDurability){
        this.durability = newDurability;
    }
    
    /**
     * Возвращает максимальную прочность брони.
     *
     * @return максимальная прочность
     */
    public int getMaxDurability(){
        return this.maxDurability;
    }
    
    /**
     * Возвращает URL изображения брони.
     *
     * @return URL изображения
     */
    public String getImageUrl() {
        return imageURL;
    }
    
    /**
     * Возвращает строковое представление брони.
     * <p>
     * Включает название, статус поломки (b) и выбор (***).
     *
     * @return строковое представление брони
     */
    @Override
    public String toString() {
        String result = name;
        if (isBroken) result += " (b)";
        if (isSelected) result += " (***)";
        return result;
    }
    
    /**
     * Проверяет состояние брони.
     * <p>
     * Если прочность достигла нуля, броня считается сломанной, снижение урона
     * обнуляется, и отображается диалог о поломке.
     */
    @Override
    public void checkStatus(){
        if(this.durability <= 0){
            this.durability = 0;
            this.isBroken = true;
            if(!wasStatusWindowShowed){
                ArmorBrokeDialog armorBrokeDialog = new ArmorBrokeDialog(null, true);
                armorBrokeDialog.setVisible(true);
                this.wasStatusWindowShowed = true;
            }
            this.damageReduction = 0;
        }
    }
    
    /**
     * Ремонтирует броню.
     * <p>
     * Восстанавливает прочность и снижение урона до максимальных значений,
     * сбрасывает статус поломки.
     */
    public void repair(){
        this.durability = this.maxDurability;
        this.damageReduction = this.maxDamageReduction;
        this.isBroken = false;
    }
    
}