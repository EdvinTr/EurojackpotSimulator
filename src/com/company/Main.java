package com.company;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> myRow = new ArrayList<>();
        List<Integer> lastTwoNumbers = new ArrayList<>();

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
            if ((myRow.size() < 5) && (parsedInput > 0) && (parsedInput <= 50) && (!myRow.contains(parsedInput))) {
                myRow.add(parsedInput);
            } else if ((myRow.size() >= 5) && (parsedInput > 0) && (parsedInput <= 10)) {
                if (!lastTwoNumbers.contains(parsedInput)) {
                    myRow.add(parsedInput);
                    lastTwoNumbers.add(parsedInput);
                }
            }

            //  System.out.println("Size: " + myRow.size());
        }
        System.out.println("Your row: " + myRow);

        System.out.println("\nHow many simulations would you like to run? <1-2147483647>");
        int numberOfLoops = 0;
        while (true) {
            try {
                numberOfLoops = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

            }
            if (numberOfLoops > 0 && numberOfLoops < Integer.MAX_VALUE) {
                break;
            } else {
                System.out.println("Specify a correct number of times to loop");
                numberOfLoops = 0;
            }
        }
        List<Integer> countEachIterationList = new ArrayList<>();
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
            countEachIterationList.add(count);

//            switch (count) {
//                case 0:
//                case 1:
//                case 2:
//                case 3:
//                case 4:
//                    break;
//                case 5:
//                case 6:
//                    System.out.println("Count " + count);
//                    break;
//                case 7:
//                    System.out.println("You won JackPot!");
//            }
        }
        System.out.println("You ran " + numberOfLoops + " iterations");
        System.out.println("This is equal to playing Eurojackpot each week for " + numberOfLoops / 52 + " years");
        System.out.println("Number of 0 " + countNumOccurences(countEachIterationList, 0));
        System.out.println("Number of 1 " + countNumOccurences(countEachIterationList, 1));
        System.out.println("Number of 2 " + countNumOccurences(countEachIterationList, 2));
        System.out.println("Number of 3 " + countNumOccurences(countEachIterationList, 3));
        System.out.println("Number of 4 " + countNumOccurences(countEachIterationList, 4));
        System.out.println("Number of 5 " + countNumOccurences(countEachIterationList, 5));
        System.out.println("Number of 6 " + countNumOccurences(countEachIterationList, 6));
        System.out.println("Number of 7 " + countNumOccurences(countEachIterationList, 7));


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

    private static int countNumOccurences(List<Integer> numList, int searchInt) {
        int count = 0;
        for (Integer i : numList) {
            if (i == searchInt) {
                count++;
            }
        }
        return count;
    }
}
