/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.examproject.GUI;

import java.awt.Color;

/**
 * Диалоговое окно, отображающее информацию об успешном восстановлении здоровья игрока.
 * <p>
 * Наследует {@link javax.swing.JDialog} и появляется после успешного использования
 * лечебного предмета. Показывает текущее, максимальное и восстановленное значение здоровья.
 * </p>
 *
 * Пример использования:
 * <pre>
 *     PlayerHealedDialog dialog =
 *         new PlayerHealedDialog(parentFrame, true, newHealth, healthBefore, maxHealth);
 *     dialog.setVisible(true);
 * </pre>
 *
 * @author Arseniy
 */
public class PlayerHealedDialog extends javax.swing.JDialog {

    /**
     * Creates new form playerHealedDialog
     */
    public PlayerHealedDialog(java.awt.Frame parent, boolean modal, int newHealth, int healthBefore, int maxHealth) {
        super(parent, modal);
        getContentPane().setBackground(Color.BLACK);
        initComponents();
        setLocationRelativeTo(null);
        this.newHealth = newHealth;
        this.healthBefore = healthBefore;
        this.maxHealth = maxHealth;
        
        updateLabels();
        
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

        playerHealedPanel = new javax.swing.JPanel();
        youHealedLabel1 = new javax.swing.JLabel();
        youHealedLabel2 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        playerHealedPanel.setBackground(new java.awt.Color(0, 0, 0));

        youHealedLabel1.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        youHealedLabel1.setForeground(new java.awt.Color(204, 204, 204));
        youHealedLabel1.setText("jLabel1");

        youHealedLabel2.setFont(new java.awt.Font("Castellar", 0, 14)); // NOI18N
        youHealedLabel2.setForeground(new java.awt.Color(204, 204, 204));
        youHealedLabel2.setText("jLabel1");

        okButton.setBackground(new java.awt.Color(102, 102, 102));
        okButton.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        okButton.setForeground(new java.awt.Color(204, 204, 204));
        okButton.setText("ok");
        okButton.setBorder(null);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout playerHealedPanelLayout = new javax.swing.GroupLayout(playerHealedPanel);
        playerHealedPanel.setLayout(playerHealedPanelLayout);
        playerHealedPanelLayout.setHorizontalGroup(
            playerHealedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerHealedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerHealedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(youHealedLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(youHealedLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerHealedPanelLayout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        playerHealedPanelLayout.setVerticalGroup(
            playerHealedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playerHealedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(youHealedLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(youHealedLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerHealedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerHealedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void updateLabels() {
        youHealedLabel1.setText("You regened " + (newHealth - healthBefore) + " HP!");
        youHealedLabel2.setText("Your current health: " + newHealth + "/" + maxHealth);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton okButton;
    private javax.swing.JPanel playerHealedPanel;
    private javax.swing.JLabel youHealedLabel1;
    private javax.swing.JLabel youHealedLabel2;
    // End of variables declaration//GEN-END:variables
    private int newHealth;
    private int healthBefore;
    private int maxHealth;
}