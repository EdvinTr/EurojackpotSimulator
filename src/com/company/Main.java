package com.company;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //LinkedHashSet<Integer> myRow = generateRow();
        LinkedHashSet<Integer> myRow = new LinkedHashSet<>();
        LinkedHashSet<Integer> winningRow = generateRow();

//        for (Integer integer : myRow) {
//            System.out.print(integer + " ");
//        }
//        System.out.println();
//        for (Integer i : winningRow) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String instructions = myRow.size() < 5 ? "Enter 5 numbers 1-50" : "Enter 2 numbers 1-10";
        System.out.println(instructions);
        while (myRow.size() < 7) {
            if (myRow.size() == 5) {
                System.out.println(instructions);
            }
            String input = scanner.nextLine();
            int parsedInput = Integer.parseInt(input);
            if ((myRow.size() < 5) && (parsedInput <= 50) && (parsedInput > 0)) {
                myRow.add(parsedInput);
            }
            if ((myRow.size() >= 5) && (parsedInput <= 10) && (parsedInput > 0)) {
                myRow.add(parsedInput);
            }
            System.out.println(myRow.size());
        }
        System.out.println("Your row: " + myRow);
        int count = 0;

        Iterator<Integer> winningRowIterator = winningRow.iterator();
        Iterator<Integer> myRowIterator = myRow.iterator();
        while (winningRowIterator.hasNext()) {
            int myNum = myRowIterator.next();
            if (myNum == winningRowIterator.next()) {
                count++;
            }
        }
        System.out.println(count);


    }

    private static LinkedHashSet<Integer> generateRow() {
        Random rand = new Random();
        LinkedHashSet<Integer> myRow = new LinkedHashSet<>();

        int bounds;
        while (myRow.size() < 7) {
            bounds = myRow.size() < 5 ? 50 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            myRow.add(nextRand);
        }
        return myRow;
    }
}
