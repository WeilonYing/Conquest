/*
 * Copyright (C) 2014 Weilon
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Creative Commons Attribution-ShareAlike 4.0
 * International as published by the Creative Commons Organisation.
 *
 * You are free to copy, redistribute and create derivative works
 * from the material under the condition appropriate credit is
 * given to the creator. In addition, if you remix, transform or
 * build upon the material, you must distribute your contributions under
 * the same license as the original.
 *
 * You may get a copy of the license here: https://creativecommons.org/licenses/by-sa/4.0/
 */

package io.github.whalesaredelicious.conquest;

/**
 *
 * @author Weilon
 */
public class ConquestHowToPlay extends javax.swing.JDialog {

    /**
     * Creates new form ConquestHowToPlay
     */
    public ConquestHowToPlay(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        panel1.setVisible(true);
        panel2.setVisible(false);
        panel3.setVisible(false);
        setSize(800, 600);
        
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnDisplayPage1 = new javax.swing.JButton();
        btnDisplayPage2 = new javax.swing.JButton();
        btnDisplayPage3 = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        panel1 = new javax.swing.JPanel();
        lblHowToPlayImg1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        lblHowToPlayImg2 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        lblHowToPlayImg3 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        lblHowToPlayImg4 = new javax.swing.JLabel();
        btnDisplayPage4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitle.setFont(new java.awt.Font("Garamond", 1, 48)); // NOI18N
        lblTitle.setText("How to Play");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(289, 11, 254, 55);

        btnDisplayPage1.setText("Page 1");
        btnDisplayPage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayPage1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplayPage1);
        btnDisplayPage1.setBounds(10, 510, 102, 43);

        btnDisplayPage2.setText("Page 2");
        btnDisplayPage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayPage2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplayPage2);
        btnDisplayPage2.setBounds(120, 510, 102, 43);

        btnDisplayPage3.setText("Page 3");
        btnDisplayPage3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayPage3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplayPage3);
        btnDisplayPage3.setBounds(230, 510, 102, 43);

        btnExit.setText("Close");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(580, 510, 201, 43);

        lblHowToPlayImg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/io/github/whalesaredelicious/conquest/assets/HowToPlay1.png"))); // NOI18N

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblHowToPlayImg1)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblHowToPlayImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(panel1);
        panel1.setBounds(0, 73, 800, 370);

        lblHowToPlayImg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/io/github/whalesaredelicious/conquest/assets/HowToPlay2.png"))); // NOI18N

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblHowToPlayImg2)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblHowToPlayImg2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panel2);
        panel2.setBounds(0, 73, 800, 430);

        lblHowToPlayImg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/io/github/whalesaredelicious/conquest/assets/HowToPlay3.png"))); // NOI18N

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblHowToPlayImg3)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblHowToPlayImg3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panel3);
        panel3.setBounds(0, 76, 800, 430);

        lblHowToPlayImg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/io/github/whalesaredelicious/conquest/assets/HowToPlay4.png"))); // NOI18N

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblHowToPlayImg4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblHowToPlayImg4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panel4);
        panel4.setBounds(0, 73, 765, 370);

        btnDisplayPage4.setText("Page 4");
        btnDisplayPage4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayPage4ActionPerformed(evt);
            }
        });
        getContentPane().add(btnDisplayPage4);
        btnDisplayPage4.setBounds(340, 510, 102, 43);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDisplayPage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayPage1ActionPerformed
        panel1.setVisible(true);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
    }//GEN-LAST:event_btnDisplayPage1ActionPerformed

    private void btnDisplayPage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayPage2ActionPerformed
        panel1.setVisible(false);
        panel2.setVisible(true);
        panel3.setVisible(false);
        panel4.setVisible(false);
    }//GEN-LAST:event_btnDisplayPage2ActionPerformed

    private void btnDisplayPage3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayPage3ActionPerformed
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(true);
        panel4.setVisible(false);
        
    }//GEN-LAST:event_btnDisplayPage3ActionPerformed

    private void btnDisplayPage4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayPage4ActionPerformed
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(true);
    }//GEN-LAST:event_btnDisplayPage4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConquestHowToPlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConquestHowToPlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConquestHowToPlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConquestHowToPlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConquestHowToPlay dialog = new ConquestHowToPlay(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisplayPage1;
    private javax.swing.JButton btnDisplayPage2;
    private javax.swing.JButton btnDisplayPage3;
    private javax.swing.JButton btnDisplayPage4;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel lblHowToPlayImg1;
    private javax.swing.JLabel lblHowToPlayImg2;
    private javax.swing.JLabel lblHowToPlayImg3;
    private javax.swing.JLabel lblHowToPlayImg4;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    // End of variables declaration//GEN-END:variables
}
