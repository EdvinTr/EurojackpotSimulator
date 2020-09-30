package com.company;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.print("Loading");
        slowPrint(".....", 250);
        try {
            clearConsole();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\t\t\t\t*****Welcome to the Eurojackpot Simulator****\n");

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
                    System.out.println("Enter a valid number");
                }
                if ((myRow.size() < 5) && (parsedInput > 0) && (parsedInput <= 50) && (!myRow.contains(parsedInput))) {
                    myRow.add(parsedInput);
                } else if ((myRow.size() >= 5) && (parsedInput > 0) && (parsedInput <= 10)) {
                    if (!lastTwoNumbers.contains(parsedInput)) {
                        myRow.add(parsedInput);
                        lastTwoNumbers.add(parsedInput);
                    }
                }
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
                    System.out.println("Executing...");
                    break;
                } else {
                    System.out.println("Specify a correct number of times to loop");
                    numberOfLoops = 0;
                }
            }

            TreeMap<Integer, Integer> resultMap = generateEmptyResultMap();
            long startTime = System.nanoTime();
            for (int i = 0; i < numberOfLoops; i++) {
                List<Integer> winningRow = generateRow();
                int countMatches = 0;

                for (int n = 0; n < winningRow.size(); n++) {
                    if (myRow.get(n).equals(winningRow.get(n))) {
                        countMatches++;
                    }
                }
                int currentValue = resultMap.get(countMatches) + 1;
                resultMap.put(countMatches, currentValue);
            }
            long stopTime = System.nanoTime();
            double totalTimeElapsed = stopTime - startTime;
            System.out.printf("Time Elapsed %.2f", totalTimeElapsed / 1000000000);

            System.out.println("\nYou ran " + numberOfLoops + " iterations");
            System.out.println("This is equal to playing the Eurojackpot each week for " + numberOfLoops / 52 + " years");
            System.out.println("Number of 0 " + resultMap.get(0));
            System.out.println("Number of 1 " + resultMap.get(1));
            System.out.println("Number of 2 " + resultMap.get(2));
            System.out.println("Number of 3 " + resultMap.get(3));
            System.out.println("Number of 4 " + resultMap.get(4));
            System.out.println("Number of 5 " + resultMap.get(5));
            System.out.println("Number of 6 " + resultMap.get(6));
            System.out.println("Number of 7 " + resultMap.get(7));
//            System.out.println("Number of 0 " + countNumOccurences(countEachIterationList, 0));
//            System.out.println("Number of 1 " + countNumOccurences(countEachIterationList, 1));
//            System.out.println("Number of 2 " + countNumOccurences(countEachIterationList, 2));
//            System.out.println("Number of 3 " + countNumOccurences(countEachIterationList, 3));
//            System.out.println("Number of 4 " + countNumOccurences(countEachIterationList, 4));
//            System.out.println("Number of 5 " + countNumOccurences(countEachIterationList, 5));
//            System.out.println("Number of 6 " + countNumOccurences(countEachIterationList, 6));
//            System.out.println("Number of 7 " + countNumOccurences(countEachIterationList, 7));

            System.out.println("Would you like to play again? - Y/N");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                System.out.println("Exiting Eurojackpot Simulator...");
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

//    private static int countNumOccurences(TreeMap<Integer, Integer> map, int searchInt) {
//        int count = 0;
//        for (Integer i : map) {
//            if (i == searchInt) {
//                count++;
//            }
//        }
//        return count;
//    }

    private static TreeMap<Integer, Integer> generateEmptyResultMap() {
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (int i = 0; i < 8; i++) {
            result.put(i, 0);
        }
        return result;
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

    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
