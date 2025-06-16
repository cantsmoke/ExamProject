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
 * Фабрика для создания врагов второго сектора (этажи 4-5) в RPG-игре.
 * <p>
 * Реализует интерфейс {@link EnemyFactory} для создания врагов типов {@link Skeleton},
 * {@link Hound} и {@link Knight}, а также случайных и комбинированных врагов с уникальными
 * характеристиками и поведением.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class EnemySection2Factory implements EnemyFactory{

    /**
     * Создает врага типа {@link Skeleton} для указанного этажа.
     *
     * @param floorNum номер этажа, на котором появляется враг
     * @return экземпляр врага {@link BlackSkeleton}
     */
    @Override
    public Skeleton createSkeleton(int floorNum) {
        return new BlackSkeleton("Black skeleton", 150, 40, floorNum - 2);
    }

    /**
     * Создает врага типа {@link Hound} для указанного этажа.
     *
     * @param floorNum номер этажа, на котором появляется враг
     * @return экземпляр врага {@link InfectedHound}
     */
    @Override
    public Hound createHound(int floorNum) {
        return new InfectedHound("Infected hound", 100, 45, floorNum - 2);
    }

    /**
     * Создает врага типа {@link Knight} для указанного этажа.
     *
     * @param floorNum номер этажа, на котором появляется враг
     * @return экземпляр врага {@link FireKnight}
     */
    @Override
    public Knight createKnight(int floorNum) {
        return new FireKnight("Fire knight", 170, 50, floorNum - 2);
    }
    
    /**
     * Создает случайного врага для указанного этажа.
     * <p>
     * С равной вероятностью возвращает врага типа {@link Skeleton}, {@link Hound},
     * {@link Knight} или комбинированного врага.
     *
     * @param floorNum номер этажа, на котором появляется враг
     * @return экземпляр случайного врага
     * @throws IllegalArgumentException если тип врага неизвестен
     */
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
    
    /**
     * Создает комбинированного врага для указанного этажа.
     * <p>
     * Комбинирует базового врага ({@link Skeleton} или {@link Hound}) с шаблоном
     * поведения другого типа врага, задавая уникальное имя и изображение.
     *
     * @param floorNum номер этажа, на котором появляется враг
     * @return экземпляр комбинированного врага
     * @throws IllegalArgumentException если комбинация врагов недопустима
     */
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

    /**
     * Объединяет имена двух врагов в одно.
     * <p>
     * Имена сортируются лексикографически для консистентности.
     *
     * @param name1 имя первого врага
     * @param name2 имя второго врага
     * @return объединенное имя
     */
    private String mergeNames(String name1, String name2) {
        if (name1.compareTo(name2) < 0) {
            return name1 + "-" + name2;
        } else {
            return name2 + "-" + name1;
        }
    }
    
    /**
     * Создает врага по указанному типу.
     *
     * @param type тип врага (0 для {@link Skeleton}, 1 для {@link Hound}, 2 для {@link Knight})
     * @param floorNum номер этажа
     * @return экземпляр врага
     * @throws IllegalArgumentException если тип врага неизвестен
     */
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
    
    /**
     * Возвращает шаблон поведения врага по указанному типу.
     * <p>
     * Использует шаблоны первого сектора для совместимости.
     *
     * @param type тип врага (0 для {@link Skeleton}, 1 для {@link Hound}, 2 для {@link Knight})
     * @return массив шаблонов поведения
     * @throws IllegalArgumentException если тип шаблона неизвестен
     */
    private EntityActionType[] getPatternByType(int type) {
        switch (type) {
            case 0: return BlackSkeleton.getDefaultPattern();
            case 1: return InfectedHound.getDefaultPattern();
            case 2: return FireKnight.getDefaultPattern();
            default: throw new IllegalArgumentException("Unknown pattern type");
        }
    }
    
    /**
     * Возвращает имя врага по указанному типу.
     *
     * @param type тип врага (0 для Skeleton, 1 для Hound, 2 для Knight)
     * @return имя врага
     * @throws IllegalArgumentException если тип врага неизвестен
     */
    private String getEnemyName(int type) {
        switch (type) {
            case 0: return "Skeleton";
            case 1: return "Hound";
            case 2: return "Knight";
            default: throw new IllegalArgumentException("Unknown type");
        }
    }
    
    /**
     * Устанавливает путь к изображению для комбинированного врага.
     *
     * @param enemy комбинированный враг
     * @throws IllegalArgumentException если имя врага недопустимо или комбинация неизвестна
     */
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