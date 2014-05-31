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
public class ConquestCombat {
    
    private static boolean[] intAttackPossibility = new boolean[4];
    public static void checkAttackPossibility (int unitSelected) {
        if (Conquest.booleanIsPlayer1Turn) {
            //intAttackPossibility = 
            checkPlayer1Units(unitSelected);
        }
        else {
            checkPlayer2Units(unitSelected);
        }
    }
    private static int checkPlayer1Units (int unitSelected) {
        int[] intUnitCoords = new int[2];
        switch (unitSelected) {
            case 0:
                intUnitCoords = getUnitCoords(unitSelected);
                findEnemyUnits(intUnitCoords);
        }
        return 69;
    }
    private static void checkPlayer2Units (int unitSelected) {
        
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
                
        }
        return intUnitCoords;
    }
    private static void findEnemyUnits(int[] intUnitCoords) {
        int x = 0, y = 1; //Coordinate axes
        if (Conquest.booleanIsPlayer1Turn) {
            
        }
    }
    
    private static void log(String message) {
        System.out.println(message);
    }
}
