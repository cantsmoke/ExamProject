/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.examproject;

import com.mycompany.examproject.GUI.MainMenuForm;
import javax.swing.SwingUtilities;
/**
 * Главный класс приложения ExamProject.
 * <p>
 * Запускает графический интерфейс программы и отображает главное меню.
 * </p>
 *
 * @author Arseniy
 */
public class ExamProject {

    /**
     * Точка входа в программу.
     * <p>
     * Инициализирует интерфейс Swing и отображает главное меню приложения.
     * </p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuForm frame = new MainMenuForm();
            frame.setVisible(true);
        });
    }
}
