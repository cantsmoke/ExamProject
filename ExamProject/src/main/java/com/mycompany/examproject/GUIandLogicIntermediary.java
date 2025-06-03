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
    
    private static StateAndNavigationForm stateAndNavigationForm;
    private static Player player;
    
    private GUIandLogicIntermediary(){}
    
    public static void handleNewGameButtonPressed(){ 
        CastleMapGenerator castleMapGenerator = new CastleMapGenerator();
        castleMapGenerator.generateMap();
        
        player = Player.getInstance(castleMapGenerator.getStartRoom());
        
        stateAndNavigationForm = new StateAndNavigationForm();
        stateAndNavigationForm.updateLabels();
        stateAndNavigationForm.setVisible(true);
        
        //обновляем все лейблы и поля в форме stateAndNavigationForm
        
    }
    
}