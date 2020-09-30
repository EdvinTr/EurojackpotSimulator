package com.company;

import java.util.ArrayList;
import java.util.Random;

public class TestGenerate {

    public static void main(String[] args) {

        ArrayList<Integer> row = generateRow();

        System.out.println(row);

        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(4);
        secondRow.add(2);

        row.addAll(secondRow);
        System.out.println(row);

    }

    private static ArrayList<Integer> generateRow() {
        Random rand = new Random();
        ArrayList<Integer> myRow = new ArrayList<>();
        ArrayList<Integer> usedNumbers = new ArrayList<>();
        ArrayList<Integer> lastTwoDigits = new ArrayList<>();
        int bounds;
        while (myRow.size() < 7) {
            bounds = myRow.size() < 5 ? 50 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            if (!usedNumbers.contains(nextRand)) {
                myRow.add(nextRand);
                usedNumbers.add(nextRand);
            } else if (myRow.size() > 5 && !lastTwoDigits.contains(nextRand)) {
                myRow.add(nextRand);
                lastTwoDigits.add(nextRand);
            }
        }
        return myRow;
    }
}
