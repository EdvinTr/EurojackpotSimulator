package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String loading = "Loading";

        System.out.print(loading);
        slowPrint(".....", 250);
        try {
            clearScreen();
        } catch (IOException e) {

        }
        System.out.println("\n*****Welcome to the Eurojackpot Simulator****");

        while (true) {
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
            System.out.println(AnsiColor.ANSI_GREEN + "Your row: " + AnsiColor.ANSI_RESET + myRow);

            System.out.println("\nHow many simulations would you like to run? <1-2147483647>");
            int numberOfLoops = 0;
            while (true) {
                try {
                    numberOfLoops = Integer.parseInt(scanner.nextLine());

                } catch (NumberFormatException e) {

                }
                if (numberOfLoops > 0 && numberOfLoops < Integer.MAX_VALUE) {
                    System.out.println(AnsiColor.ANSI_RED + "Executing..." + AnsiColor.ANSI_RESET);
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

            }
            System.out.println("You ran " + numberOfLoops + " iterations");
            System.out.println("This is equal to playing the Eurojackpot each week for " + numberOfLoops / 52 + " years");
            System.out.println("Number of 0 " + countNumOccurences(countEachIterationList, 0));
            System.out.println("Number of 1 " + countNumOccurences(countEachIterationList, 1));
            System.out.println("Number of 2 " + countNumOccurences(countEachIterationList, 2));
            System.out.println("Number of 3 " + countNumOccurences(countEachIterationList, 3));
            System.out.println("Number of 4 " + countNumOccurences(countEachIterationList, 4));
            System.out.println("Number of 5 " + countNumOccurences(countEachIterationList, 5));
            System.out.println("Number of 6 " + countNumOccurences(countEachIterationList, 6));
            System.out.println("Number of 7 " + countNumOccurences(countEachIterationList, 7));

            System.out.println("Would you like to play again? - Y/N");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                System.out.println("Existing Eurojackpot Simulator...");
                break;
            }
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

    private static int countNumOccurences(List<Integer> numList, int searchInt) {
        int count = 0;
        for (Integer i : numList) {
            if (i == searchInt) {
                count++;
            }
        }
        return count;
    }

    private static void slowPrint(String message, long millisPerChar) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e) {

            }
        }
    }

    public static void clearScreen() throws IOException {
        Runtime.getRuntime().exec("cls");
    }
}
