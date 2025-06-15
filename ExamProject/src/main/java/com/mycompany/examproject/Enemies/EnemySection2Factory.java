/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.BlackSkeleton;
import com.mycompany.examproject.Enemies.enemyStructure.*;
import com.mycompany.examproject.EntityActionType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        int choice = random.nextInt(4);
        switch (choice) {
            case 0:
                return (Enemy) createSkeleton(floorNum);
            case 1:
                return (Enemy) createHound(floorNum);
            case 2:
                return (Enemy) createKnight(floorNum);
            case 3:
                return (Enemy) createCombinedEnemy(floorNum);
            default:
                throw new IllegalArgumentException("Unknown enemy type");
        }
    }
    
    
    public Enemy createCombinedEnemy(int floorNum) {
        Random random = new Random();

        List<Integer> baseTypes = Arrays.asList(0, 1);
        Collections.shuffle(baseTypes);
        int baseType = baseTypes.get(0);

        List<Integer> patternTypes = new ArrayList<>(Arrays.asList(0, 1, 2));
        patternTypes.remove(Integer.valueOf(baseType));
        Collections.shuffle(patternTypes);
        int patternType = patternTypes.get(0);

        Enemy baseEnemy = createEnemyByType(baseType, floorNum);
        String baseName = getEnemyName(baseType);
        String patternName = getEnemyName(patternType);
        baseEnemy.setPattern(getPatternByType(patternType));

        String combinedName = mergeNames(baseName, patternName);
        baseEnemy.setName(combinedName);

        setCombinedEnemyImageSource(baseEnemy);
        
        return baseEnemy;
    }


    private String mergeNames(String name1, String name2) {
        if (name1.compareTo(name2) < 0) {
            return name1 + "-" + name2;
        } else {
            return name2 + "-" + name1;
        }
    }
    
    public Enemy createEnemyByType(int type, int floorNum) {
        switch (type) {
            case 0:
                return (Enemy) createSkeleton(floorNum);
            case 1:
                return (Enemy) createHound(floorNum);
            case 2:
                return (Enemy) createKnight(floorNum);
            default: throw new IllegalArgumentException("Unknown type");
        }
    }
    
    private EntityActionType[] getPatternByType(int type) {
        switch (type) {
            case 0: return CursedSkeleton.getDefaultPattern();
            case 1: return ShadowHound.getDefaultPattern();
            case 2: return RottingKnight.getDefaultPattern();
            default: throw new IllegalArgumentException("Unknown pattern type");
        }
    }
    
    private String getEnemyName(int type) {
        switch (type) {
            case 0: return "Skeleton";
            case 1: return "Hound";
            case 2: return "Knight";
            default: throw new IllegalArgumentException("Unknown type");
        }
    }
    
    private void setCombinedEnemyImageSource(Enemy enemy) {
        String name = enemy.getName();

        if (name == null || !name.contains("-")) {
            throw new IllegalArgumentException("Invalid combined enemy name: " + name);
        }

        String imagePath;

        if (name.equalsIgnoreCase("knight-skeleton")) {
            imagePath = "/ChatGPT Image 5 июн. 2025 г., 17_38_03.png";
        } else if (name.equalsIgnoreCase("hound-skeleton")) {
            imagePath = "/ChatGPT Image 5 июн. 2025 г., 17_34_29.png";
        } else if (name.equalsIgnoreCase("hound-knight")) {
            imagePath = "/imgonline-com-ua-BrightnessContrast-PHkCRxJEidj.jpg";
        } else {
            throw new IllegalArgumentException("Unknown combination: " + name);
        }

        enemy.setIconSource(imagePath);
    }

}