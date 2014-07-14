/*
 * Copyright (C) 2014 Weilon (WhalesAreDelicious)
 * http://whalesaredelicious.github.io
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

/**
 * INFORMATION
 * This is the FileAccessor API written by Weilon. This API allows you to read, write, create and delete files easily.
 */

package io.github.whalesaredelicious.conquest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.swing.JOptionPane;
 
public class FileAccessor{
    
    public static String read(String fileName, String dataName) throws Exception{ //Read data from specified text file
        /* 
            fileName is the name of the file.
            dataName is the name of the data variable being retrieved.
        */
            Properties file = new Properties(); //Instantialise Properties class in order to interact with file.
            FileInputStream input = new FileInputStream(fileName); //Instantialise FileInputStream class to prepare for file reading.
                file.load(input);
                String dataValue = file.getProperty(dataName);
                input.close(); //Close input stream as program no longer needs to read the file.
                return dataValue;
    }
    public static boolean write(String fileName, String dataName, String dataValue) {
        /* 
            fileName is the name of the file.
            dataName is the name of the data variable being set.
            dataValue is the value of the data variable being set.
        
            The method will return a boolean value. This is to allow other classes
            calling this method able to have error handling themselves should this 
            method encounter any errors. This is optional however.
            E.g. If returned true, do this. If returned false, do something else.
        */
        try {
            /*
                File needs to be loaded into memory before being modified.
                This is to prevent the program from wiping the file.
            */
            Properties file = new Properties(); //Intantialise Properties class in order to interact with file.
            FileInputStream input = new FileInputStream(fileName); //Instantialise FileInputStream class to prepare for file reading.
            try {
                file.load(input);
                input.close(); //Close input stream as program no longer needs to read it.
            }
            catch (IOException error) {
                log("IO Exception error after attempting to read file " + fileName);
                log(error.getMessage());
                return false;
            }
            
            FileOutputStream output = new FileOutputStream(fileName); //Initialise FileOutputStream to prepare for writing in file.
            try {
                file.setProperty(dataName, dataValue);
                file.store(output, null);
                output.close(); //Close output stream as program has finished writing in it.
                return true;
            }
            catch (IOException error) {
                /* 
                    If this occurs, there was a problem with locating the file.
                    Details will be logged to the console, and then the file
                    will be regenerated.
                */
                log("IO Exception error after attempting to read file " + fileName);
                log(error.getMessage());
                return false;
            }
        }
        catch (FileNotFoundException error) {
            log("FileNotFound Exception occurred. Cannot find file " + fileName);
            log(error.getMessage());
            log("Regenerating file...");
            regenFile(fileName);
            return false;
        }
    }
    
    public static boolean checkFileExists(String fileName) {
        /*
            This method tests whether the specified file exists or not.
            If it doesn't exist, the method will return false.
            If it does exist, the method will return true.
        */
        try {
            FileInputStream input = new FileInputStream(fileName);
            //Attempt to open file. If file cannot be found, catch statement will be executed.
            try {
                input.close();
                //If found was found and open, the stream will need to be closed again.
            }
            catch (IOException error) {
                log("IO Exception occurred when trying to close file " + fileName);
            }
            return true; //Since file was able to be interacted with, return true;
        }
        catch (FileNotFoundException error) {
            return false; //Since file was unable to be found, return false;
        }
    }
    public static void regenFile(String fileName){
        try {
            PrintWriter file = new PrintWriter(fileName, "UTF-8");
            /*
                ======
                TO DO
                ======
                Add data names when needed.
            */
            if (fileName == Conquest.stringSaveFile) {
                writeConquestProperties(fileName);
            }
            else if (fileName == Conquest.stringHighScoreFile) {
                writeHighScore(fileName);
            }
        }
        catch (FileNotFoundException error) {
            log("FileNotFound Exception occurred. Cannot find file " + fileName);
            log(error.getMessage());
            msgBox("Unable to access file " + fileName + " due to a severe error."
                    + " Please notify the game developer of this issue.", "Error", "error");
        }
        catch (UnsupportedEncodingException error) {
            log("UnsupportedEncoding Exception occurred when attempting to write to file " + fileName);
            log(error.getMessage());
            msgBox("Unable to write to file " + fileName + " due to a severe error."
                    + " Please notify the game developer of this issue.", "Error", "error");
        }
    }
    private static void writeHighScore(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        write(fileName, "playerName", "None");
        write(fileName, "score", "0");
    }
    private static void writeConquestProperties(String fileName) 
            throws FileNotFoundException, UnsupportedEncodingException { 
        //This is to ensure that code that is specific to Conquest can be easily separated from the reusable portion.
        write(fileName, "gameFinished", "true");
        write(fileName, "stringPlayer1Name", "Player 1");
        write(fileName, "stringPlayer2Name", "Player 2");

        write(fileName, "intPlayer1TicketsLeft", "50");
        write(fileName, "intPlayer2TicketsLeft", "50");

        write(fileName, "booleanIsPlayer1Turn", "true");
        write(fileName, "booleanDiceRolled", "false");
        write(fileName, "intNumMoves", "0");
        //Unit Positions
        write(fileName, "intUnit0PosX", "0");
        write(fileName, "intUnit0PosY", "0");
        write(fileName, "intUnit0Power", "1");

        write(fileName, "intUnit1PosX", "1");
        write(fileName, "intUnit1PosY", "1");
        write(fileName, "intUnit1Power", "1");

        write(fileName, "intUnit2PosX", "2");
        write(fileName, "intUnit2PosY", "2");
        write(fileName, "intUnit2Power", "1");

        write(fileName, "intUnit3PosX", "3");
        write(fileName, "intUnit3PosY", "3");
        write(fileName, "intUnit3Power", "1");

        write(fileName, "intUnit4PosX", "4");
        write(fileName, "intUnit4PosY", "4");
        write(fileName, "intUnit4Power", "1");

        write(fileName, "intUnit5PosX", "5");
        write(fileName, "intUnit5PosY", "5");
        write(fileName, "intUnit5Power", "1");

        write(fileName, "capturePointAOwnership", "0");
        write(fileName, "capturePointBOwnership", "0");
        write(fileName, "capturePointCOwnership", "0");
        write(fileName, "capturePointDOwnership", "0");
    }
    public static void deleteFile (String fileName) {
        try {
            File fileToDelete = new File(fileName);
            fileToDelete.delete();
        }
        catch (NullPointerException error) {
            log("Null Pointer Exception. Printing error details...");
            log(error.getMessage());
            msgBox("Unable to delete file file " + fileName + " due to NullPointerException error."
                    + " Please notify the game developer of this issue.", "Error", "error");
        }
        catch (IllegalArgumentException error) {
            log("Illegal Argument Exception. Printing error details...");
            msgBox("Unable to delete file file " + fileName + " due to IllegalArgumentException error."
                    + " Please notify the game developer of this issue.", "Error", "error");
        }
    }
    private static void log(String message) { //Method to make console logging tasks easier to do.
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