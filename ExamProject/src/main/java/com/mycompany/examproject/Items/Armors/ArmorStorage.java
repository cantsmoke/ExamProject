/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Armors;

import java.util.List;

/**
 * Хранилище предопределённых комплектов брони, разбитых по типам:
 * {@link LightArmor}, {@link TrooperArmor} и {@link HeavyArmor}.
 * Используется для генерации снаряжения в игре, например, при старте или луте.
 *
 * <p>Каждый список является {@code static final}, то есть доступен глобально и не изменяется.</p>
 * 
 * <p>Типы брони:</p>
 * <ul>
 *     <li><b>LightArmor</b> — лёгкая броня, минимальный вес и защита, даёт высокий шанс уклонения.</li>
 *     <li><b>TrooperArmor</b> — сбалансированная броня: умеренная защита и вес.</li>
 *     <li><b>HeavyArmor</b> — тяжёлая броня, высокая защита и прочность, но большой вес.</li>
 * </ul>
 * 
 * <p>Каждый элемент списка создаётся заранее с уникальными характеристиками, названиями и параметрами.</p>
 * 
 * <p>Пример использования:</p>
 * <pre>{@code
 * List<LightArmor> lightList = ArmorStorage.lightArmor;
 * Armor random = ArmorStorage.trooperArmor.get(2);
 * }</pre>
 * 
 * @author Arseniy
 */
public class ArmorStorage {
    
    /**
     * Коллекция предопределённых экземпляров {@link LightArmor}.
     * Подходит для классов, ориентированных на скорость и уклонение.
     */
    public static final List<LightArmor> lightArmor = List.of(
        new LightArmor("Shadowthread Vest", 5, 0.10, 50),
        new LightArmor("Whispering Leather", 6, 0.12, 55),
        new LightArmor("Silent Wind Cloak", 5, 0.13, 60),
        new LightArmor("Ethereal Hunter's Garb", 4, 0.15, 45),
        new LightArmor("Wraithweave Jerkin", 5, 0.16, 50),
        new LightArmor("Ghoststep Armor", 6, 0.17, 52),
        new LightArmor("Duskrunner's Mail", 6, 0.18, 55),
        new LightArmor("Phantom Skin Tunic", 7, 0.20, 60),
        new LightArmor("Veil of the Forsaken", 7, 0.22, 65),
        new LightArmor("Flickering Shade Armor", 6, 0.23, 62),
        new LightArmor("Silent Stalker’s Jerkin", 5, 0.25, 55),
        new LightArmor("Moonshadow Coat", 6, 0.27, 58),
        new LightArmor("Hollowwind Vest", 7, 0.28, 60),
        new LightArmor("Glade Runner's Jacket", 6, 0.30, 62),
        new LightArmor("Veil of Lost Echoes", 8, 0.32, 65)
    );

    /**
     * Коллекция предопределённых экземпляров {@link TrooperArmor}.
     * Используется как универсальный тип для сбалансированной защиты.
     */
    public static final List<TrooperArmor> trooperArmor = List.of(
        new TrooperArmor("Ironbound Mail", 15, 0.30, 120),
        new TrooperArmor("Steelguard Plate", 17, 0.32, 125),
        new TrooperArmor("Gravewatcher Harness", 16, 0.34, 130),
        new TrooperArmor("Ashen Legion Vest", 18, 0.36, 135),
        new TrooperArmor("Dreadborn Hauberk", 16, 0.37, 140),
        new TrooperArmor("Voidguard Armor", 19, 0.38, 145),
        new TrooperArmor("Ravensteel Cuirass", 17, 0.40, 150),
        new TrooperArmor("Silent Sentinel's Plate", 20, 0.41, 155),
        new TrooperArmor("Blackthorn Mail", 18, 0.43, 160),
        new TrooperArmor("Stormbreaker Harness", 19, 0.45, 165),
        new TrooperArmor("Frostguard Hauberk", 20, 0.46, 170),
        new TrooperArmor("Hellforge Cuirass", 21, 0.48, 175),
        new TrooperArmor("Oblivion Knight Plate", 22, 0.50, 180),
        new TrooperArmor("Sunpiercer Mail", 21, 0.52, 185),
        new TrooperArmor("Eclipse Guard Armor", 23, 0.55, 190)
    );

    /**
     * Коллекция предопределённых экземпляров {@link HeavyArmor}.
     * Максимально защищающая, но самая тяжёлая броня в игре.
     */
    public static final List<HeavyArmor> heavyArmor = List.of(
        new HeavyArmor("Titan's Warplate", 48, 0.4, 250),
        new HeavyArmor("Cataclysm Greatplate", 49, 0.42, 260),
        new HeavyArmor("Obsidian Juggernaut", 49, 0.45, 270),
        new HeavyArmor("Hellfire Bastion", 50, 0.43, 280),
        new HeavyArmor("Ironclad Colossus", 52, 0.44, 290),
        new HeavyArmor("Doomhammer Plate", 52, 0.46, 300),
        new HeavyArmor("Eclipse Defender", 54, 0.47, 310),
        new HeavyArmor("Voidbreaker Armor", 54, 0.48, 320),
        new HeavyArmor("Blacksteel Siegeplate", 56, 0.5, 330),
        new HeavyArmor("Stormwall Armor", 56, 0.52, 340),
        new HeavyArmor("Gravewarden’s Bulk", 60, 0.54, 350),
        new HeavyArmor("Sunforged Greatplate", 65, 0.56, 360),
        new HeavyArmor("Frostbane Armor", 65, 0.58, 370),
        new HeavyArmor("Dreadnought Bastion", 70, 0.6, 380),
        new HeavyArmor("Colossus of the Abyss", 75, 0.65, 400)
    );
}