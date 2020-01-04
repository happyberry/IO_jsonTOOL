package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;

import java.util.*;

/**
 * Class containing all the logic and methods needed to compare two given text files.
 *
 * @author kejkejovsky
 * @version 1.0
 */

public class JSONCompare extends Decorator {

    
    private List<String> difference1;

    private List<String> difference2;

    /**
     * Constructor, calls constructor from component passed in constructor's argument.
     * @param component Component from which constructor will be called inside this constructor.
     */
    public JSONCompare(Component component) {
        super(component);
    }

    /**
     * Getter for difference1 field
     *
     * @return String with content of difference1 list, delimited with new line.
     */
    public String getDifference1() {
        return String.join("\n", difference1);
    }

    /**
     * Getter for difference2 field
     *
     * @return String with content of difference2 list, delimited with new line.
     */
    public String getDifference2() {
        return String.join("\n", difference2);
    }

    /**
     * Method compares two given strings line by line, checks if files are identical and adds different lines to proper lists.
     *
     * @param Object1 String with content of first of compared files.
     * @param Object2 String with content of second of compared files.
     * @return True if two files are identical, false if they have differences.
     */
    public boolean differences(String Object1, String Object2) {
        boolean result = true;
        difference1 = new ArrayList<String>();
        difference2 = new ArrayList<String>();

        if (Object1 == null && Object2 == null) {
            return true;
        }
        if (Object1 == null) {
            difference2 = Arrays.asList(Object2.split("\n"));
            return false;
        }
        if (Object2 == null) {
            difference1 = Arrays.asList(Object1.split("\n"));
            return false;
        }
        String[] First = Object1.split("\n");
        String[] Second = Object2.split("\n");

        int l = Math.min(First.length, Second.length);
        for (int i = 0; i < l; i++) {
            if (!First[i].equalsIgnoreCase(Second[i])) {
                difference1.add(i + 1 + ": " + First[i]);
                difference2.add(i + 1 + ": " + Second[i]);
                result = false;
            }
        }
        if (First.length > Second.length) {
            for (int i = Second.length; i < First.length; i++) {
                difference1.add(i + 1 + ": " + First[i]);
            }
            result = false;
        }
        if (First.length < Second.length) {
            for (int i = First.length; i < Second.length; i++) {
                difference2.add(i + 1 + ": " + Second[i]);
            }
            result = false;
        }
        return result;
    }
    /*public void wypisz(){
        System.out.println(difference1);
        System.out.println(difference2);
    }*/

    /**
     * Stub method derived from Component interface, not needed in this class, so it just returns null.
     */
    @Override
    public String operation(String jsonString) {
        return null;
    }

    /**
     * Method derived from Component interface, wrapper for differences method.
     *
     * @param firstString String containing first file content
     * @param secondString String containing second file content
     * @return String containing list of different lines in both files (or information about lack of them).
     */
    @Override
    public String operation(String firstString, String secondString) {
        boolean res = differences(firstString, secondString);
        String wyn = res ? "Identical" : "Different";
        String wynik = res ? wyn : wyn + "\nFile1:\n" + getDifference1() + "\nFile2:\n" + getDifference2() + "\n";
        return wynik;
    }
}
