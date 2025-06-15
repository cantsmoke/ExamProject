/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

/**
 *
 * @author Arseniy
 */
import com.mycompany.examproject.GUI.GameSavedDialog;
import java.io.*;

public class SaveManager {

    public static void saveGame(GameState gameState, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gameState);
            
            GameSavedDialog gameSavedDialog = new GameSavedDialog(null, true);
            gameSavedDialog.setVisible(true);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameState loadGame(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}