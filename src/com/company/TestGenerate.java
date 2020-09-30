package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TestGenerate {

    public static void main(String[] args) {

//        ArrayList<Integer> row = generateRow2();
//
//        System.out.println(row);
//
//    }
//
//    private static ArrayList<Integer> generateRow() {
//        Random rand = new Random();
//        ArrayList<Integer> myRow = new ArrayList<>();
//        ArrayList<Integer> usedNumbers = new ArrayList<>();
//        ArrayList<Integer> lastTwoDigits = new ArrayList<>();
//        int bounds;
//        while (myRow.size() < 7) {
//            bounds = myRow.size() < 5 ? 50 : 10;
//            int nextRand = rand.nextInt(bounds) + 1;
//            if (!usedNumbers.contains(nextRand)) {
//                myRow.add(nextRand);
//                usedNumbers.add(nextRand);
//            } else if (myRow.size() > 5 && !lastTwoDigits.contains(nextRand)) {
//                myRow.add(nextRand);
//                lastTwoDigits.add(nextRand);
//            }
//        }
//        return myRow;
//    }
//
//    private static ArrayList<Integer> generateRow2() {
//        Random rand = new Random();
//        ArrayList<Integer> myRow = new ArrayList<>();
//        HashSet<Integer> usedNumbers = new HashSet<>();
//        int bounds;
//        while (myRow.size() < 7) {
//            bounds = myRow.size() < 5 ? 50 : 10;
//            int nextRand = rand.nextInt(bounds) + 1;
//            if(myRow.size() == 5) {
//                usedNumbers.clear();
//            }
//            if(!usedNumbers.contains(nextRand)) {
//                myRow.add(nextRand);
//                usedNumbers.add(nextRand);
//            }
//        }
//        return myRow;
    }
}

