/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Weapon;

/**
 * Класс, представляющий босса в игре.
 * <p>
 * Наследуется от {@link Enemy}. Каждый босс ассоциирован с определённым этажом и имеет уникальные характеристики.
 * </p>
 *
 * @author Arseniy
 */
public class Boss extends Enemy {
    /**
     * Номер этажа, на котором находится босс.
     */
    private int floorNum;

    /**
     * Создаёт нового босса с заданными параметрами.
     *
     * @param name    имя босса
     * @param health  количество здоровья босса
     * @param damage  урон, наносимый боссом
     * @param floorNum номер этажа, на котором находится босс
     */
    public Boss(String name, int health, int damage, int floorNum) {
        super(name, health, damage, floorNum);
        this.floorNum = floorNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIconSource() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPattern(EntityActionType[] newPattern) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconSource(String url) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDodgeP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityActionType[] getPattern() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String takeHeavyDamage(Weapon selectedWeapon, int baseDamage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String takeLightDamage(Weapon selectedWeapon, int baseDamage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Возвращает количество "душ", выпадающих за победу над этим боссом.
     * <p>
     * Подсчитывается как 500 плюс 50, умноженное на номер этажа.
     * </p>
     *
     * @return количество душ, получаемых за победу над боссом
     */
    @Override
    public int getSouls() {
        return 500 + 50 * floorNum;
    }
}