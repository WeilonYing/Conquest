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

import javax.swing.JOptionPane;

/**
 *
 * @author Weilon
 */
public class ConquestCombat {
    
    private static boolean[] booleanAttackPossibility = new boolean[4];
    
    //Unit scanning methods.
    public static boolean[] checkAttackPossibility() {
        int unitSelected = findSelectedUnit();

        booleanAttackPossibility = checkPlayerUnits(unitSelected);
        return booleanAttackPossibility;
    }
    private static boolean[] checkPlayerUnits (int unitSelected) {
        int[] intUnitCoords = new int[2];

        intUnitCoords = getUnitCoords(unitSelected);
        booleanAttackPossibility = findOccupiedAdjacentEnemies(intUnitCoords);
        return booleanAttackPossibility;
    }

    private static int[] getUnitCoords(int unitSelected) {
        int x = 0, y = 1; //Coordinate axes
        int[] intUnitCoords = new int[2];
        switch (unitSelected) {
            case 0:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit1_1Coords[i];
                }
                break;
            case 1:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit1_2Coords[i];
                }
                break;
            case 2:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit1_3Coords[i];
                }
                break;
            case 3:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit2_1Coords[i];
                }
                break;
            case 4:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit2_2Coords[i];
                }
                break;
            case 5:
                for (int i = 0; i < intUnitCoords.length; i++) {
                    intUnitCoords[i] = ConquestMap.intUnit2_3Coords[i];
                }
                break;
                //Add more cases for additional units
                
        }
        return intUnitCoords;
    }
    private static boolean[] findOccupiedAdjacentEnemies(int[] intUnitCoords) {
        boolean[] booleanAdjacentEnemy = new boolean[4];
        int x = 0, y = 1; //Coordinate axes
        for (int i = 0; i < booleanAdjacentEnemy.length; i++) { //Setting all variables to false initially.
            booleanAdjacentEnemy[i] = false;
        }
        
        if (intUnitCoords[y] > 0) {
            if (ConquestMap.booleanGridOccupied[intUnitCoords[x]][intUnitCoords[y] - 1]) { //up
                booleanAdjacentEnemy[0] = findEnemyUnits(intUnitCoords[x], intUnitCoords[y] - 1);
            }
        }
        if (intUnitCoords[x] < 17) {
            if (ConquestMap.booleanGridOccupied[intUnitCoords[x] + 1][intUnitCoords[y]]) { //right
                booleanAdjacentEnemy[1] = findEnemyUnits(intUnitCoords[x] + 1, intUnitCoords[y]);
            }
        }
        if (intUnitCoords[y] < 10) {
            if (ConquestMap.booleanGridOccupied[intUnitCoords[x]][intUnitCoords[y] + 1]) { //down
                booleanAdjacentEnemy[2] = findEnemyUnits(intUnitCoords[x], intUnitCoords[y] + 1);
            }
        }
        if (intUnitCoords[x] > 0) {
            if (ConquestMap.booleanGridOccupied[intUnitCoords[x] - 1][intUnitCoords[y]]) { //left
                booleanAdjacentEnemy[3] = findEnemyUnits(intUnitCoords[x] - 1, intUnitCoords[y]);
            }
        }
        return booleanAdjacentEnemy;
    }
    private static boolean findEnemyUnits(int gridX, int gridY) { //Check if there is an enemy unit in the specified location.
        boolean booleanEnemyFound = false;
        int x = 0, y = 1; //Coordinate axes
        if (Conquest.booleanIsPlayer1Turn) {
            if (ConquestMap.intUnit2_1Coords[x] == gridX && ConquestMap.intUnit2_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
            if (ConquestMap.intUnit2_2Coords[x] == gridX && ConquestMap.intUnit2_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
            if (ConquestMap.intUnit2_3Coords[x] == gridX && ConquestMap.intUnit2_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
        }
        else {
            if (ConquestMap.intUnit1_1Coords[x] == gridX && ConquestMap.intUnit1_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
            if (ConquestMap.intUnit1_2Coords[x] == gridX && ConquestMap.intUnit1_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
            if (ConquestMap.intUnit1_3Coords[x] == gridX && ConquestMap.intUnit1_1Coords[y] == gridY) {
                booleanEnemyFound = true;
            }
        }
        return booleanEnemyFound;
    }
    private static int findSpecificEnemyUnit(int[] unitCoords, int attackDirection) {
        int x = 0, y = 1; //Coordinate axes
        
        //Find coordinates to check on.
        switch (attackDirection) {
            case 0:
                if (unitCoords[y] > 0) {
                    unitCoords[y] -= 1;
                }
                break;
            case 1:
                if (unitCoords[x] < 17) {
                    unitCoords[y] += 1;
                }
                break;
            case 2:
                if (unitCoords[y] < 10) {
                    unitCoords[y] += 1;
                }
                break;
            case 3:
                if (unitCoords[x] > 0) {
                    unitCoords[x] -= 1;
                }
                break;
            default:
                log("Invalid move direction at findSpecificEnemyUnit");
                return 9999; //Since invalid move direction called. Returning a sentinel value.
        }
        
        if (Conquest.booleanIsPlayer1Turn) {
            if (ConquestMap.intUnit2_1Coords[x] == unitCoords[x] && ConquestMap.intUnit2_1Coords[y] == unitCoords[y]) {
                return 3;
            }
            if (ConquestMap.intUnit2_2Coords[x] == unitCoords[x] && ConquestMap.intUnit2_2Coords[y] == unitCoords[y]) {
                return 4;
            }
            if (ConquestMap.intUnit2_3Coords[x] == unitCoords[x] && ConquestMap.intUnit2_3Coords[y] == unitCoords[y]) {
                return 5;
            }
        }
        else if (!Conquest.booleanIsPlayer1Turn) {
            if (ConquestMap.intUnit1_1Coords[x] == unitCoords[x] && ConquestMap.intUnit1_1Coords[y] == unitCoords[y]) {
                return 0;
            }
            if (ConquestMap.intUnit1_2Coords[x] == unitCoords[x] && ConquestMap.intUnit1_2Coords[y] == unitCoords[y]) {
                return 1;
            }
            if (ConquestMap.intUnit1_3Coords[x] == unitCoords[x] && ConquestMap.intUnit1_3Coords[y] == unitCoords[y]) {
                return 2;
            }
        }
        else {
            return 9999; //No units found, return sentinel value.
        }
        return 9999; //If no results, return sentinel value.
    }
    private static int findSelectedUnit() {
        int unitSelected = 9999; //If no unit is selected, a sentinel value will be returned.
        if (Conquest.booleanIsPlayer1Turn) {
            for (int i = 0; i < Conquest.booleanPlayer1UnitSelected.length; i++) {
                if (Conquest.booleanPlayer1UnitSelected[i]) {
                    unitSelected = i;
                }
            }
        }
        else {
            for (int i = 0; i < Conquest.booleanPlayer2UnitSelected.length; i++) {
                if (Conquest.booleanPlayer2UnitSelected[i]) {
                    unitSelected = i;
                }
            }
        }
        return unitSelected;
    }
    
    //Unit attack methods.
    public static void attackUnit(int attackDirection) {
        int[] unitCoords = new int[2]; //Unit coordinates
        int enemyUnit, attackingUnit;
        int attackingUnitPower = 0, enemyUnitPower = 0;
        boolean[] booleanAdjacentEnemies = new boolean[4];
        
        attackingUnit = findSelectedUnit(); //Get the current selected unit.
        
        unitCoords = getUnitCoords(attackingUnit); //Get unit coordinates.
        booleanAdjacentEnemies = findOccupiedAdjacentEnemies(unitCoords);
        
        if (booleanAdjacentEnemies[attackDirection]) { //Checks for adjacent enemies
            
            enemyUnit = findSpecificEnemyUnit(unitCoords, attackDirection); //Finds a specific enemy to target.
            
            //Get power levels
            attackingUnitPower = getPowerLevel(attackingUnit);
            enemyUnitPower = getPowerLevel(enemyUnit);
            if (enemyUnit <= 5 && attackingUnit <= 5) {
                if (attackingUnitPower > enemyUnitPower) {
                    killUnit(enemyUnit);
                    setUnitPowerLevel(attackingUnit, attackingUnitPower - enemyUnitPower);
                }
                else if (attackingUnitPower < enemyUnitPower) {
                    killUnit(attackingUnit);
                    setUnitPowerLevel(enemyUnit, enemyUnitPower - attackingUnitPower);
                }
                else {
                    killUnit(attackingUnit);
                    killUnit(enemyUnit);
                }
            }
            else {
                msgBox("Invalid unit", "error", "error");
            }
        }
        else {
            log("No adjacent enemies found (attackUnit)");
        }
    }
    private static int getPowerLevel(int unitNumber) {
        int powerLevel = 0;
        switch (unitNumber) {
            case 0:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            case 1:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            case 2:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            case 3:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            case 4:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            case 5:
                powerLevel = Conquest.intUnit1_1Power;
                break;
            default:
                log("Invalid unit number");
                powerLevel = 0;
                break;
        }
        return powerLevel;
    }
    private static void killUnit(int unitNumber) {
        Conquest.booleanUnitKilled[unitNumber] = true;
        log("Unit " + Integer.toString(unitNumber) + " has been killed.");
    }
    private static void setUnitPowerLevel(int unitNumber, int newPowerLevel) {
        
    }
    //General purpose methods.
    private static void log(String message) {
        System.out.println(message);
    }
    private static void msgBox(String message, String title, String messageType) { //Method to make sending of message dialog boxes to the user easier to do.
        if (messageType == null) { //If programmer is uninterested in looking up dialog box types, just use null.
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        }
        else {
            messageType = messageType.toLowerCase(); //Ensures strings with capitalised letters do not get misinterpreted.
            switch (messageType) { //Use of simple keywords in order to make dialog box creation easier and less tedious.
                case "info": //Information message dialog box
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "warning": //Warning message dialog box
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                    break;
                case "error": //Error message dialog box
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
                    break;
                case "plain": //Plain message dialog box
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                    break;
                default: //If messageType is not any of the above, default to plain message.
                    log("messageType string value: " + messageType + " could not be recognised. Defaulting to plain message."); //Inform programmer of this.
                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                    break;
            }
        }
    }
}
