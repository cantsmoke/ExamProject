/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Potions;

import com.mycompany.examproject.GUI.HealthFullOrNoEstusDialog;
import com.mycompany.examproject.GUI.StaminaIsFullDialog;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Player;
import java.io.Serializable;
import java.util.Random;

/**
 * Представляет зелье восстановления выносливости {@code StaminaPotion}, расширяющее класс {@link Potion}.
 * Может быть двух размеров — «Small» и «Big», с разной величиной восстановления.
 * Размер определяется случайно при создании экземпляра.
 *
 * <p>Используется для восстановления выносливости игрока (Stamina), если она не максимальна.</p>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * StaminaPotion potion = new StaminaPotion();
 * potion.addStamina(Player.getInstance());
 * }</pre>
 * 
 * <p>При использовании:</p>
 * <ul>
 *   <li>Если выносливость не максимальна — восстанавливает указанное количество и удаляется из инвентаря.</li>
 *   <li>Если выносливость полная — открывается предупреждающее диалоговое окно.</li>
 * </ul>
 * 
 * @author Arseniy
 */
public class StaminaPotion extends Potion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /** Размер зелья: "Small" или "Big". */
    private String size;

    /** Количество выносливости, восстанавливаемой при использовании. */
    private int staminaRegenAmount;

    /** Путь к изображению зелья, используемому в UI. */
    private String imageURL;

    /**
     * Создает зелье случайного размера ("Small" или "Big"):
     * <ul>
     *   <li>"Small": восстанавливает 15 выносливости</li>
     *   <li>"Big": восстанавливает 30 выносливости</li>
     * </ul>
     * Также устанавливает соответствующее изображение.
     */
    public StaminaPotion() {
        super("Stamina potion", "Regenerates stamina.");
        
        if (new Random().nextBoolean()) {
            this.size = "Big";
            this.staminaRegenAmount = 30;
            imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_27_27.png";
        } else {
            this.size = "Small";
            this.staminaRegenAmount = 15;
            imageURL = "/ChatGPT Image 14 июн. 2025 г., 15_25_39.png";
        }
    }
    
    /**
     * Возвращает размер зелья.
     *
     * @return строка: "Small" или "Big"
     */
    public String getSize() {
        return size;
    }

    /**
     * Возвращает количество выносливости, которое восстанавливает зелье.
     *
     * @return числовое значение восстановления выносливости
     */
    public int getStaminaRegenAmount() {
        return this.staminaRegenAmount;
    }

    /**
     * Возвращает путь к изображению зелья.
     *
     * @return строка с URL изображения
     */
    public String getImageURL() {
        return this.imageURL;
    }
    
    /**
     * Применяет эффект зелья к игроку:
     * восстанавливает выносливость и удаляет зелье из инвентаря.
     * Если выносливость уже максимальна — отображает диалоговое окно.
     *
     * @param player игрок, к которому применяется зелье
     */
    public void addStamina(Player player) {
        if (player.getStamina() != player.getMaxStamina()){
            int newStamina = (int) (player.getStamina() + this.staminaRegenAmount);
            if (newStamina > player.getMaxStamina()){
                newStamina = player.getMaxStamina();
            }
            player.setStamina(newStamina);
            Player.getInstance().getInventory().remove(this);
        } else {
            StaminaIsFullDialog staminaIsFullDialog = new StaminaIsFullDialog(null, true);
            staminaIsFullDialog.setVisible(true);
        }
    }
    
    /**
     * Возвращает строковое описание зелья в формате:
     * {@code Stamina potion(Small)} или {@code Stamina potion(Big)}.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        String result = name + "("+ size +")";
        return result;
    }
    
    /**
     * Метод проверки состояния предмета. В данной реализации не используется.
     *
     * @throws UnsupportedOperationException всегда.
     */
    @Override
    public void checkStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}