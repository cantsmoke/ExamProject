/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.BlackSkeleton;
import com.mycompany.examproject.Enemies.enemyStructure.*;
import java.util.Random;

/**
 *
 * @author Arseniy
 */
public class EnemySection2Factory implements EnemyFactory{

    @Override
    public Skeleton createSkeleton(int floorNum) {
        return new BlackSkeleton("Black skeleton", 150, 40, floorNum - 2);
    }

    @Override
    public Hound createHound(int floorNum) {
        return new InfectedHound("Infected hound", 100, 45, floorNum - 2);
    }

    @Override
    public Knight createKnight(int floorNum) {
        return new FireKnight("Fire knight", 170, 50, floorNum - 2);
    }
    
    public Enemy createRandomEnemy(int floorNum) {
        Random random = new Random();
        int choice = random.nextInt(3); // сколько всего разных врагов
        switch (choice) {
            case 0:
                return (Enemy) createSkeleton(floorNum);
            case 1:
                return (Enemy) createHound(floorNum);
            case 2:
                return (Enemy) createKnight(floorNum);
            default:
                throw new IllegalArgumentException("Unknown enemy type");
        }
    }
}
