/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.Items.Weapon;

/**
 *
 * @author Arseniy
 */
public interface Skeleton {
    String getName();
    void takeDamage(Weapon selectedWeapon);
}