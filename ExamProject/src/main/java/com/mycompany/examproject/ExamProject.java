/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examproject;

import com.mycompany.examproject.GUI.MainMenuForm;
import javax.swing.SwingUtilities;
/**
 *
 * @author Arseniy
 */
public class ExamProject {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuForm frame = new MainMenuForm();
            frame.setVisible(true);
        });
    }
}