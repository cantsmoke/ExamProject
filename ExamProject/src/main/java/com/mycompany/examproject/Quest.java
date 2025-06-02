/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

/**
 *
 * @author Arseniy
 */
public class Quest {
    private String description;
    private int targetCount; // E.g., kill 5 skeletons
    private int currentCount;
    private String reward; // E.g., "100 souls" or "Dragon Sword"

    public Quest(String description, int targetCount, String reward) {
        this.description = description;
        this.targetCount = targetCount;
        this.currentCount = 0;
        this.reward = reward;
    }

    public String getDescription() { return description; }
    public int getTargetCount() { return targetCount; }
    public int getCurrentCount() { return currentCount; }
    public String getReward() { return reward; }
    public void incrementProgress() { currentCount++; }
    public boolean isComplete() { return currentCount >= targetCount; }
}