/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.SaintKnight;
import com.mycompany.examproject.Enemies.enemyStructure.*;
import java.util.Random;

/**
 *
 * @author Arseniy
 */
public class EnemySection3Factory implements EnemyFactory{

    @Override
    public Skeleton createSkeleton(int floorNum) {
        return new KingSkeleton("King skeleton", 180, 70, floorNum - 2);
    }

    @Override
    public Hound createHound(int floorNum) {
        return new GiantHound("Giant hound", 170, 80, floorNum - 2);
    }

    @Override
    public Knight createKnight(int floorNum) {
        return new SaintKnight("Saint knight", 220, 90, floorNum - 2);
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
