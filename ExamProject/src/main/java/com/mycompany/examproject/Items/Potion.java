/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import java.io.Serializable;

import java.io.Serializable;

/**
 * Абстрактный класс, представляющий зелье в игре.
 * <p>
 * Зелья являются разновидностью {@link Equipment} и могут быть сериализованы.
 * Каждый экземпляр содержит имя и описание, а также реализует методы получения этих свойств.
 * </p>
 *
 * @author Arseniy
 * @see Equipment
 */
public abstract class Potion implements Equipment, Serializable {

    /**
     * Уникальный идентификатор класса для сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Имя зелья.
     */
    protected String name;

    /**
     * Описание зелья (эффекты, способ применения).
     */
    private String description;

    /**
     * Создаёт новое зелье с указанными именем и описанием.
     *
     * @param name        имя зелья
     * @param description описание или эффект зелья
     */
    public Potion(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает имя зелья.
     *
     * @return имя зелья
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание зелья.
     *
     * @return описание зелья
     */
    public String getDescription() {
        return description;
    }
}