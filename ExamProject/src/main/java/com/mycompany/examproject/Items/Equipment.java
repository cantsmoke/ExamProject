/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.examproject.Items;

import java.io.Serializable;

/**
 * Интерфейс, представляющий экипировку в игре.
 * <p>
 * Описывает базовые методы для любой экипировки, которая может использоваться персонажем.
 * Все классы, реализующие этот интерфейс, должны быть сериализуемыми.
 * </p>
 *
 * @author Arseniy
 */
public interface Equipment extends Serializable {
    /**
     * Проверяет и возвращает текущее состояние или статус экипировки.
     * <p>
     * Метод может выводить информацию о состоянии предмета (например, прочность или наличие улучшений)
     * или выполнять необходимые проверки/обновления состояния.
     * </p>
     */
    void checkStatus();

    /**
     * Возвращает имя экипировки.
     *
     * @return имя предмета экипировки
     */
    String getName();
}