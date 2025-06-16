/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.Enemies.enemyStructure.Hound;
import com.mycompany.examproject.Enemies.enemyStructure.Knight;
import com.mycompany.examproject.Enemies.enemyStructure.Mimic;
import com.mycompany.examproject.GUI.PotionAlreadyUsedDialog;
import com.mycompany.examproject.Enemies.enemyStructure.SirenOfOblivion;
import com.mycompany.examproject.Enemies.enemyStructure.Skeleton;
import com.mycompany.examproject.Enemies.enemyStructure.TheUnhallowedArchon;
import com.mycompany.examproject.GUI.*;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.HeavyArmor;
import com.mycompany.examproject.Items.Armors.LightArmor;
import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.Poison;
import com.mycompany.examproject.Items.Potions.StaminaPotion;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Spear;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Items.Weapons.WeaponsStorage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
/**
 * Класс, управляющий боевой системой между игроком и врагом в пошаговой RPG.
 * <p>
 * Этот класс отвечает за инициализацию боя, управление действиями игрока и врага,
 * обработку атак, уклонений, блоков, использование предметов, а также проверку
 * условий победы или поражения. Интерфейс боя отображается через {@link BattleForm}.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class Fight {

    /**
     * Игрок, участвующий в бою.
     */
    private Player player;

    /**
     * Враг, против которого ведется бой.
     */
    private Enemy enemy;

    /**
     * Форма интерфейса боя, отображающая информацию о бое.
     */
    private static BattleForm battleForm;

    /**
     * Индекс текущего шаблона действий врага.
     */
    private int currentEnemyActionPattern = 0;

    /**
     * Оставшаяся длительность действия яда на враге.
     */
    private int poisonDuration = 0;

    /**
     * Объект яда, примененного к врагу, или null, если яд не активен.
     */
    private Poison poison = null;

    /**
     * Конструктор, инициализирующий бой между игроком и врагом.
     * <p>
     * Создает новый бой, инициализирует интерфейс {@link BattleForm}, обновляет
     * отображаемые данные и делает форму видимой.
     *
     * @param player игрок, участвующий в бою
     * @param enemy враг, против которого ведется бой
     */
    
    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        Random random = new Random();
        
        BattleForm battleForm = new BattleForm();
        battleForm.updateLabels(player, enemy);
        battleForm.setVisible(true);
        
        this.battleForm = battleForm;
    }
    
    /**
     * Скрывает интерфейс боя.
     * <p>
     * Делает форму боя невидимой и обновляет отображаемые данные.
     */
    public void hideBattleForm(){
        this.battleForm.setVisible(false);
        this.battleForm.updateLabels(player, enemy);
    }
    
    /**
     * Показывает интерфейс боя.
     * <p>
     * Делает форму боя видимой и обновляет отображаемые данные.
     */
    public void showBattleForm(){
        this.battleForm.setVisible(true);
        this.battleForm.updateLabels(player, enemy);
    }
    
    /**
     * Рассчитывает штраф к уклонению на основе веса экипировки игрока.
     * <p>
     * Штраф увеличивается с увеличением веса экипировки от минимального
     * значения (6.0 кг) до максимального (100.0 кг).
     *
     * @param currentWeight текущий вес экипировки игрока
     * @return штраф к уклонению (от 0.0 до 0.3)
     */
    public static double calculateEquipmentDodgePenalty(double currentWeight) {
        double minWeight = 6.0;
        double maxWeight = 100.0;
        double maxPenalty = 0.3;
        if (currentWeight <= minWeight) {
            return 0.0;
        }
        if (currentWeight >= maxWeight) {
            return maxPenalty;
        }
        double penaltyPerKg = maxPenalty / (maxWeight - minWeight);
        return (currentWeight - minWeight) * penaltyPerKg;
    }

    /**
     * Обрабатывает атаку игрока.
     * <p>
     * Открывает диалог выбора типа атаки ({@link AttackVariantsDialog}) и в
     * зависимости от выбора вызывает обработку легкой или тяжелой атаки.
     */
    public void handlePlayerAttackAction() {
        AttackVariantsDialog attackVariantDialog = new AttackVariantsDialog(battleForm, true, player.getBaseDamage() + player.getSelectedWeapon().getDamage());
        attackVariantDialog.setVisible(true);
        
        EntityActionType playerAttackType = attackVariantDialog.getAttackType();
        
        if(playerAttackType == EntityActionType.LIGHT_ATTACK){
            handlePlayerLightAttack();
        } else if (playerAttackType == EntityActionType.HEAVY_ATTACK){
            handlePlayerHeavyAttack();
        }
    }
    
    /**
     * Выбирает действие врага в ответ на атаку игрока.
     * <p>
     * Случайно выбирает одно из возможных действий (уклонение, блок или действие
     * из шаблона врага) и обновляет индекс текущего шаблона.
     *
     * @return выбранное действие врага
     */
    private EntityActionType chooseEnemyActionForPlayersAttack() {
        EntityActionType[] actions = {
            EntityActionType.DODGE,
            EntityActionType.BLOCK,
            enemy.getPattern()[currentEnemyActionPattern],
            enemy.getPattern()[currentEnemyActionPattern]
        };
        int idx = new java.util.Random().nextInt(actions.length);
        if (idx == 2 || idx == 3) {
            currentEnemyActionPattern++;
            if (currentEnemyActionPattern >= enemy.getPattern().length) {
                currentEnemyActionPattern = 0;
            }
        }
        EntityActionType randomAction = actions[idx];
        return randomAction;
    }
    
    /**
     * Обрабатывает легкую атаку игрока.
     * <p>
     * Проверяет наличие достаточной выносливости, обновляет параметры оружия и
     * выносливости игрока, определяет реакцию врага (уклонение, блок или контратака)
     * и наносит урон при необходимости. Логирует результат в {@link BattleForm}.
     */
    private void handlePlayerLightAttack() {
        EntityActionType enemyActionForPlayersAttack = chooseEnemyActionForPlayersAttack();
        
        if (player.getStamina() < 5){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 5);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        player.setStamina(player.getStamina() - 5);
        
        player.getSelectedWeapon().setDurability(player.getSelectedWeapon().getDurability() - 2);
        player.getSelectedWeapon().checkStatus();
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() + calculateEquipmentDodgePenalty(player.getTotalEquipmentWeight());
            if(Math.random() < enemyDodgePossibility){
                String logPart = enemy.getName() + " dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to dodge!";
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() + 0.15;
            if(Math.random() < enemyBlockPossibility){
                String logPart = enemy.getName() + " blocked your light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to block!";
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK) {
            
            if (isEnemyFasterInLightAttack(enemy, player)) {
                String logPart1 = enemy.getName() + " was faster and interrupted your light attack!";
                String logPart2 = "You took " + (int) (enemy.getDamage() * 0.8) + " damage!";
                int damageTaken = (int) (enemy.getDamage() * 0.8);
                player.takeDamage(damageTaken);
                battleForm.appendToLogArea(logPart1);
                battleForm.appendToLogArea(logPart2);
            } else {
                String logPart = "Both used light attacks simultaneously — no damage taken!";
                battleForm.appendToLogArea(logPart);
            }
            
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            if (Math.random() < player.getCritP()){
                String logPart1 = enemy.getName() + " used heavy attack, but your light attack was faster!";
                String logPart2 = "You landed critical hit on the " + enemy.getName() + " when he tried to use heavy attack!";
                String logPart3 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                battleForm.appendToLogArea(logPart1);
                battleForm.appendToLogArea(logPart2);
                battleForm.appendToLogArea(logPart3);
            } else {
                String logPart1 = enemy.getName() + " used heavy attack, but your light attack was faster!";
                String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), player.getBaseDamage());
                battleForm.appendToLogArea(logPart1);
                battleForm.appendToLogArea(logPart2);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    /**
     * Проверяет, быстрее ли враг при легкой атаке.
     * <p>
     * Учитывает тип врага, вес экипировки игрока и случайную вероятность,
     * чтобы определить, прерывает ли враг атаку игрока.
     *
     * @param enemy враг, участвующий в бою
     * @param player игрок, выполняющий атаку
     * @return true, если враг быстрее, иначе false
     */
    private boolean isEnemyFasterInLightAttack(Enemy enemy, Player player) {
        double baseEnemyFirstChance = 0.5;

        if (enemy instanceof Skeleton) {
            baseEnemyFirstChance = 0.7;
        } else if (enemy instanceof Hound) {
            baseEnemyFirstChance = 0.6;
        } else if (enemy instanceof Knight) {
            baseEnemyFirstChance = 0.3;
        } else if (enemy instanceof Boss) {
            baseEnemyFirstChance = 0.65;
        } else if (enemy instanceof Mimic) {
            baseEnemyFirstChance = 0.2;
        }

        double equipmentWeight = player.getTotalEquipmentWeight();
        double weightModifier = (equipmentWeight - 50.0) / 250.0;
        double finalEnemyFirstChance = baseEnemyFirstChance + weightModifier;

        finalEnemyFirstChance = Math.max(0.05, Math.min(0.95, finalEnemyFirstChance));
        finalEnemyFirstChance = Math.round(finalEnemyFirstChance * 100.0) / 100.0;

        return Math.random() < finalEnemyFirstChance;
    }
    
    /**
     * Обрабатывает тяжелую атаку игрока.
     * <p>
     * Проверяет наличие достаточной выносливости, обновляет параметры оружия и
     * выносливости игрока, определяет реакцию врага и наносит урон при необходимости.
     * Логирует результат в {@link BattleForm}.
     */
    private void handlePlayerHeavyAttack() {
        EntityActionType enemyActionForPlayersAttack = chooseEnemyActionForPlayersAttack();
        
        if (player.getStamina() < 10){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 10);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        player.setStamina(player.getStamina() - 10);
        
        player.getSelectedWeapon().setDurability(player.getSelectedWeapon().getDurability() - 4);
        player.getSelectedWeapon().checkStatus();
        
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        
        if (enemyActionForPlayersAttack == EntityActionType.DODGE){
            double enemyDodgePossibility = enemy.getDodgeP() + 0.1 + calculateEquipmentDodgePenalty(player.getTotalEquipmentWeight());
            if(Math.random() < enemyDodgePossibility){
                String logPart = enemy.getName() + " dodged your attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to dodge!";
                    String logPart2 = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.BLOCK){
            double enemyBlockPossibility = enemy.getBlockP() - 0.05;
            if(Math.random() < enemyBlockPossibility){
                String logPart = enemy.getName() + " blocked your heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                if (Math.random() < player.getCritP()){
                    String logPart1 = "You landed critical hit on the " + enemy.getName() + " when he tried to block!";
                    String logPart2 = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage() * 4);
                    battleForm.appendToLogArea(logPart1);
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = enemy.takeHeavyDamage(player.getSelectedWeapon(), player.getBaseDamage());
                    battleForm.appendToLogArea(logPart);
                }
            }
        } else if (enemyActionForPlayersAttack == EntityActionType.HEAVY_ATTACK){
            String logPart = "Both decided to use heavy attack and nobody took damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersAttack == EntityActionType.LIGHT_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You used heavy attack but " + enemy.getName() + " light attack was faster!" + "\n" + "You took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    
    /**
     * Обрабатывает блок игрока.
     * <p>
     * Проверяет наличие выносливости, выбирает действие врага из шаблона,
     * определяет успех блока и возможность контратаки, обновляет состояние
     * и логирует результат.
     */
    void handlePlayerBlockAction() {
        
        if (player.getStamina() < 10){
            NotEnoughStaminaDialog notEnoughStaminaDialog = new NotEnoughStaminaDialog(null, true, 10);
            notEnoughStaminaDialog.setVisible(true);
            return;
        }
        
        EntityActionType enemyActionForPlayersBlock = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        player.setStamina(player.getStamina() - 10);
        if(player.getStamina() <= 0){
            player.setStamina(0);
        }
        if (enemyActionForPlayersBlock == EntityActionType.HEAVY_ATTACK){
            double playerBlockPossibility = player.getBlockP() - 0.05;
            if(Math.random() < playerBlockPossibility){

                if(player.hasCounterAttack() && player.getStamina() >= 5 && player.getTotalEquipmentWeight() < player.getBearableWeight() * 1.5){
                    String logPart1 = "You blocked " + enemy.getName() + " heavy attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.3));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    String logPart = "You blocked " + enemy.getName() + " heavy attack!";
                    battleForm.appendToLogArea(logPart);
                }
                
            } else {
                player.takeDamage((int) (enemy.getDamage() * 1.2));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersBlock == EntityActionType.LIGHT_ATTACK){
            double playerBlockPossibility = player.getBlockP() + 0.1;
            if(Math.random() < playerBlockPossibility){
                
                if(player.hasCounterAttack() && player.getStamina() >= 5 && player.getTotalEquipmentWeight() < player.getBearableWeight() * 1.5){
                    String logPart1 = "You blocked " + enemy.getName() + " light attack and counter-attacked him!";
                    player.setStamina(player.getStamina() - 5);
                    
                    if(player.getStamina() <= 0){
                        player.setStamina(0);
                    }
                    
                    battleForm.appendToLogArea(logPart1);
                    String logPart2 = enemy.takeLightDamage(player.getSelectedWeapon(), (int) (player.getBaseDamage() * 0.2));
                    battleForm.appendToLogArea(logPart2);
                } else {
                    player.takeDamage((int) (enemy.getDamage() * 0.1));
                    String logPart = "You blocked " + enemy.getName() + " light attack!";
                    battleForm.appendToLogArea(logPart);
                }
                
            } else {
                player.takeDamage((int) (enemy.getDamage() * 0.8));
                String logPart = "You tried to block but took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    
    /**
     * Обрабатывает уклонение игрока.
     * <p>
     * Выбирает действие врага, увеличивает выносливость игрока, проверяет успех
     * уклонения и наносит урон при неудаче. Логирует результат.
     */
    void handlePlayerDodgeAction() {
        EntityActionType enemyActionForPlayersDodge = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        player.setStamina(player.getStamina() + 5);
        if (player.getStamina() >= player.getMaxStamina()){
            player.setStamina(player.getMaxStamina());
        }
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            double playerDodgePossibility = player.getDodgeP() + 0.1;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged " + enemy.getName() + " heavy attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                player.takeDamage((int) (enemy.getDamage() * 1.2 * 1.5));
                String logPart = "You couldn't dodge and took " + (int)(enemy.getDamage() * 1.2 * 1.5 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            double playerDodgePossibility = player.getDodgeP() - 0.05;
            if(Math.random() < playerDodgePossibility){
                String logPart = "You dodged " + enemy.getName() + " light attack!";
                battleForm.appendToLogArea(logPart);
            } else {
                player.takeDamage((int) (enemy.getDamage() * 0.8 * 1.5));
                String logPart = "You couldn't dodge and took " + (int)(enemy.getDamage() * 0.8 * 1.5 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
                battleForm.appendToLogArea(logPart);
            }
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }
    
    /**
     * Обновляет доступность кнопок интерфейса на основе выносливости игрока.
     */
    private void checkPlayerStamina(){
        battleForm.updateButtonAvaibility(player);
    }
    
    /**
     * Проверяет условия победы или поражения в бою.
     * <p>
     * Если здоровье врага достигает нуля, игрок получает награды, бой завершается,
     * и отображается диалог победы. Если здоровье игрока достигает нуля, отображается
     * форма поражения.
     */
    private void checkWinLoseConditions(){
        if(enemy.getHealth() <= 0){
            FightWinDialog winDialog = new FightWinDialog(battleForm, true);
            winDialog.setVisible(true);
            
            processEnemyItemDrop();

            battleForm.dispose();
            
            player.setStamina(player.getMaxStamina());
            player.addCurrentSoulsAmount(enemy.getSouls());
            
            if(enemy instanceof Boss){
                player.setRepairComponents(player.getRepairComponents() + 12);
            } else {
                player.setRepairComponents(player.getRepairComponents() + 6);
            }

            this.poisonDuration = 0;
            this.poison = null;
            
            if(enemy instanceof Boss){
                GUIandLogicIntermediary.autoSave(player);
            }
            
            if(enemy instanceof TheUnhallowedArchon){
                YouBeatTheGameDialog youBeatTheGameDialog = new YouBeatTheGameDialog(null,true);
                youBeatTheGameDialog.setVisible(true);
            }
            
            GUIandLogicIntermediary.showNavigationForm();
        } else if (player.getHp() <= 0){
            battleForm.setVisible(false);
            FightLoseForm loseForm = new FightLoseForm();
            loseForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            loseForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    battleForm.dispose();
                }
            });
            loseForm.setVisible(true);
        }
    }
    
    /**
     * Обрабатывает выпадение предметов после победы над врагом.
     * <p>
     * Для боссов вызывает {@link #processBossItemDrop(int)}, для обычных врагов
     * с вероятностью 70% выпадает зелье ({@link #processDefaultEnemyItemDrop()}).
     */
    private void processEnemyItemDrop() {
        if (enemy instanceof Boss){
            
            ArrayList<Equipment> foundItems = processBossItemDrop(player.getCurrentRoom().getFloor());
            BossItemDropDialog bossItemDropDialog = new BossItemDropDialog(null, true, foundItems);
            bossItemDropDialog.setVisible(true);
            
        } else if (Math.random() <= 0.7){
            
            Potion foundItem = processDefaultEnemyItemDrop();
            DefaultEnemyDropDialog defaultEnemyDropDialog = new DefaultEnemyDropDialog(null, true, foundItem);
            defaultEnemyDropDialog.setVisible(true);
            
        }
    }
    
    /**
     * Обрабатывает выпадение зелья от обычного врага.
     * <p>
     * Случайно выбирает тип зелья (бомба, зелье выносливости или яд) и добавляет
     * его в инвентарь игрока.
     *
     * @return найденное зелье
     */
    private static Potion processDefaultEnemyItemDrop() {
        Potion foundItem = null;
        boolean foundBomb = false;
        boolean foundStamina = false;
        boolean foundPoison = false;
        if (Math.random() < 0.20) {
            foundBomb = true;
        }
        if (Math.random() < 0.35) {
            foundStamina = true;
        }
        if (Math.random() < 0.5) {
            foundPoison = true;
        }

        ArrayList<String> foundTypes = new ArrayList<>();
        if (foundBomb) foundTypes.add("Bomb");
        if (foundStamina) foundTypes.add("StaminaPotion");
        if (foundPoison) foundTypes.add("PoisonPotion");

        if (foundTypes.isEmpty()) {
            foundTypes.add("PoisonPotion");
        }

        while (foundTypes.size() > 1) {
            foundTypes.remove((int)(Math.random() * foundTypes.size()));
        }

        String type = foundTypes.get(0);
        foundItem = createPotionByType(type);
        
        addItemToInventory(foundItem);
        
        return foundItem;
    }
    
    /**
     * Добавляет зелье в инвентарь игрока.
     *
     * @param item зелье для добавления
     */
    private static void addItemToInventory(Potion item) {
        Player player = Player.getInstance();
        player.addItemToInventory(item);
    }
    
    /**
     * Создает зелье указанного типа.
     *
     * @param type тип зелья ("Bomb", "StaminaPotion" или "PoisonPotion")
     * @return созданное зелье
     * @throws IllegalArgumentException если указан неизвестный тип зелья
     */
    private static Potion createPotionByType(String type) {
        switch (type) {
            case "Bomb":
                return new Bomb();
            case "StaminaPotion":
                return new StaminaPotion();
            case "PoisonPotion":
                return new Poison();
            default:
                throw new IllegalArgumentException("Unknown potion type!");
        }
    }

    
    /**
     * Обрабатывает пропуск хода игроком.
     * <p>
     * Увеличивает выносливость игрока, выбирает действие врага и наносит урон
     * игроку в зависимости от атаки врага. Логирует результат.
     */
    void handlePlayerSkipAction() {
        EntityActionType enemyActionForPlayersDodge = enemy.getPattern()[currentEnemyActionPattern];
        currentEnemyActionPattern++;
        if (currentEnemyActionPattern >= enemy.getPattern().length) {
            currentEnemyActionPattern = 0;
        }
        
        player.setStamina(player.getStamina() + 10);
        
        if (enemyActionForPlayersDodge == EntityActionType.HEAVY_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 1.2));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 1.2 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        } else if (enemyActionForPlayersDodge == EntityActionType.LIGHT_ATTACK){
            player.takeDamage((int) (enemy.getDamage() * 0.8));
            String logPart = "You skipped turn and took " + (int)(enemy.getDamage() * 0.8 * (1 - player.getSelectedArmor().getDamageReduction())) + " damage!";
            battleForm.appendToLogArea(logPart);
        }
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }
    
    /**
     * Обрабатывает использование игроком бомбы.
     * <p>
     * Наносит урон врагу, заставляет врага пропустить ход и логирует результат.
     *
     * @param bomb бомба, используемая игроком
     */
    void handlePlayerUsingBomb(Bomb bomb) {
        enemy.takeBombDamage(bomb);
        
        String logPart = "You used bomb; " + enemy.getName() + " took " + bomb.getDamage() + " damage!";
        battleForm.appendToLogArea(logPart);
        String logPart1 = enemy.getName() + " skips turn because of bomb!";
        battleForm.appendToLogArea(logPart1);
        
        checkPoison();
        battleForm.updateLabels(player, enemy);
        checkPlayerStamina();
        checkWinLoseConditions();
    }

    /**
     * Обрабатывает использование игроком яда.
     * <p>
     * Применяет яд к врагу, если он не иммунен и яд еще не активен. Враг пропускает
     * ход, получает урон, и яд добавляется в бой. Логирует результат.
     *
     * @param poison яд, используемый игроком
     */
    void handlePlayerUsingPoison(Poison poison) {
        if (this.poison == null){
            if (enemy instanceof Skeleton || enemy instanceof SirenOfOblivion){
                String logPart = "You used poison but " + enemy.getName() + " is immune to it!";
                battleForm.appendToLogArea(logPart);
            } else {
                this.poisonDuration = poison.getDuration() - 1;
                this.poison = poison;
                enemy.takePoisonDamage(poison);
                String logPart = "You used poison! " + enemy.getName() + " took " + poison.getDamage() + " damage from poison!";
                battleForm.appendToLogArea(logPart);
                String logPart1 = enemy.getName() + " skips turn because of poison!";
                battleForm.appendToLogArea(logPart1);
                checkWinLoseConditions();
            }
            Player.getInstance().getInventory().remove((Poison) poison);
        } else {
            PotionAlreadyUsedDialog potionAlreadyUsedDialog = new PotionAlreadyUsedDialog(null,true);
            potionAlreadyUsedDialog.setVisible(true);
        }
    }
    
    /**
     * Проверяет и применяет эффект яда на врага.
     * <p>
     * Если яд активен, враг получает урон, уменьшается длительность яда, и результат
     * логируется. Если длительность истекла, яд сбрасывается.
     */
    void checkPoison(){
        if (this.poisonDuration != 0){
            this.poisonDuration--;
            enemy.takePoisonDamage(this.poison);
            String logPart = enemy.getName() + " took " + poison.getDamage() + " damage from poison!";
            battleForm.appendToLogArea(logPart);
        } else {
            this.poisonDuration = 0;
            this.poison = null;
        }
    }

    /**
     * Возвращает врага, участвующего в бою.
     *
     * @return текущий враг
     */
    Enemy getEnemy() {
        return this.enemy;
    }
    
    /**
     * Проверяет, отравлен ли враг.
     *
     * @return true, если яд активен, иначе false
     */
    boolean isPoisoned() {
        boolean isPoisoned = false;
        if (this.poison == null){
            isPoisoned = false;
        } else if (this.poison != null){
            isPoisoned = true;
        }
        return isPoisoned;
    }

    /**
     * Обрабатывает выпадение предметов после победы над боссом.
     * <p>
     * Случайно выбирает до трех предметов (оружие, броню или зелья) в зависимости
     * от этажа и добавляет их в инвентарь игрока.
     *
     * @param floor текущий этаж игры
     * @return список выпавших предметов
     */
    public static ArrayList<Equipment> processBossItemDrop(int floor) {
        ArrayList<Equipment> foundItems = new ArrayList<>();
        Random random = new Random();

        ArrayList<String> possibleTypes = new ArrayList<>(Arrays.asList(
                "Bomb", "PoisonPotion", "StaminaPotion",
                "LightArmor", "TrooperArmor", "HeavyArmor",
                "Sword", "Bow", "Spear", "Hammer", "Axe"
        ));

        boolean giveArmor = random.nextBoolean();

        String chosenType = null;
        if (giveArmor) {
            String[] armorTypes = {"LightArmor", "TrooperArmor", "HeavyArmor"};
            chosenType = armorTypes[random.nextInt(armorTypes.length)];
        } else {
            String[] weaponTypes = {"Sword", "Bow", "Spear", "Hammer", "Axe"};
            chosenType = weaponTypes[random.nextInt(weaponTypes.length)];
        }
        
        foundItems.add(getEquipmentSampleByTypeAndFloor(chosenType, floor, random));
        
        possibleTypes.remove(chosenType);

        int totalItems = 3;
        while (foundItems.size() < totalItems && !possibleTypes.isEmpty()) {
            int idx = random.nextInt(possibleTypes.size());
            String nextType = possibleTypes.remove(idx);
            foundItems.add(getEquipmentSampleByTypeAndFloor(nextType, floor, random));
        }

        addItemsToInventory(foundItems);
        
        return foundItems;
    }
    
    /**
     * Создает экземпляр экипировки по типу и этажу.
     *
     * @param type тип экипировки
     * @param floor текущий этаж
     * @param random генератор случайных чисел
     * @return созданный объект экипировки
     * @throws IllegalArgumentException если тип экипировки неизвестен
     */
    private static Equipment getEquipmentSampleByTypeAndFloor(String type, int floor, Random random) {
        int idxInZone = getRandomIndexForFloor(floor, random);
        
        switch (type) {
            case "LightArmor":
                return new LightArmor(ArmorStorage.lightArmor.get(idxInZone));
            case "TrooperArmor":
                return new TrooperArmor(ArmorStorage.trooperArmor.get(idxInZone));
            case "HeavyArmor":
                return new HeavyArmor(ArmorStorage.heavyArmor.get(idxInZone));
            case "Sword":
                return new Sword(WeaponsStorage.swords.get(idxInZone));
            case "Bow":
                return new Bow(WeaponsStorage.bows.get(idxInZone));
            case "Spear":
                return new Spear(WeaponsStorage.spears.get(idxInZone));
            case "Hammer":
                return new Hammer(WeaponsStorage.hammers.get(idxInZone));
            case "Axe":
                return new Axe(WeaponsStorage.axes.get(idxInZone));
            case "Bomb":
                return new Bomb();
            case "PoisonPotion":
                return new Poison();
            case "StaminaPotion":
                return new StaminaPotion();
            default:
                throw new IllegalArgumentException("Unknown equipment type: " + type);
        }
    }
    
    /**
     * Возвращает случайный индекс для выбора экипировки в зависимости от этажа.
     *
     * @param floor текущий этаж
     * @param random генератор случайных чисел
     * @return случайный индекс для выбора экипировки
     */
    private static int getRandomIndexForFloor(int floor, Random random) {
        if (floor <= 3) {
            return random.nextInt(5);
        } else if (floor <= 5) {
            return 5 + random.nextInt(5);
        } else {
            return 10 + random.nextInt(5);
        }
    }
    
    /**
     * Добавляет список экипировки в инвентарь игрока.
     *
     * @param items список экипировки для добавления
     */
    private static void addItemsToInventory(List<? extends Equipment> items) {
        Player player = Player.getInstance();
        for (Equipment e : items) {
            player.addItemToInventory(e);
        }
    }

}