package com.company;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        LinkedHashSet<Integer> myRow = generateRow();


        for (Integer integer : myRow) {
            System.out.println(integer);
        }

        
    }

    private static LinkedHashSet<Integer> generateRow() {
        Random rand = new Random();
        LinkedHashSet<Integer> myRow = new LinkedHashSet<>();

        int bounds;
        while (myRow.size() < 7) {

            bounds = myRow.size() < 5 ? 100 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            myRow.add(nextRand);
        }
        return myRow;
    }
}
