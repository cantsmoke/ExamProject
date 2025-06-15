/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items;

import java.io.Serializable;

/**
 *
 * @author Arseniy
 */
public abstract class Potion implements Equipment, Serializable{

    private static final long serialVersionUID = 1L;

    protected String name;
    protected String description;

    public Potion(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}