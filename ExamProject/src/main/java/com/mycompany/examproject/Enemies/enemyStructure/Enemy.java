/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies.enemyStructure;

import com.mycompany.examproject.EntityActionType;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.Poison;
import com.mycompany.examproject.Items.Weapon;

/**
 * Абстрактный базовый класс для всех врагов в RPG-игре.
 * <p>
 * Определяет общие характеристики врагов, такие как имя, здоровье, урон, а также
 * методы для взаимодействия с игроком, включая получение урона, уклонение, блокировку
 * и поведение в бою.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public abstract class Enemy {
    
    /**
     * Имя врага.
     */
    String name;
    
    /**
     * Текущее здоровье врага.
     */
    int health;
    
    /**
     * Максимальное здоровье врага.
     */
    private int maxHealth;
    
    /**
     * Урон, наносимый врагом.
     */
    private int damage;

    /**
     * Конструктор для создания врага.
     * <p>
     * Инициализирует имя, здоровье и урон врага с учетом этажа, на котором он появляется.
     *
     * @param name имя врага
     * @param health базовое здоровье врага
     * @param damage базовый урон врага
     * @param floorNum номер этажа, влияющий на характеристики
     */
    public Enemy(String name, int health, int damage, int floorNum) {
        this.name = name;
        this.health = health + floorNum * 10;
        this.maxHealth = this.health;
        this.damage = damage + floorNum * 2;
    }
    
    /**
     * Возвращает имя врага.
     *
     * @return имя врага
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает текущее здоровье врага.
     *
     * @return текущее здоровье
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Возвращает максимальное здоровье врага.
     *
     * @return максимальное здоровье
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Возвращает урон врага.
     *
     * @return урон врага
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Устанавливает имя врага.
     *
     * @param name новое имя врага
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает текущее здоровье врага.
     *
     * @param health новое значение здоровья
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Устанавливает урон врага.
     *
     * @param damage новое значение урона
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    /**
     * Возвращает путь к изображению врага.
     *
     * @return путь к изображению
     */
    public abstract String getIconSource();
    
    /**
     * Устанавливает путь к изображению врага.
     *
     * @param url путь к изображению
     */
    public abstract void setIconSource(String url);
    
    /**
     * Устанавливает шаблон поведения врага.
     *
     * @param newPattern массив действий врага
     */
    public abstract void setPattern(EntityActionType[] newPattern);
    
    /**
     * Возвращает вероятность уклонения врага.
     *
     * @return вероятность уклонения
     */
    public abstract double getDodgeP();
    
    /**
     * Возвращает вероятность блокировки врага.
     *
     * @return вероятность блокировки
     */
    public abstract double getBlockP();
    
    /**
     * Возвращает шаблон поведения врага.
     *
     * @return массив действий врага
     */
    public abstract EntityActionType[] getPattern();

    /**
     * Обрабатывает получение тяжелого урона врагом.
     *
     * @param selectedWeapon выбранное оружие игрока
     * @param baseDamage базовый урон игрока
     * @return описание результата атаки
     */
    public abstract String takeHeavyDamage(Weapon selectedWeapon, int baseDamage);
    
    /**
     * Обрабатывает получение легкого урона врагом.
     *
     * @param selectedWeapon выбранное оружие игрока
     * @param baseDamage базовый урон игрока
     * @return описание результата атаки
     */
    public abstract String takeLightDamage(Weapon selectedWeapon, int baseDamage);
    
    /**
     * Возвращает количество душ, получаемых за победу над врагом.
     *
     * @return количество душ
     */
    public abstract int getSouls();

    /**
     * Наносит урон врагу от бомбы.
     * <p>
     * Уменьшает здоровье врага на величину урона бомбы, не позволяя здоровью стать отрицательным.
     *
     * @param bomb бомба, используемая игроком
     */
    public void takeBombDamage(Bomb bomb) {
        this.health -= bomb.getDamage();
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Наносит урон врагу от яда.
     * <p>
     * Уменьшает здоровье врага на величину урона яда, не позволяя здоровью стать отрицательным.
     *
     * @param poison яд, используемый игроком
     */
    public void takePoisonDamage(Poison poison) {
        this.health -= poison.getDamage();
        if (this.health < 0) {
            this.health = 0;
        }
    }
 
}