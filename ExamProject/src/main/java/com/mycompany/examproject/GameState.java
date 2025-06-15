package com.mycompany.examproject;
/**
 *
 * @author Arseniy
 */
import com.mycompany.examproject.Map.CastleMapGenerator;
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