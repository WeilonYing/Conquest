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

import static io.github.whalesaredelicious.conquest.ConquestMap.intCapturePointStatus;
import static java.awt.Color.blue;
import static java.awt.Color.lightGray;
import static java.awt.Color.red;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.out;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

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
    
    public static int intUnit1_1Power = 1;
    public static int intUnit1_2Power = 1;
    public static int intUnit1_3Power = 1;
    public static int intUnit2_1Power = 1;
    public static int intUnit2_2Power = 1;
    public static int intUnit2_3Power = 1;
    
    private int intInitialPlayer1Tickets = 50, intInitialPlayer2Tickets = 50;
    private int intPlayer1Tickets = intInitialPlayer1Tickets, intPlayer2Tickets = intInitialPlayer2Tickets;
    
    private boolean booleanDiceRolled = false;
    public static boolean[] booleanUnitKilled = new boolean[6];
    
    public static boolean booleanIsPlayer1Turn = true;
    public static boolean[] booleanPlayer1UnitSelected = new boolean[3];
    public static boolean[] booleanPlayer2UnitSelected = new boolean[3];
    private boolean booleanUnitSelected = false; //Used to prevent decrementing a move when unit is not selected.
    private boolean booleanAttackMode = false; //When true, attack mode is activated.
    
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
    
    private final JLabel lblWhoseTurn = new JLabel("<html><b>Turn: </b>" + stringPlayerTurn + "</html>");
    private final JLabel lblCurrentSelectedUnit = new JLabel("<html><b>Current selected unit: </b>None</html>");
    private final JLabel lblNumMoves = new JLabel("<html><b>No. of moves left: </b>" + Integer.toString(intNumMoves) + "</html>");
    private final JLabel lblPlayer1 = new JLabel(stringPlayer1, JLabel.CENTER);
    private final JLabel lblPlayer2 = new JLabel(stringPlayer2, JLabel.CENTER);
    private final JLabel lblVs = new JLabel("vs.", JLabel.CENTER);
    
    private final JLabel lblTicketsLeftPlayer1 = new JLabel("<html><b>Tickets left</b></html>", JLabel.CENTER);
    private final JLabel lblTicketsLeftPlayer2 = new JLabel("<html><b>Tickets left</b></html>", JLabel.CENTER);
    
    private final JLabel lblCapturePointsTitle = new JLabel("Capture Points");
    private final JLabel[] lblCapturePoint = new JLabel[4];
    
    //Declare borders (for Capture Point labels)
    private Border borderGrey = BorderFactory.createBevelBorder(1, lightGray, lightGray, lightGray, lightGray); //No man's land
    private Border borderRed = BorderFactory.createBevelBorder(1, red, red, red, red); //Player 1's land
    private Border borderBlue = BorderFactory.createBevelBorder(1, blue, blue, blue, blue); //Player 2's land
    
    //Declare progress bars (for ticket count)
    private JProgressBar progressbarPlayer1 = new JProgressBar(0, 100);
    private JProgressBar progressbarPlayer2 = new JProgressBar(0, 100);
    private JProgressBar[] progressbarPlayerTickets = new JProgressBar[2];
    
    static ConquestMap conquestmap = new ConquestMap();
    //Constructor method
    public Conquest() {
        super("Conquest");
        
        initComponents();
        initControls();
        initLabels();
        initProgressBars();
        initUnitsKilled();
        
        setScreenParameters();
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
        ConquestStart start = new ConquestStart(null, true);
        start.setVisible(true);

        new Conquest().setVisible(true);

        conquestmap.setVisible(true);
        conquestmap.initLocations();
    }
    
    //Initialisation methods - setting up GUI elements
    private void setScreenParameters() {
        setLayout(null);
        setResizable(false);
        setSize(600, 400); //Screen size
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLblUnitPower();
    }
    private void initControls() {
        /*
            Higher-level method that oversees the initialisation,
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
        //add(lblTicketsLeftPlayer1);
        //add(lblTicketsLeftPlayer2);
        
        add(lblCapturePointsTitle);
        for (int i = 0; i < lblCapturePoint.length; i++) { add(lblCapturePoint[i]); }
    }
    private void initUnitsKilled() {
        /**
         * Set units killed status to false.
         */
        for (int i = 0; i < booleanUnitKilled.length; i++) {
            booleanUnitKilled[i] = false;
        }
    }
    private void initProgressBars() {
        int boundaryX = this.getSize().width, boundaryY = this.getSize().height;
        int p1 = 0, p2 = 1; //Player numbers
        progressbarPlayer1.setBounds(boundaryX - 550, boundaryY - 295, 120, 30);
        progressbarPlayer1.setValue(100);
        progressbarPlayer1.setForeground(red);
        progressbarPlayer1.setStringPainted(true);
        progressbarPlayer1.setString("Tickets left: 50");
        
        progressbarPlayer2.setBounds(boundaryX - 220, boundaryY - 295, 120, 20);
        
        for (int player = 0; player < progressbarPlayerTickets.length; player++) {
            progressbarPlayerTickets[player] = new JProgressBar(0, 100);
            progressbarPlayerTickets[player].setStringPainted(true);
            progressbarPlayerTickets[player].setValue(100);
            add(progressbarPlayerTickets[player]);
        }
        progressbarPlayerTickets[p1].setBounds(boundaryX - 550, boundaryY - 295, 120, 30);
        progressbarPlayerTickets[p1].setForeground(red);
        progressbarPlayerTickets[p1].setString("Tickets left: " + Integer.toString(intPlayer1Tickets));
        
        progressbarPlayerTickets[p2].setBounds(boundaryX - 220, boundaryY - 295, 120, 30);
        progressbarPlayerTickets[p2].setForeground(blue);
        progressbarPlayerTickets[p2].setString("Tickets left: " + Integer.toString(intPlayer2Tickets));
        
        //add(progressbarPlayer1);
        //add(progressbarPlayer2);
        /**
         *  private JProgressBar progressbarPlayer1 = new JProgressBar(0, 100);
            private JProgressBar progressbarPlayer2 = new JProgressBar(0, 100);
            private JProgressBar[] progressbarPlayerTickets = new JProgressBar[2];
         */
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
        toggleMoveCountButtonState(false, false);
        //Disable capture button
        btnCapture.setEnabled(false);

    }
    private void setLabelParameters() {//Sets the size and location of the labels.
        //label.setBounds(LocationX, LocationY, SizeX, SizeY
        int boundaryX = this.getSize().width, boundaryY = this.getSize().height;
        
        lblUnit1Power.setBounds(boundaryX - 575, boundaryY - 205, 30, 10);
        lblUnit2Power.setBounds(boundaryX - 575, boundaryY - 155, 30, 10);
        lblUnit3Power.setBounds(boundaryX - 575, boundaryY - 105, 30, 10);
        
        lblWhoseTurn.setBounds(boundaryX - 575, boundaryY - 70, 200, 40);
        lblNumMoves.setBounds(boundaryX - 575, boundaryY - 40, 200, 40);
        lblCurrentSelectedUnit.setBounds((boundaryX - 575), (boundaryY - 10), 200, 40);
        
        lblPlayer1.setBounds(boundaryX - 580, boundaryY - 330, 180, 30);
        lblPlayer2.setBounds(boundaryX - 250, boundaryY - 330, 180, 30);
        lblVs.setBounds(boundaryX - 340, boundaryY - 320, 30, 30);
        lblTicketsLeftPlayer1.setBounds(boundaryX - 570, boundaryY - 275, 150, 30);
        lblTicketsLeftPlayer1.setText("<html><b>Tickets left: </b>" + Integer.toString(intPlayer1Tickets) + "</html>");
        lblTicketsLeftPlayer2.setBounds(boundaryX - 240, boundaryY - 275, 150, 30);
        lblTicketsLeftPlayer2.setText("<html><b>Tickets left: </b>" + Integer.toString(intPlayer2Tickets) + "</html>");
        
        lblCapturePointsTitle.setBounds(boundaryX - 360, boundaryY - 260, 200, 30);
        
        //Initialise the CapturePoint label array.
        for (int i = 0; i < lblCapturePoint.length; i++) { lblCapturePoint[i] = new JLabel("", JLabel.CENTER); }
        
        lblCapturePoint[0].setText("A");
        lblCapturePoint[1].setText("B");
        lblCapturePoint[2].setText("C");
        lblCapturePoint[3].setText("D");
        
        lblCapturePoint[0].setBounds(boundaryX - 320, boundaryY - 210, 30, 30);
        lblCapturePoint[1].setBounds(boundaryX - 320, boundaryY - 175, 30, 30);
        lblCapturePoint[2].setBounds(boundaryX - 320, boundaryY - 140, 30, 30);
        lblCapturePoint[3].setBounds(boundaryX - 320, boundaryY - 105, 30, 30);
        
        //Sets the font parameters of the labels. By default it is serif, plain and font size 10
        Font fontStandard = new Font("sans-serif", Font.PLAIN, 16);
        Font fontBold = new Font("sans-serif", Font.BOLD, 16);
        Font fontSmall = new Font("sans-serif", Font.PLAIN, 14);
        Font fontSmallBold = new Font("sans-serif", Font.BOLD, 14);
        lblWhoseTurn.setFont(fontSmall);
        lblNumMoves.setFont(fontSmall);
        lblCurrentSelectedUnit.setFont(fontSmall);
        
        lblPlayer1.setFont(fontStandard);
        lblPlayer2.setFont(fontStandard);
        lblVs.setFont(fontStandard);
        lblTicketsLeftPlayer1.setFont(fontSmall);
        lblTicketsLeftPlayer2.setFont(fontSmall);
        
        lblCapturePointsTitle.setFont(fontSmallBold);
        
        for (int i = 0; i < lblCapturePoint.length; i++) {
            lblCapturePoint[i].setFont(fontStandard);
            lblCapturePoint[i].setHorizontalTextPosition(JLabel.CENTER);
        }
        //Set label borders
        for (int i = 0; i < lblCapturePoint.length; i++) {
            lblCapturePoint[i].setBorder(borderGrey);
        }
        lblPlayer1.setBorder(borderRed);
        lblPlayer2.setBorder(borderBlue);
    }
    
    //Unit management methods
    private void checkPossibleCapture() {
        int unitSelected = getSelectedUnit();
        btnCapture.setEnabled(ConquestMap.findCapturePoint(unitSelected));
    }
    public static int getSelectedUnit() {
        //Find the current selected unit.
        if (booleanIsPlayer1Turn) {
            for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
                if (Conquest.booleanPlayer1UnitSelected[i]) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < booleanPlayer2UnitSelected.length; i++) {
                if (booleanPlayer2UnitSelected[i]) {
                    return i + 3;
                }
            }
        }
        return 999; //Sentinel value
    }
    private boolean processMove(int amount) { //Checks if player has enough points to execute an action and subtracts the appropriate amount.
        if ((intNumMoves - amount) >= 0) { //Checks if amount to remove will still be greater or equal to 0
            intNumMoves -= amount;
            setNumTurnsLabel(intNumMoves);
            toggleMoveCountButtonState(true, true);
            if (intNumMoves == 0) {
                toggleMoveCountButtonState(false, false);
            }
            return true;
        }
        else {
            intNumMoves = 0; //Setting it to 0 in case intNumMoves is somehow < 0
            setNumTurnsLabel(intNumMoves);
            toggleMoveCountButtonState(false, false);
            return false;
        }
    }
    private void prepareMove(int moveDirection) { //Ensures a unit is selected before executing.
        if (booleanUnitSelected) {
            if (processMove(1)) {
                ConquestMap.move(moveDirection);
                checkPossibleMoveDirections();
                checkPossibleCapture();
            }
        }
    }
    private void upgradeUnit(int unitNumber, int upgradeAmount) { //Makes the relevant checks, then processes the unit upgrade.
        //If invalid unit selected, boolean flag will prevent a move being subtracted from the player.
        if (processMove(upgradeAmount)) { //Ensures player has enough moves to execute upgrade.
            if (booleanIsPlayer1Turn) {
                switch (unitNumber) {
                    case 1:
                        intUnit1_1Power += upgradeAmount;
                        break;
                    case 2:
                        intUnit1_2Power += upgradeAmount;
                        break;
                    case 3:
                        intUnit1_3Power += upgradeAmount;
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
                        break;
                    case 2:
                        intUnit2_2Power += upgradeAmount;
                        break;
                    case 3:
                        intUnit2_3Power += upgradeAmount;
                        break;
                    default:
                        log("Invalid unit number");
                }
            }
        }
        
        limitUnitPower();
        setLblUnitPower();
        
        checkPossibleMoveDirections();
    }
    private void limitUnitPower() {
        /**
         * If player somehow reaches a ridiculous amount of power,
         * limit power to 9001 to prevent graphical glitches in the GUI.
         */
        int maxPower = 9001;
        if (intUnit1_1Power > maxPower) {intUnit1_1Power = maxPower;}
        if (intUnit1_2Power > maxPower) {intUnit1_2Power = maxPower;}
        if (intUnit1_3Power > maxPower) {intUnit1_3Power = maxPower;}
        if (intUnit2_1Power > maxPower) {intUnit2_1Power = maxPower;}
        if (intUnit2_2Power > maxPower) {intUnit2_2Power = maxPower;}
        if (intUnit2_3Power > maxPower) {intUnit2_3Power = maxPower;}
    }
    private void attackMode() {
        //Standard procedure to scan for enemy units. Called everytime something is changed and if Attack Mode is enabled.
        int unitSelected = getSelectedUnit(); //If getSelectedUnit() returns 999, no unit selected.
        if (unitSelected  != 999) {
            boolean[] booleanAttackPossibility = new boolean[4];
        
            booleanAttackPossibility = ConquestCombat.checkAttackPossibility(unitSelected); //check possible places to attack
            setAttackDirections(booleanAttackPossibility); //sets possible attack directions
            setBorders(unitSelected); //ensures unit cannot leave the battlefield
        }
        else {
            msgBox("No unit selected.", "Select a unit", "plain");
            disableAttackMode();
        }
        checkUnitsKilled();
    }
    private void attackUnit(int direction) { //Attack a unit if attack mode is enabled.
        int unitSelected = getSelectedUnit(); //If getSelectedUnit returns 999, no unit selected.
        int[] unitSelectedCoords = ConquestCombat.getUnitCoords(unitSelected);
        if (!booleanUnitSelected || unitSelected == 999) { //If booleanUnitSelected = false or unitSelected = 999
            msgBox("No unit selected.", "Select a unit", "plain");
        }
        else {
            int x = 0, y = 1; //Coordinate axes
            
            //Find the defending unit.
            int defendingUnit = 999; //Sentinel value if no defending unit found.
            switch (direction) { //Find the unit depending on direction chosen.
                case 1: //up
                    defendingUnit = ConquestCombat.findSpecificEnemyUnit(unitSelectedCoords[x], unitSelectedCoords[y] - 1);
                    break;
                case 2: //right
                    defendingUnit = ConquestCombat.findSpecificEnemyUnit(unitSelectedCoords[x] + 1, unitSelectedCoords[y]);
                    break;
                case 3: //down
                    defendingUnit = ConquestCombat.findSpecificEnemyUnit(unitSelectedCoords[x], unitSelectedCoords[y] + 1);
                    break;
                case 4: //left
                    defendingUnit = ConquestCombat.findSpecificEnemyUnit(unitSelectedCoords[x] - 1, unitSelectedCoords[y]);
                    break;
                default:
                    log("Invalid direction sent (attackUnit)");
                    break;
            }
            
            if (defendingUnit == 999) { //If sentinel value is returned, then cancel attack.
                msgBox("No enemy found in the chosen direction", "No enemy found", "plain");
            }
            else {
                unitBattle(unitSelected, defendingUnit, direction);
            }
        }
        disableAttackMode(); //Disable attack mode after battle is completed.
    }
    private void unitBattle(int attackingUnit, int defendingUnit, int direction) {
        //Calculates unit power levels and kills the losing units.
        int attackingUnitPower = getUnitPower(attackingUnit);
        int defendingUnitPower = getUnitPower(defendingUnit);
        
        if (attackingUnitPower > defendingUnitPower) {
            //kill defending unit
            killUnit(defendingUnit);
            log("Kill defending unit: " + Integer.toString(defendingUnit));
        }
        else if (attackingUnitPower < defendingUnitPower) {
            //kill attacking unit
            killUnit(attackingUnit);
            booleanUnitSelected = false; //Unit can no longer be selected since it is killed.
            log("Kill attacking unit: " + Integer.toString(attackingUnit));
        }
        else {
            //kill both units
            killUnit(defendingUnit);
            killUnit(attackingUnit);
            booleanUnitSelected = false; //Unit can no longer be selected since it is killed.
            out.println("Kill both units: " + attackingUnit + ", " + defendingUnit);
        }
        checkUnitsKilled();
    }
    private void checkUnitsKilled() { //Checks which units are killed and disables the appropriate buttons.
        for (int i = 0; i < booleanUnitKilled.length; i++) {
            if (booleanUnitKilled[i]) {
                disableUnitButtons(i);
                ConquestMap.disableUnit(i);
            }
            else {
                enableUnitButtons(i);
            }
        }
    }
    private void killUnit(int unitNumber) {
        booleanUnitKilled[unitNumber] = true;
        ConquestMap.disableUnitGridOccupied(unitNumber);
        log("Unit " + Integer.toString(unitNumber) + " has been killed.");
    }
    private int getUnitPower(int unitNumber) {
        //Finds the power level of a unit and returns the value as an integer.
        int power = 0;
        switch (unitNumber) {
            case 0:
                power = intUnit1_1Power;
                break;
            case 1:
                power = intUnit1_2Power;
                break;
            case 2:
                power = intUnit1_3Power;
                break;
            case 3:
                power = intUnit2_1Power;
                break;
            case 4:
                power = intUnit2_2Power;
                break;
            case 5:
                power = intUnit2_3Power;
                break;
        }
        return power;
    }
    private void disableAttackMode() {
        //Standard procedure to reset some parameters. Call this if you wish to stop Attack Mode. The method takes care of the rest.
        if (booleanUnitSelected) {
            checkPossibleMoveDirections();
        }
        else {
            toggleMoveCountButtonState(false, false);
        }
        booleanAttackMode = false;
        log("Attack mode disabled");
        checkUnitsKilled();
    }
    
    //Capture Point Management
    private void processCapture() {
        int unitSelected = getSelectedUnit();
        String stringCapturePointID = ConquestCombat.getCapturePointID(unitSelected);
        changeCapturePoint(stringCapturePointID);
    }
    private void changeCapturePoint(String capturePointID) {
        //Changes capture point ownership status to the desired team.
        int captureAlignment = 0;
        if (booleanIsPlayer1Turn) { //If player 1, captureAlignment = 1, else captureAlignment = 2
            captureAlignment = 1; //Red team
        }
        else {
            captureAlignment = 2; //Blue team
        }

        int A = 0, B = 1, C = 2, D = 3; //Capture point ID string to number conversion
        switch (capturePointID) {
            case "A":
                intCapturePointStatus[A] = captureAlignment;
                lblCapturePoint[A].setBorder(getCapturePointBorder(captureAlignment));
                break;
            case "B":
                intCapturePointStatus[B] = captureAlignment;
                lblCapturePoint[B].setBorder(getCapturePointBorder(captureAlignment));
                break;
            case "C":
                intCapturePointStatus[C] = captureAlignment;
                lblCapturePoint[C].setBorder(getCapturePointBorder(captureAlignment));
                break;
            case "D":
                intCapturePointStatus[D] = captureAlignment;
                lblCapturePoint[D].setBorder(getCapturePointBorder(captureAlignment));
                break;
        }
        //Refresh capture points (Player 1 = Red, Player 2 = Blue
    }
    private Border getCapturePointBorder(int captureAlignment) {
        //Simple method to return border information (visual decoration)
        switch (captureAlignment) {
            case 1:
                return borderRed;
            case 2:
                return borderBlue;
            default:
                return borderGrey;
        }
    }
    
    //GUI management methods
    private void disableUnitButtons(int unitNumber) {
        //Disables unit control buttons, depending on set parameter
        if (booleanIsPlayer1Turn) {
            switch (unitNumber) {
                case 0:
                    btnSelectUnit1.setEnabled(false);
                    btnUpgradeUnit1.setEnabled(false);
                    break;
                case 1:
                    btnSelectUnit2.setEnabled(false);
                    btnUpgradeUnit2.setEnabled(false);
                    break;
                case 2:
                    btnSelectUnit3.setEnabled(false);
                    btnUpgradeUnit3.setEnabled(false);
                    break;
                default:
                    log("Invalid unit parameter. (disableUnitButtons)");
                    break;
            }
        }
        else {
            switch (unitNumber) {
                case 3:
                    btnSelectUnit1.setEnabled(false);
                    btnUpgradeUnit1.setEnabled(false);
                    break;
                case 4:
                    btnSelectUnit2.setEnabled(false);
                    btnUpgradeUnit2.setEnabled(false);
                    break;
                case 5:
                    btnSelectUnit3.setEnabled(false);
                    btnUpgradeUnit3.setEnabled(false);
                    break;
                default:
                    log("Invalid unit parameter. (disableUnitButtons)");
                    break;
            }
        }
        log("Disable unit buttons called");
    }
    private void enableUnitButtons(int unitNumber) {
        //Enables unit control buttons, depending on set parameter
        //NOTE: upgrade unit button is done through disableAttackMode(), which this method is called from.
        if (booleanIsPlayer1Turn) {
            switch (unitNumber) {
                case 0:
                    btnSelectUnit1.setEnabled(true);
                    //btnUpgradeUnit1.setEnabled(true);
                    break;
                case 1:
                    btnSelectUnit2.setEnabled(true);
                    //btnUpgradeUnit2.setEnabled(true);
                    break;
                case 2:
                    btnSelectUnit3.setEnabled(true);
                    //btnUpgradeUnit3.setEnabled(true);
                    break;
                default:
                    log("Invalid unit parameter. (disableUnitButtons)");
                    break;
            }
        }
        else {
            switch (unitNumber) {
                case 3:
                    btnSelectUnit1.setEnabled(true);
                    //btnUpgradeUnit1.setEnabled(true);
                    break;
                case 4:
                    btnSelectUnit2.setEnabled(true);
                    //btnUpgradeUnit2.setEnabled(true);
                    break;
                case 5:
                    btnSelectUnit3.setEnabled(true);
                    //btnUpgradeUnit3.setEnabled(true);
                    break;
                default:
                    log("Invalid unit parameter. (disableUnitButtons)");
                    break;
            }
        }
        log("Enable unit buttons called");
    }
    private void setAttackDirections(boolean booleanAttackPossibility[]) {
        boolean booleanPossibleAttack = false;
        for (int i = 0; i < booleanAttackPossibility.length; i++) {
            if (booleanAttackPossibility[i]) {
                booleanPossibleAttack = true;
                break;
            }
        }
        if (booleanPossibleAttack) {
            toggleMoveCountButtonState(false, false);
            btnMoveUp.setEnabled(booleanAttackPossibility[0]);
            btnMoveRight.setEnabled(booleanAttackPossibility[1]);
            btnMoveDown.setEnabled(booleanAttackPossibility[2]);
            btnMoveLeft.setEnabled(booleanAttackPossibility[3]);
        }
        else {
            disableAttackMode();
            msgBox("There are no enemies near your selected unit.", "No enemy units found", "plain");
        }
    }
    private void setNumTurnsLabel (int num) {
        lblNumMoves.setText("<html><b>No. of moves left: </b>" + Integer.toString(num) + "</html>");
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
    private void toggleMoveCountButtonState(boolean moveState, boolean upgradeState) {
        //Greys out (disables) or re-enables the move and upgrade buttons depending on its set parameters.
        btnMoveUp.setEnabled(moveState);
        btnMoveDown.setEnabled(moveState);
        btnMoveLeft.setEnabled(moveState);
        btnMoveRight.setEnabled(moveState);
        
        btnUpgradeUnit1.setEnabled(upgradeState);
        btnUpgradeUnit2.setEnabled(upgradeState);
        btnUpgradeUnit3.setEnabled(upgradeState);
        log("Move count buttons toggled");
    }
    private void checkPossibleMoveDirections() {
        //Checks which squares around the current selected unit is vacant, and enables the corresponding move buttons.
        
        //1 = up, 2 = right, 3 = down, 4 = left
        //Ensures buttons aren't set enabled if user doesn't have any moves left anyway.
        if (intNumMoves > 0) {
            toggleMoveCountButtonState(true, true);
        }
        else {
            toggleMoveCountButtonState(false, false);
        }
        //Initialise unitSelected variable.
        int unitSelected = getSelectedUnit(); //NOTE: If no unit selected, returns 999
        
        if (unitSelected != 999) {
            setBorders(unitSelected);
            boolean[] booleanButtonGreyOut = new boolean[4];
            out.println("Unit Selected: " + unitSelected);
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
    }
    public void setBorders(int unitSelected) {
        //Ensures units are unable to leave the game map.
        
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
            default:
                break;
        }
    }
    //Player management methods
    private void selectUnit(int unitNumber) {
        //Processes unit selection. 
        
        //Select a unit by setting the appropriate array index to true and all others false.
        if (booleanIsPlayer1Turn) {
            for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
                //Deselect all units before selecting the unit specified
                booleanPlayer1UnitSelected[i] = false;
            }
            booleanPlayer1UnitSelected[unitNumber] = true;
            checkPossibleMoveDirections();
            
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
        lblCurrentSelectedUnit.setText("<html><b>Current selected unit: </b>" + Integer.toString(unitNumber + 1) + "</html>");
        checkUnitsKilled(); //Check which units are killed so that they cannot be selected/upgraded.
    }
    private void changeTurn() {
        //Processes switching the turn from a player to the other player.
        
        //Disable Attack Mode if still running so that Player 2 does not automatically enter Attack Mode.
        disableAttackMode();
        
        //Switch turn
        if (booleanIsPlayer1Turn) {
            booleanIsPlayer1Turn = false;
            stringPlayerTurn = stringPlayer2;
        }
        else {
            booleanIsPlayer1Turn = true;
            stringPlayerTurn = stringPlayer1;
        }
        lblWhoseTurn.setText("<html><b>Turn: </b>" + stringPlayerTurn + "</html>");
        
        //Reset selected units
        for (int i = 0; i < booleanPlayer1UnitSelected.length; i++) {
            booleanPlayer1UnitSelected[i] = false;
        }
        for (int i = 0; i < booleanPlayer2UnitSelected.length; i++) {
            booleanPlayer2UnitSelected[i] = false;
        }
        
        //Set unit selected to false.
        
        booleanUnitSelected = false;
        lblCurrentSelectedUnit.setText("<html><b>Current selected unit:</b> None</html>");
        
        //Reset number of moves left
        intNumMoves = 0;
        setNumTurnsLabel(intNumMoves);
        
        //Display unit powers
        setLblUnitPower();

        toggleMoveCountButtonState(false, false); //disable all buttons.
        booleanDiceRolled = false;
        btnRollDice.setEnabled(true); //enable roll dice button
        checkUnitsKilled(); //check for units that have been killed.
        
        //Subtract tickets
        ticketBleed();
    }
    private void rollDice() { //Generate a random integer between 1-6
        intNumMoves = (RandomIntGenerator(1, 6));
        intNumMoves = 100;
        setNumTurnsLabel(intNumMoves);
        
        //Re-enable the buttons
        toggleMoveCountButtonState(true, true);

        //Disable the Roll Dice button after use.
        btnRollDice.setEnabled(false);
        
        //Check possible move directions if unit is already selected.
        if (booleanUnitSelected) {
            checkPossibleMoveDirections();
        }
        else {
            toggleMoveCountButtonState(false, true);
        }
        booleanDiceRolled = true;
        
        //Check which units are killed
        checkUnitsKilled();
    }
    private void ticketBleed() {
        //Subtracts a player's ticket amount depending on how many capture points the other player controls.
        int intPlayer1Subtract = 0, intPlayer2Subtract = 0; //Set subtract value to zero before running.
        for (int i = 0; i < intCapturePointStatus.length; i++) {
            switch (intCapturePointStatus[i]) {
                case 1: //If capture point belongs to Player 1, subtract ticket from player 2
                    intPlayer2Subtract++;
                    break;
                case 2: //If capture point belongs to Player 2, subtract ticket from player 1
                    intPlayer1Subtract++;
                    break;
                default:
                    break;
            }
        }
        intPlayer1Tickets -= intPlayer1Subtract;
        intPlayer2Tickets -= intPlayer2Subtract;
        
        updateProgressBars();
        
        //Check if a player has lost all tickets.
        if (intPlayer1Tickets <= 0 || intPlayer2Tickets <= 0) {
            if (intPlayer1Tickets > intPlayer2Tickets && intPlayer1Tickets > 0) {
                msgBox(stringPlayer1 + " wins the game!", "Game Over!", "plain");
                processWin(1);
            }
            else if (intPlayer2Tickets > intPlayer1Tickets && intPlayer2Tickets > 0) {
                msgBox(stringPlayer2 + " wins the game!", "Game Over!", "plain");
                processWin(2);
            }
            else {
                msgBox("Both players lost all their tickets at the same time! It's a draw!", "Game Over!", "plain");
                processWin(0);
            }
            //TO DO: Scoresheet
        }
    }
    private void processWin(int winner) {
        
    }
    private void updateProgressBars() { //Update the progress bars to the new values.
        int p1 = 0, p2 = 1; //Player numbers
        double p1Progress = (intPlayer1Tickets/intInitialPlayer1Tickets) * 100;
        double p2Progress = (intPlayer2Tickets/intInitialPlayer2Tickets) * 100;
        log("Player 1 Tickets left: " + Double.toString(p1Progress));
        progressbarPlayerTickets[p1].setValue((int) p1Progress);
        progressbarPlayerTickets[p1].setString("Tickets left: " + Integer.toString(intPlayer1Tickets));
        progressbarPlayerTickets[p2].setValue((int) p2Progress);
        progressbarPlayerTickets[p2].setString("Tickets left: " + Integer.toString(intPlayer2Tickets));
    }
    
    //General purpose methods
    private static void log(String message) { //Log method - not necessary, but makes typing out code for printing to console easier.
        out.println(message);
    }
    private int RandomIntGenerator(int minvalue, int maxvalue) { //Generates a random integer between a set minimum and maximum value
        int number = (minvalue + (int)(Math.random() * ((maxvalue - minvalue) + 1)));
        return number;
    }
    private void msgBox(String message, String title, String messageType) { //Method to make sending of message dialog boxes to the user easier to do.
        if (messageType == null) { //If programmer is uninterested in looking up dialog box types, just use null.
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
        }
        else {
            messageType = messageType.toLowerCase(); //Ensures strings with capitalised letters do not get misinterpreted.
            switch (messageType) { //Use of simple keywords in order to make dialog box creation easier and less tedious.
                case "info": //Information message dialog box
                    JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "warning": //Warning message dialog box
                    JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
                    break;
                case "error": //Error message dialog box
                    JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
                    break;
                case "plain": //Plain message dialog box
                    JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
                    break;
                default: //If messageType is not any of the above, default to plain message.
                    log("messageType string value: " + messageType + " could not be recognised. Defaulting to plain message."); //Inform programmer of this.
                    JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
                    break;
            }
        }
    }
    
    //ActionEvent Responders - event driven methods for responding to button presses
    private void btnMoveUpPressed(ActionEvent evt) {
        if (booleanAttackMode) {
            attackUnit(1);
        }
        else {prepareMove(1);}
    }
    private void btnMoveDownPressed(ActionEvent evt) {
        if (booleanAttackMode) {
            attackUnit(3);
        }
        else {prepareMove(3);}
    }
    private void btnMoveLeftPressed(ActionEvent evt) {
        if (booleanAttackMode) {
            attackUnit(4);
        }
        else {prepareMove(4);}
    }
    private void btnMoveRightPressed(ActionEvent evt) {
        if (booleanAttackMode) {
            attackUnit(2);
        }
        else {prepareMove(2);}
    }
    private void btnSelectUnit1Pressed(ActionEvent evt) {
        selectUnit(0);
        if (booleanAttackMode) {
            disableAttackMode();
        }
    }
    private void btnSelectUnit2Pressed(ActionEvent evt) {
        selectUnit(1);
        if (booleanAttackMode) {
            disableAttackMode();
        }
    }
    private void btnSelectUnit3Pressed(ActionEvent evt) {
        selectUnit(2);
        if (booleanAttackMode) {
            disableAttackMode();
        }
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
        //Toggle attack mode.
        if (booleanUnitSelected) {
            if (booleanDiceRolled) {
                if (booleanAttackMode) {
                    booleanAttackMode = false;
                    disableAttackMode();
                }
                else {
                    booleanAttackMode = true;
                    attackMode();
                }
            }
            else {
                msgBox("Roll the dice first.", "Dice not rolled", "plain");
            }
        }
        else {
            msgBox("Select a unit to attack with.", "Select a unit", "plain");
        }
    }
    private void btnCapturePressed(ActionEvent evt) {
        processCapture();
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

        btnPlayer1Wins = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPlayer1Wins.setText("Player 1 wins");
        btnPlayer1Wins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayer1WinsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(328, Short.MAX_VALUE)
                .addComponent(btnPlayer1Wins)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnPlayer1Wins)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayer1WinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayer1WinsActionPerformed
        btnEndTurn.setEnabled(false);
        btnRollDice.setEnabled(false);
        toggleMoveCountButtonState(false, false);
        btnSelectUnit1.setEnabled(false);
        btnSelectUnit2.setEnabled(false);
        btnSelectUnit3.setEnabled(false);
        btnAttack.setEnabled(false);
        this.setAlwaysOnTop(false);
        this.dispose();
        
        ConquestScoresheet conquestscoresheet = new ConquestScoresheet();
        conquestscoresheet.setVisible(true);
        conquestscoresheet.setWinner(1, stringPlayer1, stringPlayer2, intPlayer1Tickets, intPlayer2Tickets);
    }//GEN-LAST:event_btnPlayer1WinsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayer1Wins;
    // End of variables declaration//GEN-END:variables
}