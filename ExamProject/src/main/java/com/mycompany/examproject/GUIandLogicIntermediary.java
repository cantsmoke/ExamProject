/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

import com.mycompany.examproject.GUI.StateAndNavigationForm;
import com.mycompany.examproject.Map.CastleMapGenerator;

/**
 *
 * @author Arseniy
 */
public class GUIandLogicIntermediary {
    
    private GUIandLogicIntermediary(){}
    
    public static void handleNewGameButtonPressed(){ 
        CastleMapGenerator castleMapGenerator = new CastleMapGenerator();
        castleMapGenerator.generateMap();
        
        Player player = new Player(castleMapGenerator.getStartRoom());
        
        StateAndNavigationForm stateAndNavigationForm = new StateAndNavigationForm();
        stateAndNavigationForm.setVisible(true);
        
        //обновляем все лейблы и поля в форме stateAndNavigationForm
        
    }
    
}