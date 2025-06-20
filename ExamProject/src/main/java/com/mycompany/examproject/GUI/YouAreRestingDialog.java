/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.examproject.GUI;

import com.mycompany.examproject.GUIandLogicIntermediary;
import java.awt.Color;

/**
 * Диалоговое окно, информирующее игрока о том, что он находится в режиме отдыха.
 * <p>
 * Наследует {@link javax.swing.JDialog} и появляется, когда игрок отдыхает для восстановления
 * здоровья, энергии и запасов эстуса.
 * </p>
 *
 * Пример использования:
 * <pre>
 *     YouAreRestingDialog dialog = new YouAreRestingDialog(parentFrame, true);
 *     dialog.setVisible(true);
 * </pre>
 *
 * @author Arseniy
 */
public class YouAreRestingDialog extends javax.swing.JDialog {

    /**
     * Creates new form YouAreRestingDialog
     */
    public YouAreRestingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        getContentPane().setBackground(Color.BLACK);
        initComponents();
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

        restPanel = new javax.swing.JPanel();
        restLabel1 = new javax.swing.JLabel();
        restLabel2 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        restIconLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        restPanel.setBackground(new java.awt.Color(0, 0, 0));

        restLabel1.setFont(new java.awt.Font("Castellar", 0, 24)); // NOI18N
        restLabel1.setForeground(new java.awt.Color(204, 204, 204));
        restLabel1.setText("you found rest room!");

        restLabel2.setFont(new java.awt.Font("Castellar", 0, 18)); // NOI18N
        restLabel2.setForeground(new java.awt.Color(204, 204, 204));
        restLabel2.setText("you rested and restored hp and estus");

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

        restIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChatGPT Image 15 июн. 2025 г., 17_07_03 (1).png"))); // NOI18N
        restIconLabel.setBorder(new AntiqueBorder());

        javax.swing.GroupLayout restPanelLayout = new javax.swing.GroupLayout(restPanel);
        restPanel.setLayout(restPanelLayout);
        restPanelLayout.setHorizontalGroup(
            restPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restPanelLayout.createSequentialGroup()
                .addGroup(restPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, restPanelLayout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(restLabel2))
                    .addGroup(restPanelLayout.createSequentialGroup()
                        .addGroup(restPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(restPanelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(restLabel1))
                            .addGroup(restPanelLayout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, restPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(restIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        restPanelLayout.setVerticalGroup(
            restPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(restPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(restLabel1)
                .addGap(30, 30, 30)
                .addComponent(restLabel2)
                .addGap(38, 38, 38)
                .addComponent(restIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.dispose();
        GUIandLogicIntermediary.showNavigationForm();
    }//GEN-LAST:event_okButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton okButton;
    private javax.swing.JLabel restIconLabel;
    private javax.swing.JLabel restLabel1;
    private javax.swing.JLabel restLabel2;
    private javax.swing.JPanel restPanel;
    // End of variables declaration//GEN-END:variables
}
