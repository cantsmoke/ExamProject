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
public interface Hound {
    String getName();
    String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);
    String takeLightDamage(Weapon selectedWeapon, int baseDamage);
}