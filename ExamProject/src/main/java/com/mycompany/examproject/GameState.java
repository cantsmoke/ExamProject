package com.mycompany.examproject;

import com.mycompany.examproject.Map.CastleMapGenerator;
import java.io.Serializable;

/**
 * Класс для хранения состояния игры, реализующий интерфейс {@link Serializable}.
 * <p>
 * Содержит данные об игроке и карте замка, используемые для сохранения и загрузки
 * игрового прогресса в RPG-игре.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
public class GameState implements Serializable {
    
    /**
     * Идентификатор версии сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Игрок, чье состояние сохраняется.
     */
    public Player player;
    
    /**
     * Генератор карты замка, содержащий структуру игрового мира.
     */
    public CastleMapGenerator castleMap;

    /**
     * Конструктор для создания объекта состояния игры.
     *
     * @param player игрок, чье состояние сохраняется
     * @param castleMap генератор карты замка
     */
    public GameState(Player player, CastleMapGenerator castleMap) {
        this.player = player;
        this.castleMap = castleMap;
    }
}