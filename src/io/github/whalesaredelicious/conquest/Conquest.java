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

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Weilon
 */
public class Conquest extends JFrame {
    //Declare strings, booleans, and integers
    public static String stringPlayerTurn;
    public static String stringPlayer1 = "Player 1";
    public static String stringPlayer2 = "Player 2";
    
    public int intNumMoves = 0;
    
    public int intUnit1_1Power = 1;
    public int intUnit1_2Power = 1;
    public int intUnit1_3Power = 1;
    public int intUnit2_1Power = 1;
    public int intUnit2_2Power = 1;
    public int intUnit2_3Power = 1;
    
    public static boolean booleanIsPlayer1Turn = true;
    public static boolean[] booleanPlayer1UnitSelected = new boolean[3];
    public static boolean[] booleanPlayer2UnitSelected = new boolean[3];
    private boolean booleanUnitSelected = false; //Used to prevent decrementing a move when unit is not selected.
    
    //Declare control buttons
    public final JButton btnMoveUp = new JButton("Up");
    public final JButton btnMoveDown = new JButton("Down");
    public final JButton btnMoveLeft = new JButton("Left");
    public final JButton btnMoveRight = new JButton("Right");
    
    private final JButton btnSelectUnit1 = new JButton("Unit 1");
    private final JButton btnSelectUnit2 = new JButton("Unit 2");
    private final JButton btnSelectUnit3 = new JButton("Unit 3");
    
    private final JButton btnUpgradeUnit1 = new JButton("+");
    private final JButton btnUpgradeUnit2 = new JButton("+");
    private final JButton btnUpgradeUnit3 = new JButton("+");
    
    private final JButton btnRollDice = new JButton("Roll Dice");
    private final JButton btnEndTurn = new JButton("End Turn");
    
    private final JButton btnAttack = new JButton("Attack");
    private final JButton btnCapture = new JButton("Capture");
    
    //Declare labels
    private final JLabel lblUnit1Power = new JLabel("0");
    private final JLabel lblUnit2Power = new JLabel("0");
    private final JLabel lblUnit3Power = new JLabel("0");
    
    private final JLabel lblWhoseTurn = new JLabel("Turn: " + stringPlayerTurn);
    private final JLabel lblCurrentSelectedUnit = new JLabel("Current selected unit: None");
    private final JLabel lblNumMoves = new JLabel("No. of moves left: " + Integer.toString(intNumMoves));
    private final JLabel lblPlayer1 = new JLabel(stringPlayer1, JLabel.CENTER);
    private final JLabel lblPlayer2 = new JLabel(stringPlayer2, JLabel.CENTER);
    private final JLabel lblVs = new JLabel("vs.", JLabel.CENTER);
    
    //Constructor method
    public Conquest() {
        super("Conquest");
        
        initComponents();
        initControls();
        initLabels();
        
        setLayout(null);
        setResizable(false);
        setSize(600, 400);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLblUnitPower();
    }
    
    //Main method
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            getLogger(Conquest.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConquestStart start = new ConquestStart(null, true);
                start.setVisible(true);
                
                new Conquest().setVisible(true);
                
                ConquestMap conquestmap = new ConquestMap();
                conquestmap.setVisible(true);
                conquestmap.initLocations();
            }
        });
    }
    
    //Initialisation methods - setting up GUI elements
    private void initControls() {
        /*
            Higher-level method that oversees the initialisation
            setting parameters, creating ActionListeners and
            finally adding controls to the window.
        */
        
        //Create ActionListeners
        initActionListeners();
        setButtonParameters();

        //Add the buttons to the screen
        add(btnMoveUp);
        add(btnMoveDown);
        add(btnMoveLeft);
        add(btnMoveRight);
        
        add(btnSelectUnit1);
        add(btnSelectUnit2);
        add(btnSelectUnit3);
        
        add(btnUpgradeUnit1);
        add(btnUpgradeUnit2);
        add(btnUpgradeUnit3);
        
        add(btnEndTurn);
        add(btnRollDice);
        
        add(btnAttack);
        add(btnCapture);
    }
    private void initActionListeners() { //Create ActionListeners
        /*
            Action Listeners are needed for registering a button click event to the program.
        */
        btnMoveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoveUpPressed(evt);
            }
        });
        btnMoveDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoveDownPressed(evt);
            }
        });
        btnMoveLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoveLeftPressed(evt);
            }
        });
        btnMoveRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnMoveRightPressed(evt);
            }
        });
        
        btnSelectUnit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelectUnit1Pressed(evt);
            }
        });
        btnSelectUnit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelectUnit2Pressed(evt);
            }
        });
        btnSelectUnit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelectUnit3Pressed(evt);
            }
        });
        btnUpgradeUnit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpgradeUnit1Pressed(evt);
            }
        });
        btnUpgradeUnit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpgradeUnit2Pressed(evt);
            }
        });
        btnUpgradeUnit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpgradeUnit3Pressed(evt);
            }
        });
        
        btnEndTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEndTurnPressed(evt);
            }
        });
        btnRollDice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRollDicePressed(evt);
            }
        });
        
        btnAttack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAttackPressed(evt);
            }
        });
        btnCapture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCapturePressed(evt);
            }
        });
    }
    private void initLabels() {
        /*
            Used for setting up labels for displaying relevant info.
        */
        setLabelParameters();
        
        add(lblUnit1Power);
        add(lblUnit2Power);
        add(lblUnit3Power);
        
        add(lblWhoseTurn);
        add(lblNumMoves);
        add(lblCurrentSelectedUnit);
        
        add(lblPlayer1);
        add(lblPlayer2);
        add(lblVs);
    }
    private void setButtonParameters() {
        //Sets the size and location of the buttons.

        //button.setBounds(LocationX, LocationY, SizeX, SizeY
        int boundaryX = this.getSize().width, boundaryY = this.getSize().height;

        btnMoveUp.setBounds((boundaryX - 160), (boundaryY - 240), 60, 60);
        btnMoveDown.setBounds((boundaryX - 160), (boundaryY - 140), 60, 60);
        btnMoveLeft.setBounds((boundaryX - 220), (boundaryY - 190), 60, 60);
        btnMoveRight.setBounds((boundaryX - 100), (boundaryY - 190), 60, 60);

        btnSelectUnit1.setBounds((boundaryX - 550), (boundaryY - 220), 100, 40);
        btnSelectUnit2.setBounds((boundaryX - 550), (boundaryY - 170), 100, 40);
        btnSelectUnit3.setBounds((boundaryX - 550), (boundaryY - 120), 100, 40);
        
        btnUpgradeUnit1.setBounds((boundaryX - 450), (boundaryY - 220), 50, 40);
        btnUpgradeUnit2.setBounds((boundaryX - 450), (boundaryY - 170), 50, 40);
        btnUpgradeUnit3.setBounds((boundaryX - 450), (boundaryY - 120), 50, 40);
        
        btnEndTurn.setBounds((boundaryX - 150), (boundaryY - 20), 100, 40);
        btnRollDice.setBounds((boundaryX - 150), (boundaryY - 60), 100, 40);
        
        btnAttack.setBounds((boundaryX - 350), (boundaryY - 60), 150, 40);
        btnCapture.setBounds((boundaryX - 350), (boundaryY - 20), 150, 40);
        //Set button fonts
        Font fontBold = new Font("sans-serif", Font.BOLD, 18);
        Font fontMedium = new Font("sans-serif", Font.PLAIN, 14);
        btnUpgradeUnit1.setFont(fontBold);
        btnUpgradeUnit2.setFont(fontBold);
        btnUpgradeUnit3.setFont(fontBold);
        
        btnAttack.setFont(fontMedium);
        btnCapture.setFont(fontMedium);
        
        //Move count related buttons are disabled initially
        toggleMoveCountButtonState(false);

    }
    private void setLabelParameters() {
        //Sets the size and location of the labels.
        
        //label.setBounds(LocationX, LocationY, SizeX, SizeY
        int boundaryX = this.getSize().width, boundaryY = this.getSize().height;
        
        lblUnit1Power.setBounds(boundaryX - 575, boundaryY - 205, 30, 10);
        lblUnit2Power.setBounds(boundaryX - 575, boundaryY - 155, 30, 10);
        lblUnit3Power.setBounds(boundaryX - 575, boundaryY - 105, 30, 10);
        
        lblWhoseTurn.setBounds(boundaryX - 575, boundaryY - 70, 200, 30);
        lblNumMoves.setBounds(boundaryX - 575, boundaryY - 40, 200, 30);
        lblCurrentSelectedUnit.setBounds((boundaryX - 575), (boundaryY - 10), 200, 30);
        
        lblPlayer1.setBounds(boundaryX - 550, boundaryY - 300, 150, 30);
        lblPlayer2.setBounds(boundaryX - 250, boundaryY - 300, 150, 30);
        lblVs.setBounds(boundaryX - 350, boundaryY - 300, 30, 30);
        
        //Sets the font parameters of the labels. By default it is serif, plain and font size 10
        Font fontStandard = new Font("sans-serif", Font.PLAIN, 16);
        lblWhoseTurn.setFont(fontStandard);
        lblNumMoves.setFont(fontStandard);
        lblCurrentSelectedUnit.setFont(fontStandard);
        
        lblPlayer1.setFont(fontStandard);
        lblPlayer2.setFont(fontStandard);
        lblVs.setFont(fontStandard);
        
    }
    
    //Unit management methods
    private boolean processMove(int amount) { //Checks if player has enough points to execute an action and subtracts the appropriate amount.
        if ((intNumMoves - amount) >= 0) { //Checks if amount to remove will still be greater or equal to 0
            intNumMoves -= amount;
            setNumTurnsLabel(intNumMoves);
            toggleMoveCountButtonState(true);
            if (intNumMoves == 0) {
                toggleMoveCountButtonState(false);
            }
            return true;
        }
        else {
            intNumMoves = 0; //Setting it to 0 in case intNumMoves is somehow < 0
            setNumTurnsLabel(intNumMoves);
            toggleMoveCountButtonState(false);
            return false;
        }
    }
    private void prepareMove(int moveDirection) { //Ensures a unit is selected before executing.
        if (booleanUnitSelected) {
            if (processMove(1)) {
                ConquestMap.move(moveDirection);
                checkPossibleMoveDirections();
            }
        }
    }
    private void upgradeUnit(int unitNumber, int upgradeAmount) { //Makes the relevant checks, then processes the unit upgrade.
        //If invalid unit selected, boolean flag will prevent a move being subtracted from the player.
        boolean booleanUpgradeSuccessful = false;
        if (booleanIsPlayer1Turn) {
            switch (unitNumber) {
                case 1:
                    intUnit1_1Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                case 2:
                    intUnit1_2Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                case 3:
                    intUnit1_3Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                default:
                    log("Invalid unit number");
                    break;
            }
        }
        else {
            switch (unitNumber) {
                case 1:
                    intUnit2_1Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                case 2:
                    intUnit2_2Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                case 3:
                    intUnit2_3Power += upgradeAmount;
                    booleanUpgradeSuccessful = true;
                    break;
                default:
                    log("Invalid unit number");
            }
        }
        limitUnitPower();
        setLblUnitPower();
        if (booleanUpgradeSuccessful) {
            processMove(upgradeAmount);
        }
        checkPossibleMoveDirections();
    }
    private void limitUnitPower() {
        /**
         * If player somehow reaches a ridiculous amount of power,
         * limit power to 9001 to prevent graphical glitches in the GUI.
         * 9999 can be used too, but 9001 is better (Please don't take
         * marks off for this!)
         */
        if (intUnit1_1Power > 9001) {intUnit1_1Power = 9001;}
        if (intUnit1_2Power > 9001) {intUnit1_2Power = 9001;}
        if (intUnit1_3Power > 9001) {intUnit1_3Power = 9001;}
        if (intUnit2_1Power > 9001) {intUnit2_1Power = 9001;}
        if (intUnit2_2Power > 9001) {intUnit2_2Power = 9001;}
        if (intUnit2_3Power > 9001) {intUnit2_3Power = 9001;}
    }
    
    //GUI management methods
    private void setNumTurnsLabel (int num) {
        lblNumMoves.setText("No. of moves left: " + Integer.toString(num));
    }
    private void setLblUnitPower() {
        if (booleanIsPlayer1Turn) {
            lblUnit1Power.setText(Integer.toString(intUnit1_1Power));
            lblUnit2Power.setText(Integer.toString(intUnit1_2Power));
            lblUnit3Power.setText(Integer.toString(intUnit1_3Power));
        }
        else {
            lblUnit1Power.setText(Integer.toString(intUnit2_1Power));
            lblUnit2Power.setText(Integer.toString(intUnit2_2Power));
            lblUnit3Power.setText(Integer.toString(intUnit2_3Power));
        }
    }
    private void toggleMoveCountButtonState(boolean state) {
        //Greys out or re-enables the move buttons depending on its set parameter.
        btnMoveUp.setEnabled(state);
        btnMoveDown.setEnabled(state);
        btnMoveLeft.setEnabled(state);
        btnMoveRight.setEnabled(state);
        
        btnUpgradeUnit1.setEnabled(state);
        btnUpgradeUnit2.setEnabled(state);
        btnUpgradeUnit3.setEnabled(state);
    }
    private void checkPossibleMoveDirections() {
        //1 = up, 2 = right, 3 = down, 4 = left
        //Ensures buttons aren't set enabled if user doesn't have any moves left anyway.
        if (intNumMoves > 0) {
            toggleMoveCountButtonState(true);
        }
        //Initialise unitSelected variable.
        int unitSelected = 0;
        
        //Find the current selected unit.
        if (booleanIsPlayer1Turn) {
            for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
                if (Conquest.booleanPlayer1UnitSelected[i]) {
                    unitSelected = i;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < booleanPlayer2UnitSelected.length; i++) {
                if (booleanPlayer2UnitSelected[i]) {
                    unitSelected = i + 3;
                    break;
                }
            }
        }
        
        //Switch statement finds the appropriate unit and its location on the grid.
        int x = 0, y = 1; //Coordinate axes
        int gridX = 0, gridY = 0;
        switch (unitSelected) {
            case 0:
                gridX = ConquestMap.intUnit1_1Coords[x];
                gridY = ConquestMap.intUnit1_1Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
            case 1:
                gridX = ConquestMap.intUnit1_2Coords[x];
                gridY = ConquestMap.intUnit1_2Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
            case 2:
                gridX = ConquestMap.intUnit1_3Coords[x];
                gridY = ConquestMap.intUnit1_3Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
            case 3:
                gridX = ConquestMap.intUnit2_1Coords[x];
                gridY = ConquestMap.intUnit2_1Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
            case 4:
                gridX = ConquestMap.intUnit2_2Coords[x];
                gridY = ConquestMap.intUnit2_2Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
            case 5:
                gridX = ConquestMap.intUnit2_3Coords[x];
                gridY = ConquestMap.intUnit2_3Coords[y];
                switch (gridX) {
                    case 0:
                        btnMoveLeft.setEnabled(false);
                        break;
                    case 17:
                        btnMoveRight.setEnabled(false);
                        break;
                }
                switch (gridY) {
                    case 0:
                        btnMoveUp.setEnabled(false);
                        break;
                    case 10:
                        btnMoveDown.setEnabled(false);
                        break;
                }
                break;
        }
        boolean[] booleanButtonGreyOut = new boolean[4];
        System.out.println("Unit Selected: " + unitSelected);
        booleanButtonGreyOut = ConquestMap.checkGridOccupied(unitSelected);
        if (booleanButtonGreyOut[0]) {
            btnMoveUp.setEnabled(false);
        }
        if (booleanButtonGreyOut[1]) {
            btnMoveRight.setEnabled(false);
        }
        if (booleanButtonGreyOut[2]) {
            btnMoveDown.setEnabled(false);
        }
        if (booleanButtonGreyOut[3]) {
            btnMoveLeft.setEnabled(false);
        }
    }
    //Player management methods
    private void selectUnit(int unitNumber) {
        //Select a unit by setting the appropriate array index to true and all others false.
        if (booleanIsPlayer1Turn) {
            for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
                //Deselect all units before selecting the unit specified
                booleanPlayer1UnitSelected[i] = false;
            }
            booleanPlayer1UnitSelected[unitNumber] = true;
            checkPossibleMoveDirections();
            ConquestCombat.checkAttackPossibility(unitNumber);
            booleanUnitSelected = true;
        }
        else {
            for (int i = 0; i < booleanPlayer2UnitSelected.length; i++) {
                booleanPlayer2UnitSelected[i] = false;
            }
            booleanPlayer2UnitSelected[unitNumber] = true;
            checkPossibleMoveDirections();
            booleanUnitSelected = true;
        }
        lblCurrentSelectedUnit.setText("Current selected unit: " + Integer.toString(unitNumber + 1));
    }
    private void changeTurn() {
        //Switch turn
        if (booleanIsPlayer1Turn) {
            booleanIsPlayer1Turn = false;
            stringPlayerTurn = stringPlayer2;
        }
        else {
            booleanIsPlayer1Turn = true;
            stringPlayerTurn = stringPlayer1;
        }
        lblWhoseTurn.setText("Turn: " + stringPlayerTurn);
        
        //Reset selected units
        for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
            booleanPlayer1UnitSelected[i] = false;
        }
        for (int i = 0; i < booleanPlayer2UnitSelected.length; i++) {
            booleanPlayer2UnitSelected[i] = false;
        }
        booleanUnitSelected = false;
        intNumMoves = 0;
        setNumTurnsLabel(intNumMoves);
        setLblUnitPower();
        lblCurrentSelectedUnit.setText("Current selected unit: None");
        toggleMoveCountButtonState(false);
        btnRollDice.setEnabled(true);
    }
    private void rollDice() { //Generate a random integer between 1-6
        intNumMoves = (RandomIntGenerator(1, 6));
        intNumMoves = 9001;
        setNumTurnsLabel(intNumMoves);
        
        //Re-enable the buttons
        toggleMoveCountButtonState(true);
        
        //Disable the Roll Dice button after use.
        btnRollDice.setEnabled(false);
        
        //Check possible move directions if unit is already selected.
        checkPossibleMoveDirections();
    }
    
    //General purpose methods
    private static void log(String message) { //Log method - not necessary, but makes typing out code for printing to console easier.
        System.out.println(message);
    }
    private int RandomIntGenerator(int minvalue, int maxvalue) { //Generates a random integer between a set minimum and maximum value
        int number = (minvalue + (int)(Math.random() * ((maxvalue - minvalue) + 1)));
        return number;
    }
    
    //ActionEvent Responders - event driven methods for responding to button presses
    private void btnMoveUpPressed(ActionEvent evt) {
        prepareMove(1);
    }
    private void btnMoveDownPressed(ActionEvent evt) {
        prepareMove(3);
    }
    private void btnMoveLeftPressed(ActionEvent evt) {
        prepareMove(4);
    }
    private void btnMoveRightPressed(ActionEvent evt) {
        prepareMove(2);
    }
    private void btnSelectUnit1Pressed(ActionEvent evt) {
        selectUnit(0);
    }
    private void btnSelectUnit2Pressed(ActionEvent evt) {
        selectUnit(1);
    }
    private void btnSelectUnit3Pressed(ActionEvent evt) {
        selectUnit(2);
    }
    private void btnUpgradeUnit1Pressed(ActionEvent evt) {
        upgradeUnit(1, 1);
    }
    private void btnUpgradeUnit2Pressed(ActionEvent evt) {
        upgradeUnit(2, 1);
    }
    private void btnUpgradeUnit3Pressed(ActionEvent evt) {
        upgradeUnit(3, 1);
    }
    private void btnEndTurnPressed(ActionEvent evt) {
        changeTurn();
    }
    private void btnRollDicePressed(ActionEvent evt) {
        rollDice();
    }
    private void btnAttackPressed(ActionEvent evt) {
        
    }
    private void btnCapturePressed(ActionEvent evt) {
        
    }
    
    //Auto-generated code below
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}