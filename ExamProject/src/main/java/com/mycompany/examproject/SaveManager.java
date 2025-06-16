/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;
import com.mycompany.examproject.GUI.GameSavedDialog;
import java.io.*;

/**
 * Класс для управления сохранением и загрузкой состояния игры.
 * <p>
 * Предоставляет методы для сериализации и десериализации объекта {@link GameState}
 * в файл, обеспечивая сохранение и восстановление прогресса в RPG-игре.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class SaveManager {

    /**
     * Сохраняет состояние игры в указанный файл.
     * <p>
     * Сериализует объект {@link GameState} в файл с помощью {@link ObjectOutputStream}
     * и отображает диалог подтверждения сохранения.
     *
     * @param gameState состояние игры для сохранения
     * @param filename путь к файлу сохранения
     */
    public static void saveGame(GameState gameState, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gameState);
            GameSavedDialog gameSavedDialog = new GameSavedDialog(null, true);
            gameSavedDialog.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загружает состояние игры из указанного файла.
     * <p>
     * Десериализует объект {@link GameState} из файла с помощью {@link ObjectInputStream}.
     * Возвращает null, если загрузка не удалась.
     *
     * @param filename путь к файлу сохранения
     * @return загруженное состояние игры или null в случае ошибки
     */
    public static GameState loadGame(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}