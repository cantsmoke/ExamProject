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
import com.mycompany.examproject.GUI.EnemyEncounteredDialog;
import com.mycompany.examproject.GUI.FightLoseForm;
import com.mycompany.examproject.GUI.ForewordDialog;
import com.mycompany.examproject.GUI.InventoryForm;
import com.mycompany.examproject.GUI.InventoryFormForBattle;
import com.mycompany.examproject.GUI.MainMenuForm;
import com.mycompany.examproject.GUI.NothingFoundDialog;
import com.mycompany.examproject.GUI.StashFoundDialog;
import com.mycompany.examproject.GUI.StateAndNavigationForm;
import com.mycompany.examproject.GUI.TrapDialog;
import com.mycompany.examproject.GUI.UpgradeMenu;
import com.mycompany.examproject.GUI.YouAreRestingDialog;
import com.mycompany.examproject.GUI.YouFoundChestDialog;
import com.mycompany.examproject.GUI.YouLostDialog;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Arseniy
 */
public class GUIandLogicIntermediary {
    
    private static StateAndNavigationForm stateAndNavigationForm;
    private static Player player;
    private static CastleMapGenerator castleMapGenerator;
    
    private static EnemySection1Factory enemySection1Factory;
    private static EnemySection2Factory enemySection2Factory;
    private static EnemySection3Factory enemySection3Factory;
    
    private static Fight fight;

    private GUIandLogicIntermediary(){}
    
    public static void saveCurrentGame() {
        File fixedDir = new File("C:\\Users\\Arseniy\\Documents\\GitHub\\ExamProject\\ExamProject\\src\\main\\resources");
        FileSystemView fsv = new SingleRootFileSystemView(fixedDir);
        JFileChooser fileChooser = new JFileChooser();
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

    public static void loadGame() {
        File fixedDir = new File("C:\\Users\\Arseniy\\Documents\\GitHub\\ExamProject\\ExamProject\\src\\main\\resources");
        FileSystemView fsv = new SingleRootFileSystemView(fixedDir);
        JFileChooser fileChooser = new JFileChooser(fixedDir, fsv);

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

                // 🔥 Вставить вот здесь:
                CastleMapGenerator.setFloors(castleMapGenerator.getFloorsInstance());

                stateAndNavigationForm = new StateAndNavigationForm();
                stateAndNavigationForm.updateLabels();
                stateAndNavigationForm.setVisible(true);
            }
        }
    }

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

        SwingUtilities.updateComponentTreeUI(chooser);
    }
    
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
    
    public static void handleMimicEncounter() {
        EnemyEncounteredDialog enemyEncounteredDialog = new EnemyEncounteredDialog(null, true);
        enemyEncounteredDialog.setVisible(true);
        Enemy enemy = new Mimic("Mimic", 300, 50, Player.getInstance().getDamage());
        fight = new Fight(player, enemy);
        stateAndNavigationForm.setVisible(false);
    }
    
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
    
    public static void handlePlayerAttackAction() {
        fight.handlePlayerAttackAction();
    }
    
    public static void handlePlayerBlockAction() {
        fight.handlePlayerBlockAction();
    }

    public static void handlePlayerDodgeAction() {
        fight.handlePlayerDodgeAction();
    }
    
    public static void handlePlayerSkipAction() {
        fight.handlePlayerSkipAction();
    }
    
    public static void showNavigationForm() {
        stateAndNavigationForm.updateLabels();
        stateAndNavigationForm.setVisible(true);
    }
    
    public static void showBattleForm() {
        fight.showBattleForm();
    }
    
    public static void openInventory() {
        stateAndNavigationForm.setVisible(false);
        
        InventoryForm inventoryForm = new InventoryForm();
        inventoryForm.setVisible(true);
        inventoryForm.UpdateLabels();
    }

    public static void handlePlayerOpenDialogFromBattle() {
        fight.hideBattleForm();
        
        InventoryFormForBattle inventoryFormForBattle = new InventoryFormForBattle();
        inventoryFormForBattle.setVisible(true);
        inventoryFormForBattle.UpdateLabels();
    }

    public static void openUpgradeMenu() {
        UpgradeMenu upgradeMenu = new UpgradeMenu();
        upgradeMenu.setVisible(true);
        
        stateAndNavigationForm.setVisible(false);
    }
    
    public static void handlePlayerUsingBomb(Bomb bomb) {
        fight.handlePlayerUsingBomb(bomb);
    }
    
    public static void handlePlayerUsingPoison(Poison poison) {
        fight.handlePlayerUsingPoison(poison);
    }
    
    public static int checkEnemyHealth() {
        int enemyHealth = fight.getEnemy().getHealth();
        return enemyHealth;
    }
    
    public static boolean isPoisoned() {
        return fight.isPoisoned();
    }
    
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
            stateAndNavigationForm.setVisible(false);
            
            NothingFoundDialog nothingFoundDialog = new NothingFoundDialog(null, true);
            nothingFoundDialog.setVisible(true);
            
            stateAndNavigationForm.updateLabels();
            stateAndNavigationForm.setVisible(true);
        }
    }
    
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

    private static void addItemsToInventory(List<? extends Equipment> items) {
        Player player = Player.getInstance();
        for (Equipment e : items) {
            player.addItemToInventory(e);
        }
    }
    
    // Вспомогательный метод
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

        int totalItems = 3 + random.nextInt(3); // 3, 4 или 5

        while (foundItems.size() < totalItems && !possibleTypes.isEmpty()) {
            int idx = random.nextInt(possibleTypes.size());
            String nextType = possibleTypes.remove(idx);
            foundItems.add(getEquipmentSampleByTypeAndFloor(nextType, floor, random));
        }

        addItemsToInventory(foundItems);
        
        return foundItems;
    }

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
            // Зелья и расходники создаются как новые экземпляры 
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

    // Возвращает индекс в списке предметов согласно текущей зоне
    private static int getRandomIndexForFloor(int floor, Random random) {
        if (floor <= 3) {
            // зона 1, индексы 0..4
            return random.nextInt(5);
        } else if (floor <= 5) {
            // зона 2, индексы 5..9
            return 5 + random.nextInt(5);
        } else {
            // зона 3, индексы 10..14
            return 10 + random.nextInt(5);
        }
    }

}