/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.examproject.GUI;

import com.mycompany.examproject.Enemies.enemyStructure.Enemy;
import com.mycompany.examproject.Fight;
import com.mycompany.examproject.GUIandLogicIntermediary;
import com.mycompany.examproject.Player;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Окно с интерфейсом для проведения боя между игроком и противником.
 * <p>
 * Наследует {@link javax.swing.JFrame} и отображает все элементы, необходимые для управления боем:
 * сведения об участниках, их характеристики, кнопки для выбора действий (атака, защита, использование предметов и др.),
 * а также сообщения о ходе сражения и результатах.
 * </p>
 *
 * Пример использования:
 * <pre>
 *     BattleForm battleForm = new BattleForm();
 *     battleForm.setVisible(true);
 * </pre>
 *
 * Обычно вызывается, когда игрок вступает в бой с врагом.
 *
 * @author Arseniy
 */
public class BattleForm extends javax.swing.JFrame {

    /**
     * Creates new form BattleForm
     */
    public BattleForm() {
        initComponents();
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BattleFramePanel = new javax.swing.JPanel();
        logScrollPane = new javax.swing.JScrollPane();
        battleLogArea = new javax.swing.JTextArea();
        enemyIconLabel = new javax.swing.JLabel();
        attackButton = new javax.swing.JButton();
        playerIconLabel = new javax.swing.JLabel();
        playerHpBar = new javax.swing.JProgressBar();
        enemyHpBar = new javax.swing.JProgressBar();
        playerStaminaBar = new javax.swing.JProgressBar();
        inventoryButton = new javax.swing.JButton();
        dodgeButton = new javax.swing.JButton();
        blockButton = new javax.swing.JButton();
        enemyNameLabel = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        playerBaseDamageLabel = new javax.swing.JLabel();
        enemyBaseDamageLabel = new javax.swing.JLabel();
        skipTurnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BattleFramePanel.setBackground(new java.awt.Color(0, 0, 0));

        logScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        logScrollPane.setBorder(new AntiqueBorder());

        battleLogArea.setEditable(false);
        battleLogArea.setBackground(new java.awt.Color(0, 0, 0));
        battleLogArea.setColumns(20);
        battleLogArea.setFont(new java.awt.Font("Yu Mincho Light", 0, 13)); // NOI18N
        battleLogArea.setForeground(new java.awt.Color(204, 204, 204));
        battleLogArea.setLineWrap(true);
        battleLogArea.setRows(5);
        battleLogArea.setWrapStyleWord(true);
        battleLogArea.setBorder(null);
        battleLogArea.setCaretColor(new java.awt.Color(0, 0, 0));
        logScrollPane.setViewportView(battleLogArea);

        enemyIconLabel.setBackground(new java.awt.Color(0, 0, 0));

        attackButton.setBackground(new java.awt.Color(51, 51, 51));
        attackButton.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        attackButton.setForeground(new java.awt.Color(204, 204, 204));
        attackButton.setText("ATTACK");
        attackButton.setBorder(null);
        attackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackButtonActionPerformed(evt);
            }
        });

        playerIconLabel.setBackground(new java.awt.Color(0, 0, 0));
        playerIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatGPT Image 3 июн. 2025 г., 17_05_41 (1).png"))); // NOI18N

        playerHpBar.setBackground(new java.awt.Color(0, 20, 0));
        playerHpBar.setForeground(new java.awt.Color(0, 153, 0));
        playerHpBar.setBorder(null);
        playerHpBar.setStringPainted(true);

        enemyHpBar.setBackground(new java.awt.Color(0, 20, 0));
        enemyHpBar.setForeground(new java.awt.Color(0, 153, 0));
        enemyHpBar.setBorder(null);
        enemyHpBar.setStringPainted(true);

        playerStaminaBar.setBackground(new java.awt.Color(0, 51, 102));
        playerStaminaBar.setForeground(new java.awt.Color(0, 102, 204));
        playerStaminaBar.setBorder(null);
        playerStaminaBar.setStringPainted(true);

        inventoryButton.setBackground(new java.awt.Color(51, 51, 51));
        inventoryButton.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        inventoryButton.setForeground(new java.awt.Color(204, 204, 204));
        inventoryButton.setText("Inventory");
        inventoryButton.setBorder(null);
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });

        dodgeButton.setBackground(new java.awt.Color(51, 51, 51));
        dodgeButton.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        dodgeButton.setForeground(new java.awt.Color(204, 204, 204));
        dodgeButton.setText("dodge");
        dodgeButton.setBorder(null);
        dodgeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodgeButtonActionPerformed(evt);
            }
        });

        blockButton.setBackground(new java.awt.Color(51, 51, 51));
        blockButton.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        blockButton.setForeground(new java.awt.Color(204, 204, 204));
        blockButton.setText("Block");
        blockButton.setBorder(null);
        blockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockButtonActionPerformed(evt);
            }
        });

        enemyNameLabel.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        enemyNameLabel.setForeground(new java.awt.Color(204, 204, 204));
        enemyNameLabel.setText("jLabel1");

        playerNameLabel.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        playerNameLabel.setForeground(new java.awt.Color(204, 204, 204));
        playerNameLabel.setText("You");

        playerBaseDamageLabel.setFont(new java.awt.Font("Castellar", 0, 12)); // NOI18N
        playerBaseDamageLabel.setForeground(new java.awt.Color(204, 204, 204));
        playerBaseDamageLabel.setText("jLabel1");

        enemyBaseDamageLabel.setFont(new java.awt.Font("Castellar", 0, 12)); // NOI18N
        enemyBaseDamageLabel.setForeground(new java.awt.Color(204, 204, 204));
        enemyBaseDamageLabel.setText("jLabel2");

        skipTurnButton.setBackground(new java.awt.Color(51, 51, 51));
        skipTurnButton.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        skipTurnButton.setForeground(new java.awt.Color(204, 204, 204));
        skipTurnButton.setText("SKIP TURN");
        skipTurnButton.setBorder(null);
        skipTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipTurnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BattleFramePanelLayout = new javax.swing.GroupLayout(BattleFramePanel);
        BattleFramePanel.setLayout(BattleFramePanelLayout);
        BattleFramePanelLayout.setHorizontalGroup(
            BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BattleFramePanelLayout.createSequentialGroup()
                .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BattleFramePanelLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(blockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dodgeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inventoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(enemyHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(BattleFramePanelLayout.createSequentialGroup()
                            .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(BattleFramePanelLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(playerHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(playerIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(playerStaminaBar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(56, 56, 56))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BattleFramePanelLayout.createSequentialGroup()
                                    .addGap(97, 97, 97)
                                    .addComponent(playerNameLabel)))
                            .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(logScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BattleFramePanelLayout.createSequentialGroup()
                                    .addComponent(skipTurnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(33, 33, 33)))
                            .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(BattleFramePanelLayout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(enemyIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BattleFramePanelLayout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(enemyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(BattleFramePanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(playerBaseDamageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enemyBaseDamageLabel)
                .addGap(143, 143, 143))
        );
        BattleFramePanelLayout.setVerticalGroup(
            BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BattleFramePanelLayout.createSequentialGroup()
                .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BattleFramePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BattleFramePanelLayout.createSequentialGroup()
                                .addComponent(playerNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(playerIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(logScrollPane)))
                    .addGroup(BattleFramePanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(enemyNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(enemyIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enemyBaseDamageLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(playerBaseDamageLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BattleFramePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(playerHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enemyHpBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playerStaminaBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BattleFramePanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(skipTurnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(BattleFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dodgeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BattleFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BattleFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        GUIandLogicIntermediary.handlePlayerAttackAction();
    }//GEN-LAST:event_attackButtonActionPerformed

    private void blockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockButtonActionPerformed
        GUIandLogicIntermediary.handlePlayerBlockAction();
    }//GEN-LAST:event_blockButtonActionPerformed

    private void dodgeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodgeButtonActionPerformed
        GUIandLogicIntermediary.handlePlayerDodgeAction();
    }//GEN-LAST:event_dodgeButtonActionPerformed

    private void skipTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipTurnButtonActionPerformed
        GUIandLogicIntermediary.handlePlayerSkipAction();
    }//GEN-LAST:event_skipTurnButtonActionPerformed

    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryButtonActionPerformed
        GUIandLogicIntermediary.handlePlayerOpenDialogFromBattle();
    }//GEN-LAST:event_inventoryButtonActionPerformed
    
    /**
    * Добавляет новое сообщение в журнал боя с визуальным разделителем.
    * <p>
    * Сообщение добавляется в текстовую область журнала боя, после каждого сообщения добавляется строка "-----------".
    * </p>
    *
    * @param message Сообщение, которое будет добавлено в журнал боя.
    */
    public void appendToLogArea(String message) {
        battleLogArea.append(message + "\n" + "-----------" + "\n");
    }
    
    /**
    * Обновляет доступность и видимость кнопок управления боем в зависимости от выносливости игрока.
    * <p>
    * Если у игрока есть выносливость ({@code stamina}>0), кнопка пропуска хода скрывается и выключается,
    * остальные кнопки (атака, блок, уворот, инвентарь) становятся активными.
    * Если выносливость на нуле, активной остаётся только уворот и инвентарь, 
    * а кнопка пропуска становится видимой и доступной.
    * </p>
    *
    * @param player Текущий игрок, чья выносливость анализируется.
    */
    public void updateButtonAvaibility(Player player){
        if(player.getStamina() > 0){
            skipTurnButton.setVisible(false);
            skipTurnButton.setEnabled(false);
            attackButton.setEnabled(true);
            blockButton.setEnabled(true);
            dodgeButton.setEnabled(true);
            inventoryButton.setEnabled(true);
        }
        if(player.getStamina() <= 0){
            player.setStamina(0);
            playerStaminaBar.setValue(player.getStamina());
            skipTurnButton.setVisible(true);
            skipTurnButton.setEnabled(true);
            attackButton.setEnabled(false);
            blockButton.setEnabled(false);
            dodgeButton.setEnabled(true);
            inventoryButton.setEnabled(true);
        }
    }
    
    /**
    * Обновляет отображение информации о состоянии игрока и врага на панели боя.
    * <p>
    * Обновляются: иконка, имя, полоски здоровья и выносливости, значения урона,
    * а также цвет полосок здоровья в критическом состоянии (меньше 25%).
    * Если у персонажа здоровье меньше нуля, на индикаторе показывается 0.
    * В конце вызывается {@link #updateButtonAvaibility(Player)}.
    * </p>
    *
    * @param player Объект игрока, чьи данные отображаются.
    * @param enemy  Объект врага, чьи данные отображаются.
    */
    public void updateLabels(Player player, Enemy enemy){
        skipTurnButton.setVisible(false);
        skipTurnButton.setEnabled(false);
        
        enemyIconLabel.setIcon(new ImageIcon(getClass().getResource(enemy.getIconSource())));
        enemyNameLabel.setText(enemy.getName());
        enemyHpBar.setMaximum(enemy.getMaxHealth());
        enemyHpBar.setValue(enemy.getHealth());
        enemyHpBar.setString(enemy.getHealth() + "/" + enemy.getMaxHealth());
        enemyBaseDamageLabel.setText("Damage: " + enemy.getDamage());
        
        playerHpBar.setMaximum(player.getMaxHp());
        playerHpBar.setValue(player.getHp());
        playerHpBar.setString(player.getHp() + "/" + player.getMaxHp());
        
        playerStaminaBar.setMaximum(player.getMaxStamina());
        playerStaminaBar.setValue(player.getStamina());
        playerStaminaBar.setString(player.getStamina()+ "/" + player.getMaxStamina());
        
        playerHpBar.repaint();
        playerStaminaBar.repaint();

        playerBaseDamageLabel.setText("Damage: " + (player.getBaseDamage() + player.getSelectedWeapon().getDamage()));

        if(player.getHp() < player.getMaxHp() * 0.25){
            playerHpBar.setForeground(Color.red);
            playerHpBar.setBackground(new Color(50, 0, 0));
        } else {
            playerHpBar.setForeground(new Color(0, 153, 0));
            playerHpBar.setBackground(new Color(0, 20, 0));
        }
        if(enemy.getHealth() < enemy.getMaxHealth() * 0.25){
            enemyHpBar.setForeground(Color.red);
            enemyHpBar.setBackground(new Color(50, 0, 0));
        } else {
            enemyHpBar.setForeground(new Color(0, 153, 0));
        }
        if(player.getHp() < 0){
            playerHpBar.setString(0 + "/" + enemy.getMaxHealth());
        }
        if(enemy.getHealth() < 0){
            enemyHpBar.setString(0 + "/" + enemy.getMaxHealth());
        }
        
        updateButtonAvaibility(Player.getInstance());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BattleFramePanel;
    private javax.swing.JButton attackButton;
    private javax.swing.JTextArea battleLogArea;
    private javax.swing.JButton blockButton;
    private javax.swing.JButton dodgeButton;
    private javax.swing.JLabel enemyBaseDamageLabel;
    private javax.swing.JProgressBar enemyHpBar;
    private javax.swing.JLabel enemyIconLabel;
    private javax.swing.JLabel enemyNameLabel;
    private javax.swing.JButton inventoryButton;
    private javax.swing.JScrollPane logScrollPane;
    private javax.swing.JLabel playerBaseDamageLabel;
    private javax.swing.JProgressBar playerHpBar;
    private javax.swing.JLabel playerIconLabel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JProgressBar playerStaminaBar;
    private javax.swing.JButton skipTurnButton;
    // End of variables declaration//GEN-END:variables
}