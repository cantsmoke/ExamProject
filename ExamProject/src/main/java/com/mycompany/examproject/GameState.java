package com.mycompany.examproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Arseniy
 */
import com.mycompany.examproject.Map.CastleMapGenerator;
import com.mycompany.examproject.Player;
import java.io.Serializable;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    public Player player;
    public CastleMapGenerator castleMap;

    public GameState(Player player, CastleMapGenerator castleMap) {
        this.player = player;
        this.castleMap = castleMap;
    }
}