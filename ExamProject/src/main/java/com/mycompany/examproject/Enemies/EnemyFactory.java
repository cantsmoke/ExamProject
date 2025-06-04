/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.*;

/**
 *
 * @author Arseniy
 */
interface EnemyFactory {
    Skeleton createSkeleton(int floorNum);
    Hound createHound(int floorNum);
    Knight createKnight(int floorNum);
}