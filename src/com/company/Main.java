package com.company;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.print("Loading");
        slowPrint(".....", 250);
        try {
            clearConsole();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("*****Welcome to the Eurojackpot Simulator****\n");

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
            System.out.printf("Time Elapsed %.2fs", totalTimeElapsed / 1000000000);

            System.out.println("\nYou ran " + numberOfLoops + " iterations");
            System.out.println("This is equal to playing the Eurojackpot each week for " + numberOfLoops / 52 + " years");
            printUserResult(resultMap, numberOfLoops);

            System.out.println("Would you like to play again? - Y/N");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                System.out.println("Exiting Eurojackpot Simulator...");
                break;
            }
        }
    }

//    private static ArrayList<Integer> generateRow() {
//        Random rand = new Random();
//        ArrayList<Integer> myRow = new ArrayList<>();
//        int bounds;
//        while (myRow.size() < 7) {
//            bounds = myRow.size() < 5 ? 50 : 10;
//            int nextRand = rand.nextInt(bounds) + 1;
//            myRow.add(nextRand);
//        }
//        return myRow;
//    }

    private static ArrayList<Integer> generateRow() {
        Random rand = new Random();
        ArrayList<Integer> myRow = new ArrayList<>();
        HashSet<Integer> usedNumbers = new HashSet<>();
        int bounds;
        while (myRow.size() < 7) {
            bounds = myRow.size() < 5 ? 50 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            if (myRow.size() == 5) {
                usedNumbers.clear();
            }
            if (!usedNumbers.contains(nextRand)) {
                myRow.add(nextRand);
                usedNumbers.add(nextRand);
            }
        }
        return myRow;
    }

    private static void printUserResult(Map<Integer, Integer> result, int numberOfLoopsUsed) {
        String pattern = "";
        String formattedPercentage;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            double percentageResult = ((double) entry.getValue() / numberOfLoopsUsed) * 100;

            if (percentageResult > 1) {
                pattern = "#.#";
            } else if (percentageResult < 1) {
                pattern = "#.#####";
            }
            DecimalFormat df = new DecimalFormat(pattern);
            df.setRoundingMode(RoundingMode.CEILING);
            formattedPercentage = df.format(percentageResult);
            System.out.printf("Number of [%s] | %s | (%s%%)\n", entry.getKey(), entry.getValue(), formattedPercentage);
        }
    }

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
                e.printStackTrace();
            }
        }
    }

    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
