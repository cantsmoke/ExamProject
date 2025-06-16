/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Items.Weapons;

import java.util.List;

/**
 * Хранилище предопределённых видов оружия, разбитых по типам:
 * {@link Sword}, {@link Bow}, {@link Hammer}, {@link Spear}, {@link Axe}.
 * Используется для генерации оружия в игре — например, при старте, в магазинах, луте или наградах.
 *
 * <p>Каждая коллекция объявлена как {@code static final}, что обеспечивает её глобальную доступность 
 * и неизменяемость во время выполнения программы.</p>
 *
 * <p>Типы оружия:</p>
 * <ul>
 *     <li><b>Sword</b> — мечи, универсальное холодное оружие с хорошим балансом урона, веса и прочности.</li>
 *     <li><b>Bow</b> — луки и арбалеты, дальнобойное оружие с высоким уроном и прочностью.</li>
 *     <li><b>Hammer</b> — молоты, тяжёлое оружие с высоким уроном и небольшим запасом прочности.</li>
 *     <li><b>Spear</b> — копья, сбалансированное оружие со средней дальностью и характеристиками.</li>
 *     <li><b>Axe</b> — топоры, сильный урон с хорошей проникающей способностью, но весомее мечей.</li>
 * </ul>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * List<Sword> swordList = WeaponsStorage.swords;
 * Weapon randomBow = WeaponsStorage.bows.get(3);
 * }</pre>
 *
 * @author Arseniy
 */
public class WeaponsStorage {
    
    /**
     * Коллекция предопределённых экземпляров {@link Sword}.
     */
    private static final List<Sword> swords = List.of(
        new Sword("Forsaken Dagger of the Lost", 2, 10, 60),
        new Sword("Blade of Withering Ashes", 2, 15, 65),
        new Sword("Funeral Sword of the Ancient Monk", 2, 20, 70),
        new Sword("Shattered Saber of the Abyss", 3, 25, 80),
        new Sword("Sinful Dirk of the Executioner", 3, 30, 90),
        new Sword("Pale Pilgrim's Sword", 3, 40, 100),
        new Sword("Grim Cleaver of the Fallen King", 4, 45, 110),
        new Sword("Crimson Glaive of the Shadow Matron", 5, 50, 120),
        new Sword("Cursed Fang of the Deep Tomb", 4, 55, 100),
        new Sword("Doomblade of the Silent Sepulcher", 5, 60, 130),
        new Sword("Hellfire Edge of the Damned Lord", 5, 70, 140),
        new Sword("Sacred Blade of Hope", 7, 77, 177),
        new Sword("Blackfang of the Dread Prophet", 6, 85, 150),
        new Sword("Oblivion Greatsword of the Voidwalker", 8, 100, 160),
        new Sword("Eclipse Slayer of the Forgotten God", 9, 120, 200)
    );

    /**
     * Коллекция предопределённых экземпляров {@link Bow}.
     */
    private static final List<Bow> bows = List.of(
        new Bow("Whispering Shortbow of the Hollow", 2, 15, 120),
        new Bow("Dark Falcon Bow", 3, 25, 100),
        new Bow("Moonlit Hunter's Bow", 3, 30, 130),
        new Bow("Forest Bow of the River Maiden", 3, 40, 150),
        new Bow("Runic Shortbow of the Watcher", 2, 50, 200),
        new Bow("Ashen Warbow of the Exile", 4, 55, 180),
        new Bow("Widowmaker Longbow", 4, 60, 210),
        new Bow("Phantom Recurve of the Abyss", 4, 65, 230),
        new Bow("Seeker's Greatbow of Lost Light", 5, 70, 240),
        new Bow("Voidpiercer Longbow", 5, 75, 250),
        new Bow("Twilight Longbow of the Forsaken", 5, 80, 260),
        new Bow("Sunpiercer Bow of the Forgotten Hero", 6, 90, 300),
        new Bow("Ironcross of the Silent Inquisitor", 7, 100, 320),
        new Bow("Bonecrusher Arbalest of the Tyrant", 8, 110, 350),
        new Bow("Doomreaver Siege Crossbow", 9, 120, 400)
    );

    /**
     * Коллекция предопределённых экземпляров {@link Hammer}.
     */
    private static final List<Hammer> hammers = List.of(
        new Hammer("Emaciated Butcher's Bone Hammer", 10, 60, 40),
        new Hammer("Cracked Stone Sledge of the Exile", 12, 65, 35),
        new Hammer("Gravedigger's Giant Maul", 15, 70, 30),
        new Hammer("Iron Fist of the Ashen Knight", 13, 75, 40),
        new Hammer("Silent Crusher of the Lost Saint", 14, 80, 45),
        new Hammer("Bloodbound Maul of the Betrayer", 16, 85, 35),
        new Hammer("Widowmaker Warhammer", 17, 90, 50),
        new Hammer("Twilight Smasher of the Abyss", 18, 95, 40),
        new Hammer("Titanbone Mallet of the Forsaken", 19, 100, 50),
        new Hammer("Soulrender Great Maul", 20, 110, 45),
        new Hammer("Doomhammer of the Silent King", 21, 120, 55),
        new Hammer("Hellfire Anvil of the Damned", 22, 130, 50),
        new Hammer("Oblivion Warhammer of the Void Herald", 23, 140, 60),
        new Hammer("Eclipse Breaker of the Fallen God", 24, 150, 55),
        new Hammer("Cataclysm Maul of the World Ender", 25, 160, 60)
    );

    /**
     * Коллекция предопределённых экземпляров {@link Spear}.
     */
    private static final List<Spear> spears = List.of(
        new Spear("Shaggy Spear of the Wolf Order", 7, 60, 50),
        new Spear("Coastal Spear of Lightning", 8, 65, 60),
        new Spear("Drowning Pilgrim's Spike", 9, 70, 70),
        new Spear("Runed Pike of the Forgotten Legion", 8, 75, 80),
        new Spear("Venombite Fang Spear", 9, 80, 90),
        new Spear("Moonlit Trident of the Abyss", 10, 85, 95),
        new Spear("Crimson Halberd of the Exiled Knight", 10, 90, 100),
        new Spear("Silent Impaler of the Pale Warden", 11, 95, 100),
        new Spear("Stormpiercer Lance", 11, 100, 105),
        new Spear("Phantom Pike of the Hollow", 12, 105, 110),
        new Spear("Iron Fang of the Tyrant Prince", 12, 110, 115),
        new Spear("Obsidian Halberd of the Depths", 13, 115, 115),
        new Spear("Sunpiercer Lance of the Damned Herald", 14, 120, 120),
        new Spear("Voidfang Spear of the Dark Prophet", 15, 125, 120),
        new Spear("Eclipse Impaler of the World Eater", 15, 130, 120)
    );

    /**
     * Коллекция предопределённых экземпляров {@link Axe}.
     */
    private static final List<Axe> axes = List.of( 
        new Axe("Crescent Axe of the Exile", 10, 40, 70),
        new Axe("Twisted Hatchet of the Hollow", 8, 45, 60),
        new Axe("Bonecleaver of the Drowned Tribe", 9, 50, 65),
        new Axe("Ashfang Axe of the Blackened Grove", 10, 55, 70),
        new Axe("Iron Bastion Breaker", 15, 60, 70),
        new Axe("Wraithsplitter Axe", 11, 65, 75),
        new Axe("Blightborn War Axe", 12, 70, 80),
        new Axe("Crimson Cleaver of the Lost Flame", 12, 75, 85),
        new Axe("Soulrender Hatchet", 13, 80, 90),
        new Axe("Executioner's Doomaxe", 14, 85, 85),
        new Axe("Frostbite Axe of the Forsaken Watch", 14, 90, 90),
        new Axe("Stormbreaker Axe of the Abyss", 15, 95, 95),
        new Axe("Hellcleaver of the Crimson King", 16, 105, 100),
        new Axe("Titan Maulaxe of the Eternal Siege", 17, 115, 100),
        new Axe("Voidbite Axe of the Doomsworn", 18, 120, 100)
    );
    
    /**
    * Возвращает экземпляр {@link Sword} по указанному индексу.
    *
    * @param index индекс меча в списке {@code swords}
    * @return экземпляр {@link Sword} по данному индексу
    * @throws IndexOutOfBoundsException если индекс вне диапазона допустимых значений
    */
   public static Sword getSword(int index) { 
       return swords.get(index); 
   }

   /**
    * Возвращает экземпляр {@link Bow} по указанному индексу.
    *
    * @param index индекс лука в списке {@code bows}
    * @return экземпляр {@link Bow} по данному индексу
    * @throws IndexOutOfBoundsException если индекс вне диапазона допустимых значений
    */
   public static Bow getBow(int index) { 
       return bows.get(index); 
   }

   /**
    * Возвращает экземпляр {@link Hammer} по указанному индексу.
    *
    * @param index индекс молота в списке {@code hammers}
    * @return экземпляр {@link Hammer} по данному индексу
    * @throws IndexOutOfBoundsException если индекс вне диапазона допустимых значений
    */
   public static Hammer getHammer(int index) { 
       return hammers.get(index); 
   }

   /**
    * Возвращает экземпляр {@link Spear} по указанному индексу.
    *
    * @param index индекс копья в списке {@code spears}
    * @return экземпляр {@link Spear} по данному индексу
    * @throws IndexOutOfBoundsException если индекс вне диапазона допустимых значений
    */
   public static Spear getSpear(int index) { 
       return spears.get(index); 
   }

   /**
    * Возвращает экземпляр {@link Axe} по указанному индексу.
    *
    * @param index индекс топора в списке {@code axes}
    * @return экземпляр {@link Axe} по данному индексу
    * @throws IndexOutOfBoundsException если индекс вне диапазона допустимых значений
    */
   public static Axe getAxe(int index) { 
       return axes.get(index); 
   }

}