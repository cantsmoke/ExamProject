/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.examproject.Items;

import java.io.Serializable;

/**
 *
 * @author Arseniy
 */
public interface Equipment extends Serializable{
    void checkStatus();
    String getName();
}