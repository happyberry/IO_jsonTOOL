package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONCompare {
    private Logger logger;
    private List<String> difference1;
    private List<String> difference2;

    public JSONCompare(Logger logger) {
        this.logger = logger;
    }
    public List<String> getDifference1() { return difference1; }
    public List<String> getDifference2() { return difference2; }

    public boolean Compare(String Object1, String Object2) {
        boolean result = true;
        difference1 = new ArrayList<String>();
        difference2 = new ArrayList<String>();
        if(Object1.isEmpty() && Object2.isEmpty()) {
            return true;
        }
        if(Object1.isEmpty()){
            difference2 = Arrays.asList(Object2.split("\n"));
            return false;
        }
        if(Object2.isEmpty()){
            difference1 = Arrays.asList(Object1.split("\n"));
            return false;
        }
        String [] First = Object1.split("\n");
        String [] Second = Object2.split("\n");
        int l = Math.min(First.length, Second.length);
        for(int i=0; i<l; i++){
            if(!First[i].equalsIgnoreCase(Second[i])) {
                difference1.add(i + 1 + ": " + First[i]);
                difference2.add(i + 1 + ": " + Second[i]);
                result = false;
            }
        }
        if( First.length > Second.length) {
            for (int i = Second.length; i < First.length; i++)
                difference1.add(i + 1 + ": " + First[i]);
            result = false;
        }
        if( First.length < Second.length) {
            for (int i = First.length; i < Second.length; i++)
                difference2.add(i + 1 + ": " + Second[i]);
            result = false;
        }
        return result;
    }
    public void wypisz(){
        System.out.println(difference1);
        System.out.println(difference2);
    }

}
