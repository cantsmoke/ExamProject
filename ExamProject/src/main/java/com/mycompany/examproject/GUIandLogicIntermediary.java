/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.Enemies.BossFactory;
import com.mycompany.examproject.Enemies.EnemySection1Factory;
import com.mycompany.examproject.Enemies.EnemySection2Factory;
import com.mycompany.examproject.Enemies.EnemySection3Factory;
import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.BossType;
import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.Enemies.enemyStructure.Mimic;
import com.mycompany.examproject.GUI.*;
import com.mycompany.examproject.Items.Armors.ArmorStorage;
import com.mycompany.examproject.Items.Armors.HeavyArmor;
import com.mycompany.examproject.Items.Armors.LightArmor;
import com.mycompany.examproject.Items.Armors.TrooperArmor;
import com.mycompany.examproject.Items.Equipment;
import com.mycompany.examproject.Items.Potion;
import com.mycompany.examproject.Items.Potions.Bomb;
import com.mycompany.examproject.Items.Potions.EstusBottle;
import com.mycompany.examproject.Items.Potions.Poison;
import com.mycompany.examproject.Items.Potions.StaminaPotion;
import com.mycompany.examproject.Items.Weapons.Axe;
import com.mycompany.examproject.Items.Weapons.Bow;
import com.mycompany.examproject.Items.Weapons.Hammer;
import com.mycompany.examproject.Items.Weapons.Spear;
import com.mycompany.examproject.Items.Weapons.Sword;
import com.mycompany.examproject.Items.Weapons.WeaponsStorage;
import com.mycompany.examproject.Map.CastleMapGenerator;
import com.mycompany.examproject.Map.Floor;
import com.mycompany.examproject.Map.Room;
import com.mycompany.examproject.Map.RoomType;
import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Класс-посредник между графическим интерфейсом и логикой игры.
 * <p>
 * Этот класс управляет взаимодействием между элементами интерфейса и игровой логикой,
 * включая сохранение и загрузку игры, навигацию по карте, встречи с врагами, использование
 * предметов и открытие меню. Обеспечивает синхронизацию состояния игрока, карты и боевой системы.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class GUIandLogicIntermediary {
    
    /**
     * Форма навигации и состояния игрока.
     */
    private static StateAndNavigationForm stateAndNavigationForm;
    
    /**
     * Игрок, участвующий в игре.
     */
    private static Player player;
    
    /**
     * Генератор карты замка.
     */
    private static CastleMapGenerator castleMapGenerator;
    
    /**
     * Фабрика врагов для первого сектора (этажи 1-3).
     */
    private static EnemySection1Factory enemySection1Factory;
    
    /**
     * Фабрика врагов для второго сектора (этажи 4-5).
     */
    private static EnemySection2Factory enemySection2Factory;
    
    /**
     * Фабрика врагов для третьего сектора (этажи 6-9).
     */
    private static EnemySection3Factory enemySection3Factory;
    
    /**
     * Текущий бой между игроком и врагом.
     */
    private static Fight fight;

    /**
     * Приватный конструктор для предотвращения создания экземпляров класса.
     */
    private GUIandLogicIntermediary(){}
    
    
    /**
     * Сохраняет текущее состояние игры в файл.
     * <p>
     * Открывает диалог выбора файла, применяет темную тему, проверяет перезапись
     * существующего файла и сохраняет состояние игры через {@link SaveManager}.
     */
    public static void saveCurrentGame() {
        File fixedDir = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser(fixedDir);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы сохранения (*.dat)", "dat");
        fileChooser.setFileFilter(filter);
        applyDarkTheme(fileChooser);

        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".dat")) {
                filePath += ".dat";
            }

            File fileToSave = new File(filePath);
            if (fileToSave.exists()) {
                int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Файл уже существует. Перезаписать?",
                    "Подтверждение перезаписи",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) return;
            }

            GameState gameState = new GameState(player, castleMapGenerator);
            SaveManager.saveGame(gameState, filePath);
        }
    }

    /**
     * Загружает игру из файла.
     * <p>
     * Открывает диалог выбора файла, загружает состояние игры через {@link SaveManager},
     * инициализирует игрока, карту и фабрики врагов, а также отображает форму навигации.
     * Если загрузка не удалась, открывает главное меню.
     */
    public static void loadGame() {
        File fixedDir = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser(fixedDir);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы сохранения (*.dat)", "dat");
        fileChooser.setFileFilter(filter);
        applyDarkTheme(fileChooser);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            GameState gameState = SaveManager.loadGame(selectedFile.getAbsolutePath());

            if (gameState != null) {
                player = gameState.player;
                Player.setInstance(player);
                castleMapGenerator = gameState.castleMap;
                
                enemySection1Factory = new EnemySection1Factory();
                enemySection2Factory = new EnemySection2Factory();
                enemySection3Factory = new EnemySection3Factory();

                CastleMapGenerator.setFloors(castleMapGenerator.getFloorsInstance());

                stateAndNavigationForm = new StateAndNavigationForm();
                stateAndNavigationForm.updateLabels();
                stateAndNavigationForm.setVisible(true);
            }
        } else {
            MainMenuForm mainMenuForm = new MainMenuForm();
            mainMenuForm.setVisible(true);
        }
    }
    
    /**
     * Автоматически сохраняет игру в файл с меткой времени.
     * <p>
     * Создает файл сохранения с именем, включающим уровень игрока, этаж и временную метку,
     * и сохраняет состояние игры через {@link SaveManager}.
     *
     * @param player текущий игрок
     */
    public static void autoSave(Player player) {
        File saveDir = new File(System.getProperty("user.dir"));
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        int level = player.getLevel();
        int floor = player.getCurrentRoom().getFloor();

        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String fileName = "Save - LVL " + level + ", FLOOR " + floor + " - " + timeStamp + ".dat";

        File saveFile = new File(saveDir, fileName);

        GameState gameState = new GameState(player, castleMapGenerator);
        SaveManager.saveGame(gameState, saveFile.getAbsolutePath());

        System.out.println("Game automatically saved to " + saveFile.getAbsolutePath());
    }
    
    /**
     * Применяет темную тему к диалогу выбора файла.
     * <p>
     * Настраивает цвета фона, текста и элементов интерфейса для создания темной темы.
     *
     * @param chooser диалог выбора файла
     */
    private static void applyDarkTheme(JFileChooser chooser) {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        Color bg = new Color(40, 40, 40);
        Color fg = new Color(220, 220, 220);

        defaults.put("Panel.background", bg);
        defaults.put("FileChooser.background", bg);
        defaults.put("FileChooser.foreground", fg);
        defaults.put("FileChooser.textBackground", bg);
        defaults.put("FileChooser.textForeground", fg);
        defaults.put("Button.background", bg);
        defaults.put("Button.foreground", fg);
        defaults.put("Label.foreground", fg);
        defaults.put("List.background", new Color(50, 50, 50));
        defaults.put("List.foreground", fg);
        defaults.put("TextField.background", new Color(60, 60, 60));
        defaults.put("TextField.foreground", fg);
        defaults.put("ComboBox.background", new Color(60, 60, 60));
        defaults.put("ComboBox.foreground", fg);
        
        UIManager.put("OptionPane.messageForeground", new Color(255, 255, 255));
        UIManager.put("Button.foreground", new Color(255, 255, 255));
        UIManager.put("Label.foreground", new Color(255, 255, 255));

        SwingUtilities.updateComponentTreeUI(chooser);
    }
    
    /**
     * Обрабатывает нажатие кнопки "Новая игра".
     * <p>
     * Показывает вступительный диалог, генерирует новую карту, создает игрока
     * и отображает форму навигации.
     */
    public static void handleNewGameButtonPressed(){ 
        ForewordDialog forewordDialog = new ForewordDialog(null, true);
        forewordDialog.setVisible(true);
        
        castleMapGenerator = new CastleMapGenerator();
        castleMapGenerator.generateMap();
        
        enemySection1Factory = new EnemySection1Factory();
        enemySection2Factory = new EnemySection2Factory();
        enemySection3Factory = new EnemySection3Factory();
        
        Player.resetInstance();
        player = Player.getInstance(castleMapGenerator.getStartRoom());
        
        stateAndNavigationForm = new StateAndNavigationForm();
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        stateAndNavigationForm.setVisible(true);
    }
    
    /**
     * Обрабатывает перемещение игрока на юг.
     * <p>
     * Обновляет текущую комнату игрока, проверяет встречу с врагом и комнату отдыха,
     * обновляет интерфейс навигации.
     */
    public static void handlePLayerGoingSouth() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomSouth(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        handlePlayerFoundRestRoom();
    }
    
    /**
     * Обрабатывает перемещение игрока на восток.
     * <p>
     * Обновляет текущую комнату игрока, проверяет встречу с врагом и комнату отдыха,
     * обновляет интерфейс навигации.
     */
    public static void handlePLayerGoingEast() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToEast(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        handlePlayerFoundRestRoom();
    }
    
    /**
     * Обрабатывает перемещение игрока на запад.
     * <p>
     * Обновляет текущую комнату игрока, проверяет встречу с врагом и комнату отдыха,
     * обновляет интерфейс навигации.
     */
    public static void handlePLayerGoingWest() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomToWest(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        handlePlayerFoundRestRoom();
    }
    
    /**
     * Обрабатывает перемещение игрока на север.
     * <p>
     * Обновляет текущую комнату игрока, проверяет встречу с врагом и комнату отдыха,
     * обновляет интерфейс навигации.
     */
    public static void handlePLayerGoingNorth() {
        int x = player.getCurrentRoom().getX();
        int y = player.getCurrentRoom().getY();
        int floorNumber = player.getCurrentRoom().getFloor();
        
        player.setCurrentRoom(castleMapGenerator.getRoomNorth(x, y, floorNumber));
        
        stateAndNavigationForm.updateLabels();
        handleEnemyEncounter(player.getCurrentRoom());
        
        player.getCurrentRoom().setVisitedByPlayer(true);
        
        stateAndNavigationForm.updateLabels();
        handlePlayerFoundRestRoom();
    }
    
    /**
     * Обрабатывает использование лестницы игроком.
     * <p>
     * Перемещает игрока на следующий или предыдущий этаж в зависимости от типа лестницы,
     * обновляет интерфейс навигации.
     */
    public static void handlePLayerUsingStairs() {
        Room current = player.getCurrentRoom();

        if (current.getType() == RoomType.STAIRCASE_UP) {
            int nextFloorNumber = current.getFloor() + 1;
            Floor nextFloor = CastleMapGenerator.getFloors().get(nextFloorNumber - 1);

            Room targetRoom = nextFloor.getStaircaseDownRoom();
            if (targetRoom != null) {
                player.setCurrentRoom(targetRoom);
                targetRoom.setVisitedByPlayer(true);
            }

        } else if (current.getType() == RoomType.STAIRCASE_DOWN) {
            int previousFloorNumber = current.getFloor() - 1;
            Floor prevFloor = CastleMapGenerator.getFloors().get(previousFloorNumber - 1);

            Room targetRoom = prevFloor.getStaircaseUpRoom();
            if (targetRoom != null) {
                player.setCurrentRoom(targetRoom);
                targetRoom.setVisitedByPlayer(true);
            }
        }
        stateAndNavigationForm.updateLabels();
    }
    
    /**
     * Обрабатывает встречу игрока с врагом в комнате.
     * <p>
     * С вероятностью 25% создает бой с обычным врагом или боссом, если комната
     * предназначена для босса. Прячет форму навигации и инициализирует бой.
     *
     * @param currentRoom текущая комната игрока
     */
    private static void handleEnemyEncounter(Room currentRoom) {
        double encounterProbability = 0.25;
        double randomValue = Math.random();
        
        if (encounterProbability > randomValue && currentRoom.getType() != RoomType.STAIRCASE_DOWN
        && currentRoom.getType() != RoomType.STAIRCASE_UP && currentRoom.getType() != RoomType.REST 
        && currentRoom.getType() != RoomType.BOSS && currentRoom.getFloor() != 10 && currentRoom.getType() != RoomType.ENTRANCE_HALL
        && currentRoom.isVisitedByPlayer() == false){
            
            EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
            enemyEncounteredDialog.setVisible(true);
            Enemy enemy = generateBasicEnemy();
            fight = new Fight(player, enemy);
            stateAndNavigationForm.setVisible(false);
            
        } else if (currentRoom.getType() == RoomType.BOSS && currentRoom.isVisitedByPlayer() == false){
            int floor = currentRoom.getFloor();
            if (floor >= 1 && floor <= 10) {
                EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
                enemyEncounteredDialog.setVisible(true);
                BossType bossType = BossType.values()[floor - 1];
                Boss boss = BossFactory.createBoss(bossType);
                fight = new Fight(player, boss);
                stateAndNavigationForm.setVisible(false);
            }
        }
    }
    
    /**
     * Обрабатывает нахождение игроком комнаты отдыха.
     * <p>
     * Если текущая комната — комната отдыха, восстанавливает здоровье и выносливость
     * игрока, наполняет фляги эстуса и показывает диалог отдыха.
     */
    public static void handlePlayerFoundRestRoom(){
        if (player.getCurrentRoom().getType() == RoomType.REST){
            
            stateAndNavigationForm.setVisible(false);
            player.setHp(player.getMaxHp());
            player.setStamina(player.getMaxStamina());
            for (Equipment eq : player.getInventory()) {
                if (eq instanceof EstusBottle) {
                    ((EstusBottle) eq).refill();
                }
            }
            
            YouAreRestingDialog youAreRestingDialog = new YouAreRestingDialog(null, true);
            youAreRestingDialog.setVisible(true); 
            
            stateAndNavigationForm.setVisible(true);
        }
    }
    
    /**
     * Обрабатывает встречу игрока с мимиком.
     * <p>
     * Создает бой с врагом-мимиком и скрывает форму навигации.
     */
    public static void handleMimicEncounter() {
        EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
        enemyEncounteredDialog.setVisible(true);
        Enemy enemy = new Mimic("Mimic", 300, 50, Player.getInstance().getDamage());
        fight = new Fight(player, enemy);
        stateAndNavigationForm.setVisible(false);
    }
    
    /**
     * Генерирует случайного врага в зависимости от этажа.
     * <p>
     * Использует фабрики врагов для создания врага, соответствующего текущему этажу.
     *
     * @return сгенерированный враг
     */
    private static Enemy generateBasicEnemy(){
        Enemy enemy = null;
        if (player.getCurrentRoom().getFloor() <= 3) {
            enemy = enemySection1Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        } else if (player.getCurrentRoom().getFloor() <= 5) {
            enemy = enemySection2Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        } else if (player.getCurrentRoom().getFloor() <= 9) {
            enemy = enemySection3Factory.createRandomEnemy(player.getCurrentRoom().getFloor());
        }
        return enemy;
    }
    
    /**
     * Обрабатывает атаку игрока в бою.
     * <p>
     * Вызывает метод атаки из текущего боя {@link Fight#handlePlayerAttackAction()}.
     */
    public static void handlePlayerAttackAction() {
        fight.handlePlayerAttackAction();
    }
    
    /**
     * Обрабатывает блок игрока в бою.
     * <p>
     * Вызывает метод блока из текущего боя {@link Fight#handlePlayerBlockAction()}.
     */
    public static void handlePlayerBlockAction() {
        fight.handlePlayerBlockAction();
    }
    
    /**
     * Обрабатывает уклонение игрока в бою.
     * <p>
     * Вызывает метод уклонения из текущего боя {@link Fight#handlePlayerDodgeAction()}.
     */
    public static void handlePlayerDodgeAction() {
        fight.handlePlayerDodgeAction();
    }
    
    /**
     * Обрабатывает пропуск хода игроком в бою.
     * <p>
     * Вызывает метод пропуска хода из текущего боя {@link Fight#handlePlayerSkipAction()}.
     */
    public static void handlePlayerSkipAction() {
        fight.handlePlayerSkipAction();
    }
    
    /**
     * Показывает форму навигации.
     * <p>
     * Обновляет данные формы и делает ее видимой.
     */
    public static void showNavigationForm() {
        stateAndNavigationForm.updateLabels();
        stateAndNavigationForm.setVisible(true);
    }
    
    /**
     * Показывает форму боя.
     * <p>
     * Вызывает метод показа формы боя из текущего боя {@link Fight#showBattleForm()}.
     */
    public static void showBattleForm() {
        fight.showBattleForm();
    }
    
    /**
     * Открывает инвентарь игрока.
     * <p>
     * Скрывает форму навигации и показывает форму инвентаря.
     */
    public static void openInventory() {
        stateAndNavigationForm.setVisible(false);
        
        InventoryForm inventoryForm = new InventoryForm();
        inventoryForm.setVisible(true);
        inventoryForm.UpdateLabels();
    }
    
    /**
     * Открывает инвентарь во время боя.
     * <p>
     * Скрывает форму боя и показывает форму инвентаря для боя.
     */
    public static void handlePlayerOpenDialogFromBattle() {
        fight.hideBattleForm();
        
        InventoryFormForBattle inventoryFormForBattle = new InventoryFormForBattle();
        inventoryFormForBattle.setVisible(true);
        inventoryFormForBattle.UpdateLabels();
    }

    /**
     * Открывает меню улучшений.
     * <p>
     * Скрывает форму навигации и показывает меню улучшений.
     */
    public static void openUpgradeMenu() {
        UpgradeMenu upgradeMenu = new UpgradeMenu();
        upgradeMenu.setVisible(true);
        
        stateAndNavigationForm.setVisible(false);
    }
    
    /**
     * Обрабатывает использование бомбы игроком в бою.
     * <p>
     * Вызывает метод использования бомбы из текущего боя {@link Fight#handlePlayerUsingBomb(Bomb)}.
     *
     * @param bomb бомба, используемая игроком
     */
    public static void handlePlayerUsingBomb(Bomb bomb) {
        fight.handlePlayerUsingBomb(bomb);
    }
    
    /**
     * Обрабатывает использование яда игроком в бою.
     * <p>
     * Вызывает метод использования яда из текущего боя {@link Fight#handlePlayerUsingPoison(Poison)}.
     *
     * @param poison яд, используемый игроком
     */
    public static void handlePlayerUsingPoison(Poison poison) {
        fight.handlePlayerUsingPoison(poison);
    }
    
    /**
     * Проверяет текущее здоровье врага в бою.
     * <p>
     * Возвращает здоровье врага из текущего боя.
     *
     * @return текущее здоровье врага
     */
    public static int checkEnemyHealth() {
        int enemyHealth = fight.getEnemy().getHealth();
        return enemyHealth;
    }
    
    /**
     * Проверяет, отравлен ли враг в текущем бою.
     *
     * @return true, если враг отравлен, иначе false
     */
    public static boolean isPoisoned() {
        return fight.isPoisoned();
    }
    
    /**
     * Обрабатывает исследование текущей локации игроком.
     * <p>
     * С вероятностью 7% игрок попадает в ловушку, с вероятностью 7% находит тайник
     * или сундук, иначе ничего не находит. Обновляет интерфейс и состояние игрока.
     */
    public static void handlePLayerExploringLocation() {
        double rand = Math.random();
        
        Player.getInstance().getCurrentRoom().setExplored(true);
        
        if (rand < 0.07) {
            
            stateAndNavigationForm.setVisible(false);
            TrapDialog trapDialog = new TrapDialog(null, true);
            trapDialog.setVisible(true);
            Player.getInstance().setHp((int) (player.getHp() - player.getMaxHp() * 0.8));
            if (player.getHp() <= 0){   
                YouLostDialog youLostDialog = new YouLostDialog(null, true);
                youLostDialog.setVisible(true);
            } else {
                stateAndNavigationForm.updateLabels();
                stateAndNavigationForm.setVisible(true);
            }
            
        } else if (rand < 0.14) {
            
            stateAndNavigationForm.setVisible(false);
            double subRand1 = Math.random();
            if (subRand1 < 0.7) {
                ArrayList<Potion> foundItems = processFoundedItemsInStash();
                StashFoundDialog stashFoundDialog = new StashFoundDialog(null, true, foundItems);
                stashFoundDialog.setVisible(true);
                
                stateAndNavigationForm.updateLabels();
                stateAndNavigationForm.setVisible(true);
            } else {
                YouFoundChestDialog YouFoundChestDialog = new YouFoundChestDialog(null, true);
                YouFoundChestDialog.setVisible(true);
                
                stateAndNavigationForm.updateLabels();
            }
            
        } else {
            NothingFoundDialog nothingFoundDialog = new NothingFoundDialog(null, true);
            nothingFoundDialog.setVisible(true);
            
            stateAndNavigationForm.updateLabels();
        }
    }
    
    /**
     * Обрабатывает нахождение тайника с зельями.
     * <p>
     * Случайно выбирает до двух зелий (бомба, зелье выносливости или яд) и добавляет
     * их в инвентарь игрока.
     *
     * @return список найденных зелий
     */
    private static ArrayList<Potion> processFoundedItemsInStash() {
        ArrayList<Potion> foundItems = new ArrayList<>();
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

        while (foundTypes.size() > 2) {
            foundTypes.remove((int)(Math.random() * foundTypes.size()));
        }

        if (foundTypes.size() == 1) {
            String type = foundTypes.get(0);
            foundItems.add(createPotionByType(type));
            foundItems.add(createPotionByType(type));
        } else if (foundTypes.size() == 2) {
            foundItems.add(createPotionByType(foundTypes.get(0)));
            foundItems.add(createPotionByType(foundTypes.get(1)));
        }
        
        addItemsToInventory(foundItems);
        
        return foundItems;
    }

    /**
     * Добавляет предметы в инвентарь игрока.
     *
     * @param items список предметов для добавления
     */
    private static void addItemsToInventory(List<? extends Equipment> items) {
        Player player = Player.getInstance();
        for (Equipment e : items) {
            player.addItemToInventory(e);
        }
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
     * Обрабатывает нахождение сундука с предметами.
     * <p>
     * Случайно выбирает от 3 до 5 предметов (оружие, броню или зелья) в зависимости
     * от этажа и добавляет их в инвентарь игрока.
     *
     * @param floor текущий этаж
     * @return список найденных предметов
     */
    public static ArrayList<Equipment> processFoundedItemsInChest(int floor) {
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

        int totalItems = 3 + random.nextInt(3);

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

}