/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.GUI.LevelUpdateDialog;
import com.mycompany.examproject.Items.Armor;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potions.EstusBottle;
import com.mycompany.examproject.Items.Weapon;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Items.Weapons.WeaponsStorage;
import com.mycompany.examproject.Map.Room;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий игрока в RPG-игре, реализующий интерфейс {@link Serializable}.
 * <p>
 * Управляет характеристиками игрока, такими как здоровье, выносливость, урон, инвентарь,
 * экипировка и параметры боя. Реализован как синглтон для обеспечения единственного
 * экземпляра игрока в игре.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class Player implements Serializable{
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Текущее здоровье игрока.
     */
    private int hp;
    
    /**
     * Максимальное здоровье игрока.
     */
    private int maxHp;
    
    /**
     * Текущая выносливость игрока.
     */
    private int stamina;
    
    /**
     * Максимальная выносливость игрока.
     */
    private int maxStamina;
    
    /**
     * Общий урон игрока (с учетом оружия).
     */
    private int damage;
    
    /**
     * Количество компонентов для ремонта экипировки.
     */
    private int repairComponents;
    
    /**
     * Базовый урон игрока (без учета оружия).
     */
    private int baseDamage;
    
    /**
     * Вероятность уклонения игрока.
     */
    private double dodgeP;
    
    /**
     * Вероятность блока игрока.
     */
    private double blockP;
    
    /**
     * Вероятность критического удара игрока.
     */
    private double critP;
    
    /**
     * Инвентарь игрока, содержащий экипировку и предметы.
     */
    private List<Equipment> inventory;
    
    /**
     * Выбранное оружие игрока.
     */
    private Weapon selectedWeapon;
    
    /**
     * Выбранная броня игрока.
     */
    private Armor selectedArmor;
    
    /**
     * Максимальный переносимый вес экипировки.
     */
    private int bearableWeight;
    
    /**
     * Общее количество собранных душ.
     */
    private int totalSoulsAmount;
    
    /**
     * Текущее количество душ, доступных для траты.
     */
    private int currentSoulsAmount;
    
    /**
     * Уровень игрока.
     */
    private int level;

    /**
     * Сила игрока, влияющая на урон и переносимый вес.
     */
    private int strength;
    
    /**
     * Ловкость игрока, влияющая на уклонение и критический урон.
     */
    private int agility;
    
    /**
     * Выносливость игрока, влияющая на выносливость и переносимый вес.
     */
    private int endurance;
    
    /**
     * Флаг, указывающий, доступна ли контратака.
     */
    private boolean hasCounterAttack;

    /**
     * Текущая комната, в которой находится игрок.
     */
    private Room currentRoom;

    /**
     * Единственный экземпляр игрока (паттерн синглтон).
     */
    private static Player instance = null;
    
    /**
     * Приватный конструктор для создания игрока.
     * <p>
     * Инициализирует начальные параметры игрока, такие как здоровье, выносливость,
     * инвентарь и экипировку, а также устанавливает стартовую комнату.
     *
     * @param room начальная комната игрока
     */
    private Player(Room room) {
        this.hp = 150;
        this.maxHp = 150;
        
        this.repairComponents = 0;
        
        this.blockP = 0.7;
        
        this.strength = 2;
        this.agility = 2;
        this.endurance = 2;
        
        this.hasCounterAttack = false;
        
        this.inventory = new ArrayList<>();
        this.selectedWeapon = null;
        this.selectedArmor = null;    
        
        this.totalSoulsAmount = 0;
        this.currentSoulsAmount = 0;
        this.level = 1;
        
        this.currentRoom = room;       
        
        setStartingEquipment();
        
        this.bearableWeight = 50;
        
        this.damage = selectedWeapon.getDamage();
        this.baseDamage = 15 + this.strength * 3;
        
        this.stamina = 50 + this.endurance * 5;
        this.maxStamina = stamina;
        
        updateDodgeProbability();
        
        calcCritPossibility();
    }
    
    /**
     * Устанавливает экземпляр игрока.
     *
     * @param p новый экземпляр игрока
     */
    public static void setInstance(Player p) {
        instance = p;
    }

    /**
     * Возвращает вероятность критического удара.
     *
     * @return вероятность критического удара
     */
    public double getCritP(){
        return this.critP;
    }
    
     /**
     * Рассчитывает вероятность критического удара.
     * <p>
     * Вероятность зависит от суммы характеристик силы, ловкости и выносливости.
     */
    public void calcCritPossibility(){
        int sumSkills = this.strength + this.agility + this.endurance;
        this.critP = 0.025 + 0.05 * (sumSkills - 3) / 27.0;
    }
    
    /**
     * Проверяет, доступна ли контратака игроку.
     *
     * @return true, если контратака доступна, иначе false
     */
    public boolean hasCounterAttack() {
        return this.hasCounterAttack;
    }
    
    /**
     * Разблокирует способность контратаковать.
     *
     * @param soulsSpended количество душ, потраченных на разблокировку
     */
    public void unlockCounterAttack(int soulsSpended) {
        this.hasCounterAttack = true;
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;
    }
    
    /**
     * Обновляет вероятность уклонения игрока.
     * <p>
     * Учитывает уровень выносливости и общий вес экипировки.
     */
    public void updateDodgeProbability() {
        int enduranceLevel = this.endurance;
        int equipmentWeight = getTotalEquipmentWeight();
        double baseDodge = 0.5;
        double dodge = baseDodge
                + (enduranceLevel * 0.025)
                - (equipmentWeight * 0.005);
        if (dodge < 0) dodge = 0;
        if (dodge > 1) dodge = 1;

        this.dodgeP = dodge;
    }
    
    /**
     * Возвращает максимальный переносимый вес.
     *
     * @return максимальный переносимый вес
     */
    public int getBearableWeight() {
        return bearableWeight;
    }

    /**
     * Устанавливает максимальный переносимый вес.
     *
     * @param bearableWeight новый максимальный переносимый вес
     */
    public void setBearableWeight(int bearableWeight) {
        this.bearableWeight = bearableWeight;
    }

     /**
     * Возвращает текущий уровень игрока.
     *
     * @return текущий уровень
     */
    public int getLevel(){
        return this.level;
    }
    
    /**
     * Повышает уровень игрока.
     * <p>
     * Отображает диалог повышения уровня и обновляет параметры игрока.
     */
    public void updateLevel(){
        this.level++;
        LevelUpdateDialog levelUpdateDialog = new LevelUpdateDialog(null, true);
        levelUpdateDialog.setVisible(true);
        updateParametrsBasesOnLVL();
    }
    
    /**
     * Обновляет параметры игрока при повышении уровня.
     * <p>
     * Увеличивает максимальное здоровье, выносливость и переносимый вес,
     * сохраняя пропорции текущих значений.
     */
    public void updateParametrsBasesOnLVL() {
        double hpRatio = (double) this.hp / this.maxHp;
        double staminaRatio = (double) this.stamina / this.maxStamina;

        this.maxHp = this.maxHp + 25;
        this.maxStamina = this.maxStamina + 15;

        this.hp = (int) (this.maxHp * hpRatio);
        this.stamina = (int) (this.maxStamina * staminaRatio);
        
        this.bearableWeight = this.bearableWeight + 4;
    }
    
    /**
     * Возвращает общее количество собранных душ.
     *
     * @return общее количество душ
     */
    public int getTotalSoulsAmount(){
        return this.totalSoulsAmount;
    }
    
    /**
     * Добавляет души к общему количеству.
     * <p>
     * Проверяет, достаточно ли душ для повышения уровня.
     *
     * @param souls количество добавляемых душ
     */
    public void addSoulsToTotal(int souls){
        this.totalSoulsAmount = this.totalSoulsAmount + souls;
        int newLevel = 1 + this.totalSoulsAmount / 500;
        
        if (newLevel > getLevel()) {
            updateLevel();
        }
    }
    
    /**
     * Возвращает текущее количество душ, доступных для траты.
     *
     * @return текущее количество душ
     */
    public int getCurrentSoulsAmount(){
        return this.currentSoulsAmount;
    }
    
    /**
     * Добавляет души к текущему количеству.
     *
     * @param souls количество добавляемых душ
     */
    public void addCurrentSoulsAmount(int souls){
        this.currentSoulsAmount = this.currentSoulsAmount + souls;
        addSoulsToTotal(souls);
    }
    
    /**
     * Возвращает количество компонентов для ремонта.
     *
     * @return количество компонентов
     */
    public int getRepairComponents() {
        return repairComponents;
    }

    /**
     * Устанавливает количество компонентов для ремонта.
     *
     * @param repairComponents новое количество компонентов
     */
    public void setRepairComponents(int repairComponents) {
        this.repairComponents = repairComponents;
    }
    
    /**
     * Устанавливает начальную экипировку игрока.
     * <p>
     * Добавляет начальный меч, броню и флягу эстуса в инвентарь, выбирая их
     * как активные.
     */
    public void setStartingEquipment(){
        Sword swordTemplate = WeaponsStorage.getSword(0);
        addItemToInventory(new Sword(swordTemplate.getName(), swordTemplate.getWeight(), swordTemplate.getDamage(), swordTemplate.getDurability()));      
        TrooperArmor trooperArmorTemplate = ArmorStorage.getTrooperArmor(0);
        addItemToInventory(new TrooperArmor(trooperArmorTemplate.getName(), trooperArmorTemplate.getWeight(), trooperArmorTemplate.getDamageReduction(), trooperArmorTemplate.getDurability())); 
        this.selectedWeapon = (Weapon) inventory.get(0);
        this.selectedArmor = (Armor) inventory.get(1);
        this.selectedArmor.setSelected(true);
        this.selectedWeapon.setSelected(true);
        
        addItemToInventory(new EstusBottle());
    }
    
    /**
     * Устанавливает базовый урон игрока.
     *
     * @param newBaseDamage новый базовый урон
     */
    public void setBaseDamage(int newBaseDamage){
        this.baseDamage = newBaseDamage;
    }

    /**
     * Возвращает базовый урон игрока.
     *
     * @return базовый урон
     */
    public int getBaseDamage() {
        return baseDamage;
    }
    
    /**
     * Возвращает выбранное оружие игрока.
     *
     * @return выбранное оружие
     */
    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }
    
    /**
     * Устанавливает новое выбранное оружие.
     *
     * @param selectedWeapon новое оружие
     */
    public void setSelectedWeapon(Weapon selectedWeapon) {
        this.selectedWeapon.setSelected(false);
        this.selectedWeapon = selectedWeapon;
        this.selectedWeapon.setSelected(true);
        
        updateDodgeProbability();
    }

    /**
     * Возвращает выбранную броню игрока.
     *
     * @return выбранная броня
     */
    public Armor getSelectedArmor() {
        return selectedArmor;
    }

    /**
     * Устанавливает новую выбранную броню.
     *
     * @param selectedArmor новая броня
     */
    public void setSelectedArmor(Armor selectedArmor) {
        this.selectedArmor.setSelected(false);
        this.selectedArmor = selectedArmor;
        this.selectedArmor.setSelected(true);
        
        updateDodgeProbability();
    }
    
    /**
     * Наносит урон игроку.
     * <p>
     * Уменьшает прочность брони и здоровье игрока с учетом снижения урона броней.
     *
     * @param enemyDamage урон, наносимый врагом
     */
    public void takeDamage(int enemyDamage){
        this.getSelectedArmor().setDurability(this.getSelectedArmor().getDurability() - 7);
        this.getSelectedArmor().checkStatus();
        this.setHp((int) (this.hp - enemyDamage*(1 - this.getSelectedArmor().getDamageReduction())));
    }
    
    /**
     * Возвращает экземпляр игрока, создавая его, если он еще не существует.
     *
     * @param room начальная комната игрока
     * @return экземпляр игрока
     */
    public static Player getInstance(Room room) {
        if (instance == null) {
            instance = new Player(room);
        }
        return instance;
    }
    
    /**
     * Возвращает существующий экземпляр игрока.
     *
     * @return экземпляр игрока
     * @throws IllegalStateException если игрок не инициализирован
     */
    public static Player getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Player is not initialized! Call getInstance(Room) first.");
        }
        return instance;
    }
    
    /**
     * Сбрасывает экземпляр игрока.
     */
    public static void resetInstance() {
        instance = null;
    }
    
    /**
     * Возвращает текущее здоровье игрока.
     *
     * @return текущее здоровье
     */
    public int getHp() { return hp; }
    
    /**
     * Устанавливает текущее здоровье игрока.
     *
     * @param hp новое значение здоровья
     */
    public void setHp(int hp){
        this.hp = hp;
    }
    
    /**
     * Возвращает максимальное здоровье игрока.
     *
     * @return максимальное здоровье
     */
    public int getMaxHp() { return maxHp; }
    
    /**
     * Возвращает текущую выносливость игрока.
     *
     * @return текущая выносливость
     */
    public int getStamina() { return stamina; }
    
    /**
     * Устанавливает текущую выносливость игрока.
     *
     * @param stamina новое значение выносливости
     */
    public void setStamina(int stamina){
        this.stamina = stamina;
    }
    
    /**
     * Возвращает максимальную выносливость игрока.
     *
     * @return максимальная выносливость
     */
    public int getMaxStamina() { return maxStamina; }
    
    /**
     * Возвращает общий урон игрока.
     *
     * @return общий урон
     */
    public int getDamage(){
        return damage;
    }

    /**
     * Возвращает вероятность уклонения игрока.
     *
     * @return вероятность уклонения
     */
    public double getDodgeP(){
        return this.dodgeP;
    }
    
    /**
     * Возвращает вероятность блока игрока.
     *
     * @return вероятность блока
     */
    public double getBlockP(){
        return this.blockP;
    }
    
    /**
     * Возвращает значение силы игрока.
     *
     * @return сила
     */
    public int getStrength() { return strength; }
    
    /**
     * Возвращает значение ловкости игрока.
     *
     * @return ловкость
     */
    public int getAgility() { return agility; }
    
    /**
     * Возвращает значение выносливости игрока.
     *
     * @return выносливость
     */
    public int getEndurance() { return endurance; }
    
    /**
     * Увеличивает силу игрока.
     * <p>
     * Увеличивает базовый урон, переносимый вес и вероятность критического удара.
     *
     * @param soulsSpended количество потраченных душ
     */
    public void boostStrength(int soulsSpended) {
        this.strength = Math.min(this.strength + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;
        this.baseDamage += 3;
        this.bearableWeight += 1;
        calcCritPossibility();
        
        System.out.println(this.critP);
    }

    /**
     * Увеличивает ловкость игрока.
     * <p>
     * Обновляет вероятность уклонения и критического удара.
     *
     * @param soulsSpended количество потраченных душ
     */
    public void boostAgility(int soulsSpended) {
        this.agility = Math.min(this.agility + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;        
        updateDodgeProbability();
        calcCritPossibility();
        
        System.out.println(this.critP);
    }

    /**
     * Увеличивает выносливость игрока.
     * <p>
     * Увеличивает выносливость, переносимый вес и вероятность критического удара.
     *
     * @param soulsSpended количество потраченных душ
     */
    public void boostEndurance(int soulsSpended) {
        this.endurance = Math.min(this.endurance + 1, 10);
        this.currentSoulsAmount = this.currentSoulsAmount - soulsSpended;      
        this.stamina += 5;
        this.bearableWeight += 1;
        
        calcCritPossibility();
    }

    /**
     * Устанавливает текущую комнату игрока.
     *
     * @param room новая комната
     */
    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    
    /**
     * Возвращает текущую комнату игрока.
     *
     * @return текущая комната
     */
    public Room getCurrentRoom() { return currentRoom; }
    
    /**
     * Добавляет предмет в инвентарь игрока.
     *
     * @param equipment предмет для добавления
     */
    public void addItemToInventory(Equipment equipment) {
        inventory.add(equipment);
    }
    
    /**
     * Возвращает инвентарь игрока.
     *
     * @return список экипировки в инвентаре
     */
    public List<Equipment> getInventory() {
        return inventory;
    }

    /**
     * Рассчитывает общий вес экипировки игрока.
     *
     * @return общий вес выбранной брони и оружия
     */
    public int getTotalEquipmentWeight(){
        int totalWeight = this.selectedArmor.getWeight() + this.selectedWeapon.getWeight();
        return totalWeight;
    }

}