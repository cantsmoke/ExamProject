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
        return new BlackSkeleton("Черный скелет", 150, 40, floorNum - 2);
    }

//    @Override
//    public Hound createHound(int floorNum) {
//        return new InfectedHound(floorNum);
//    }
//
//    @Override
//    public Knight createKnight(int floorNum) {
//        return new FireKnight(floorNum);
//    }
    
    public Enemy createRandomEnemy(int floorNum) {
        Random random = new Random();
        int choice = random.nextInt(3); // сколько всего разных врагов
        switch (choice) {
            case 0:
                return (Enemy) createSkeleton(floorNum);
            case 1:
                return (Enemy) createSkeleton(floorNum)/*createHound(floorNum)*/;
            case 2:
                return (Enemy) createSkeleton(floorNum)/*createKnight(floorNum)*/;
            default:
                throw new IllegalArgumentException("Unknown enemy type");
        }
    }
}
