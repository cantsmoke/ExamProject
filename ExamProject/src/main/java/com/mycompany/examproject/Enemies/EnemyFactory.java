/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.*;
/**
 * Интерфейс фабрики для создания врагов в RPG-игре.
 * <p>
 * Определяет методы для создания различных типов врагов ({@link Skeleton}, {@link Hound}, {@link Knight})
 * в зависимости от этажа, на котором они появляются.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
interface EnemyFactory {
    /**
     * Создает врага типа {@link Skeleton} для указанного этажа.
     *
     * @param floorNum номер этажа, от которого зависят хар-ки
     * @return экземпляр врага {@link Skeleton}
     */
    Skeleton createSkeleton(int floorNum);
    
    /**
     * Создает врага типа {@link Hound} для указанного этажа.
     *
     * @param floorNum номер этажа, от которого зависят хар-ки
     * @return экземпляр врага {@link Hound}
     */
    Hound createHound(int floorNum);
    
    /**
     * Создает врага типа {@link Knight} для указанного этажа.
     *
     * @param floorNum номер этажа, от которого зависят хар-ки
     * @return экземпляр врага {@link Knight}
     */
    Knight createKnight(int floorNum);
}