package com.company;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> myRow = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String instructions = "Enter 5 numbers 1-50";
        System.out.println(instructions);
        while (myRow.size() < 7) {
            if (myRow.size() == 5) {
                System.out.println("Enter 2 numbers 1-10");
            }
            String input = scanner.nextLine();
            int parsedInput = 0;
            try {
                parsedInput = Integer.parseInt(input);
            } catch (NumberFormatException e) {

            }
            if ((myRow.size() <= 5) && (parsedInput > 0) && (parsedInput <= 50) && (!myRow.contains(parsedInput))) {
                myRow.add(parsedInput);
            }
            if ((myRow.size() > 5) && (parsedInput > 0) && (parsedInput <= 10)) {
                myRow.add(parsedInput);
            }
            System.out.println("Size: " + myRow.size());
        }
        System.out.println("Your row: " + myRow);

        System.out.println("\nHow many simulations would you like to run?");
        int numberOfLoops = 0;
        try {
            numberOfLoops = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

        }

        for (int i = 0; i < numberOfLoops; i++) {
            ArrayList<Integer> winningRow = generateRow();

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

    }

    private static ArrayList<Integer> generateRow() {
        Random rand = new Random();
        ArrayList<Integer> myRow = new ArrayList<>();

        int bounds;
        while (myRow.size() < 7) {
            bounds = myRow.size() < 5 ? 50 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            myRow.add(nextRand);
        }
        return myRow;
    }
}
