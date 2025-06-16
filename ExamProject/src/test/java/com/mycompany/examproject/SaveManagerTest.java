/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.examproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.mockito.Mockito.*;

public class SaveManagerTest {

    private GameState gameState;
    private String filename;

    @BeforeEach
    public void setUp() {
        gameState = mock(GameState.class);
        filename = "test_save.dat";
    }

    @Test
    public void testLoadGame_FileNotFound() {
        GameState loadedState = SaveManager.loadGame("non_existent_file.dat");
        assertNull(loadedState, "При загрузке несуществующего файла должно возвращаться null");
    }

    @Test
    public void testLoadGame_InvalidData() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write("invalid data".getBytes());
        }

        GameState loadedState = SaveManager.loadGame(filename);
        assertNull(loadedState, "При загрузке некорректных данных должно возвращаться null");
    }
}