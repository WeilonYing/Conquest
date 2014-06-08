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
 * @author Weilon Ying
 */


public class ConquestCombat {
    public static boolean[] checkAttackPossibility(int unitSelected) {
        //Holds true/false values as to whether attacking in a particular direction is possible.
        boolean[] booleanAttackPossible = new boolean[4];
        int[] intUnitSelectedCoords = new int[2];
        //Initialise all indexes in array as false.
        for (int i = 0; i < booleanAttackPossible.length; i++) {
            booleanAttackPossible[i] = false;
        }
        intUnitSelectedCoords = getUnitCoords(unitSelected);
        
        int x = 0, y = 1; //Coordinate axes
        //Find enemy units in all four directions
        //Check upwards
        try {
            booleanAttackPossible[0] = findEnemyUnits(intUnitSelectedCoords[0], intUnitSelectedCoords[1] - 1);
        }
        catch (IndexOutOfBoundsException e) { //If checking outside the battlefield, it will cause out of bounds exception.
            booleanAttackPossible[0] = false;
        }
        //Check right
        try {
            booleanAttackPossible[1] = findEnemyUnits(intUnitSelectedCoords[0] + 1, intUnitSelectedCoords[1]);
        }
        catch (IndexOutOfBoundsException e) { //If checking outside the battlefield, it will cause out of bounds exception.
            booleanAttackPossible[1] = false;
        }
        //Check down
        try {
            booleanAttackPossible[2] = findEnemyUnits(intUnitSelectedCoords[0], intUnitSelectedCoords[1] + 1);
        }
        catch (IndexOutOfBoundsException e) { //If checking outside the battlefield, it will cause out of bounds exception.
            booleanAttackPossible[2] = false;
        }
        //Check left
        try {
            booleanAttackPossible[3] = findEnemyUnits(intUnitSelectedCoords[0] - 1, intUnitSelectedCoords[1]);
        }
        catch (IndexOutOfBoundsException e) { //If checking outside the battlefield, it will cause out of bounds exception.
            booleanAttackPossible[3] = false;
        }
        
        return booleanAttackPossible;
    }
    public static int[] getUnitCoords(int unitSelected) {
        int x = 0, y = 1; //Coordinate axes
        int[] intCoords = new int[2];
        switch (unitSelected) {
            case 0:
                intCoords[x] = ConquestMap.intUnit1_1Coords[x];
                intCoords[y] = ConquestMap.intUnit1_1Coords[y];
                break;
            case 1:
                intCoords[x] = ConquestMap.intUnit1_2Coords[x];
                intCoords[y] = ConquestMap.intUnit1_2Coords[y];
                break;
            case 2:
                intCoords[x] = ConquestMap.intUnit1_3Coords[x];
                intCoords[y] = ConquestMap.intUnit1_3Coords[y];
                break;
            case 3:
                intCoords[x] = ConquestMap.intUnit2_1Coords[x];
                intCoords[y] = ConquestMap.intUnit2_1Coords[y];
                break;
            case 4:
                intCoords[x] = ConquestMap.intUnit2_2Coords[x];
                intCoords[y] = ConquestMap.intUnit2_2Coords[y];
                break;
            case 5:
                intCoords[x] = ConquestMap.intUnit2_3Coords[x];
                intCoords[y] = ConquestMap.intUnit2_3Coords[y];
                break;
        }
        return intCoords;
    }
    private static boolean findEnemyUnits(int gridX, int gridY) {
        boolean booleanEnemyFound = false;
        int x = 0, y = 1; //Coordinate axes
        try {
            if (Conquest.booleanIsPlayer1Turn) {
                if (ConquestMap.intUnit2_1Coords[x] == gridX && 
                        ConquestMap.intUnit2_1Coords[y] == gridY && !Conquest.booleanUnitKilled[3]) {
                    booleanEnemyFound = true;
                }
                if (ConquestMap.intUnit2_2Coords[x] == gridX && 
                        ConquestMap.intUnit2_2Coords[y] == gridY && !Conquest.booleanUnitKilled[4]) {
                    booleanEnemyFound = true;
                }
                if (ConquestMap.intUnit2_3Coords[x] == gridX && 
                        ConquestMap.intUnit2_3Coords[y] == gridY && !Conquest.booleanUnitKilled[5]) {
                    booleanEnemyFound = true;
                }
            }
            else {
                if (ConquestMap.intUnit1_1Coords[x] == gridX && 
                        ConquestMap.intUnit1_1Coords[y] == gridY && !Conquest.booleanUnitKilled[0]) {
                    booleanEnemyFound = true;
                }
                if (ConquestMap.intUnit1_2Coords[x] == gridX && 
                        ConquestMap.intUnit1_2Coords[y] == gridY && !Conquest.booleanUnitKilled[1]) {
                    booleanEnemyFound = true;
                }
                if (ConquestMap.intUnit1_3Coords[x] == gridX && 
                        ConquestMap.intUnit1_3Coords[y] == gridY && !Conquest.booleanUnitKilled[2]) {
                    booleanEnemyFound = true;
                }
            }
        }
        catch (IndexOutOfBoundsException e) { //If out of bounds, definitely no enemy unit there.
            return false;
        }
        return booleanEnemyFound;
    }
    public static int findSpecificEnemyUnit(int gridX, int gridY) {
        int intEnemyFound = 999; //999 is a sentinel value that the method will return if no unit found.
        int x = 0, y = 1; //Coordinate axes
        try {
            if (Conquest.booleanIsPlayer1Turn) {
                if (ConquestMap.intUnit2_1Coords[x] == gridX && 
                        ConquestMap.intUnit2_1Coords[y] == gridY && !Conquest.booleanUnitKilled[3]) {
                    intEnemyFound = 3;
                }
                if (ConquestMap.intUnit2_2Coords[x] == gridX && 
                        ConquestMap.intUnit2_2Coords[y] == gridY && !Conquest.booleanUnitKilled[4]) {
                    intEnemyFound = 4;
                }
                if (ConquestMap.intUnit2_3Coords[x] == gridX && 
                        ConquestMap.intUnit2_3Coords[y] == gridY && !Conquest.booleanUnitKilled[5]) {
                    intEnemyFound = 5;
                }
            }
            else {
                if (ConquestMap.intUnit1_1Coords[x] == gridX && 
                        ConquestMap.intUnit1_1Coords[y] == gridY && !Conquest.booleanUnitKilled[0]) {
                    intEnemyFound = 0;
                }
                if (ConquestMap.intUnit1_2Coords[x] == gridX && 
                        ConquestMap.intUnit1_2Coords[y] == gridY && !Conquest.booleanUnitKilled[1]) {
                    intEnemyFound = 1;
                }
                if (ConquestMap.intUnit1_3Coords[x] == gridX && 
                        ConquestMap.intUnit1_3Coords[y] == gridY && !Conquest.booleanUnitKilled[2]) {
                    intEnemyFound = 2;
                }
            }
        }
        catch (IndexOutOfBoundsException e) { //If out of bounds, definitely no enemy unit there.
            return 999;
        }
        return intEnemyFound;
    }
}   
