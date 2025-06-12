/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.Enemies;

import com.mycompany.examproject.Enemies.enemyStructure.WormOfLivingStone;
import com.mycompany.examproject.Enemies.enemyStructure.WardenOfDeathSands;
import com.mycompany.examproject.Enemies.enemyStructure.TheUnhallowedArchon;
import com.mycompany.examproject.Enemies.enemyStructure.SirenOfOblivion;
import com.mycompany.examproject.Enemies.enemyStructure.LordOfLabyrinthineShadows;
import com.mycompany.examproject.Enemies.enemyStructure.ExecutionerOfTheLastCreed;
import com.mycompany.examproject.Enemies.enemyStructure.ConfessorOfTheRottingLight;
import com.mycompany.examproject.Enemies.enemyStructure.BroodmotherOfTheHollowedWeb;
import com.mycompany.examproject.Enemies.enemyStructure.BloodHeirOfTheMark;
import com.mycompany.examproject.Enemies.enemyStructure.AshenGatekeeper;
import com.mycompany.examproject.Enemies.enemyStructure.Boss;
import com.mycompany.examproject.Enemies.enemyStructure.BossType;

/**
 *
 * @author Arseniy
 */
public class BossFactory {
    
    public static Boss createBoss(BossType bossType){
        switch (bossType) {
            case FLOOR_ONE_BOSS:
                return new AshenGatekeeper("Ashen Gatekeeper", 300, 30, 0);
            case FLOOR_TWO_BOSS:
                return new SirenOfOblivion("Siren of Oblivion", 400, 60, 0);
            case FLOOR_THREE_BOSS:
                return new WormOfLivingStone("Worm of Living Stone", 500, 70, 0);
            case FLOOR_FOUR_BOSS:
                return new ConfessorOfTheRottingLight("Confessor of the Rotting Light", 550, 80, 0);
            case FLOOR_FIVE_BOSS:
                return new BloodHeirOfTheMark("Blood Heir of the Mark", 600, 90, 0);
            case FLOOR_SIX_BOSS:
                return new WardenOfDeathSands("Warden of the Death Sands", 650, 90, 0);
            case FLOOR_SEVEN_BOSS:
                return new BroodmotherOfTheHollowedWeb("Broodmother of the Hollowed Web", 700, 95, 0);
            case FLOOR_EIGHT_BOSS:
                return new LordOfLabyrinthineShadows("Lord of Labyrinthine Shadows", 700, 95, 0);
            case FLOOR_NINE_BOSS:
                return new ExecutionerOfTheLastCreed("Executioner of the Last Creed", 600, 120, 0);
            case FLOOR_TEN_BOSS:
                return new TheUnhallowedArchon("The Unhallowed Archon", 1000, 100, 0);
            default:
                throw new IllegalArgumentException("Такого типа босса нет: " + bossType);
        }
    }
    
}
