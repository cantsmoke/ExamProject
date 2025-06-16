/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.GUI.HealthFullOrNoEstusDialog;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Представляет флягу {@code EstusBottle}, используемую для восстановления здоровья игрока.
 * Расширяет базовый класс {@link Potion}. Может быть использована ограниченное количество раз.
 * Эффект восстановления зависит от максимального HP игрока.
 *
 * <p>Формула восстановления: {@code +25% от max HP}, ограничено {@code maxUses} применениями.</p>
 * 
 * <p>Если здоровье уже полное или фляга пуста — открывается диалоговое окно с предупреждением.</p>
 * 
 * <p>Пример использования:</p>
 * <pre>{@code
 * EstusBottle estus = new EstusBottle();
 * estus.heal(Player.getInstance());
 * }</pre>
 * 
 * @author Arseniy
 */
public class EstusBottle extends Potion implements Serializable{
    
    /** Процент восстановления здоровья от максимального HP игрока. */
    private double healRegenPercentage;

    /** Максимальное количество применений фляги. */
    private int maxUses;

    /** Оставшееся количество применений. */
    private int usesLeft;

    /** Путь к изображению фляги, используемому в UI. */
    private String imageURL = "/imgonline-com-ua-BrightnessContrast-DWdgxay1HD3Nqm.png";

    /**
     * Создает новый экземпляр {@code EstusBottle} с заданными параметрами:
     * {@code 25% восстановления}, {@code 10} максимум применений, {@code 5} текущих зарядов.
     */
    public EstusBottle() {
        super("Estus", "Regens your health.");
        this.healRegenPercentage = 0.25;
        this.maxUses = 10;
        this.usesLeft = 5;
    }

    /**
     * Возвращает процент восстановления здоровья от максимального HP.
     *
     * @return процент (в формате от 0.0 до 1.0)
     */
    public double getHealthRegenPercentage(){
        return this.healRegenPercentage;
    }
    
    /**
     * Полностью восстанавливает количество зарядов Estus до {@code maxUses}.
     */
    public void refill() {
        this.usesLeft = maxUses;
    }

    /**
     * Возвращает количество оставшихся применений фляги.
     *
     * @return оставшиеся заряды
     */
    public int getUsesLeft() {
        return usesLeft;
    }

    /**
     * Возвращает максимальное количество применений фляги.
     *
     * @return максимальные заряды
     */
    public int getMaxUses() {
        return maxUses;
    }
    
    /**
     * Возвращает строковое представление фляги в формате: {@code Estus(текущие/максимум)}.
     *
     * @return строка вида Estus(5/10)
     */
    @Override
    public String toString() {
        String result = name + "(" + usesLeft + "/" + maxUses + ")";
        return result;
    }
    
    /**
     * Возвращает путь к изображению, связанному с флягой.
     *
     * @return URL изображения
     */
    public String getImageURL(){
        return this.imageURL;
    }

    /**
     * Применяет эффект лечения к игроку.
     * Восстанавливает здоровье на {@code healRegenPercentage} от максимального HP.
     * Если HP полное или заряды на нуле — отображается диалоговое окно.
     *
     * @param player игрок, к которому применяется лечение
     */
    public void heal(Player player) {
        if (player.getHp() != player.getMaxHp() && usesLeft != 0){
            int newHp = (int) (player.getHp() + player.getMaxHp() * healRegenPercentage);
            if (newHp > player.getMaxHp()){
                newHp = player.getMaxHp();
            }
            player.setHp(newHp);
            this.usesLeft--;
        } else {
            HealthFullOrNoEstusDialog healthFullOrNoEstusDialog = new HealthFullOrNoEstusDialog(null, true);
            healthFullOrNoEstusDialog.setVisible(true);
        }
    }
    
    /**
     * Метод проверки состояния, пока не реализован.
     *
     * @throws UnsupportedOperationException всегда, так как метод не поддерживается.
     */
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}