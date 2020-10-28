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
        System.out.println("*****Welcome to the EuroJackpot Simulator****\n");

        while (true) {
            List<Integer> myRow = new ArrayList<>();
            List<Integer> lastTwoNumbers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String instructions = "Enter 5 numbers 1-50";
            System.out.println(instructions);

            /* Loop until 7 numbers have been entered correctly */
            while (myRow.size() < 7) {
                if (myRow.size() != 0) {
                    System.out.println(myRow);
                }
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
                /* Check to make sure the first 5 digits contains numbers between 1-50 and the last 2 digits are between 1-10
                   Also check to make sure there are no duplicates between the first 5 numbers and no duplicates between the last 2 digits.

                   The last 2 digits are allowed to have values already defined in the first 5 digits!.
                 */
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
                /* Make sure the amount of loops specified is greater than zero and does not exceed max value of Integer */
                if (numberOfLoops > 0 && numberOfLoops < Integer.MAX_VALUE) {
                    System.out.println("Executing...");
                    break;
                } else {
                    System.out.println("Specify a correct number of times to loop");
                    numberOfLoops = 0;
                }
            }
            /* The resultMap will contain the result from all iterations */
            TreeMap<Integer, Integer> resultMap = generateEmptyResultMap();
            long startTime = System.nanoTime();
            for (int i = 0; i < numberOfLoops; i++) {
                List<Integer> winningRow = generateRow();
                int countMatches = 0;

                /* Check the users row against the randomly generated one */
                for (int n = 0; n < winningRow.size(); n++) {
                    if (myRow.get(n).equals(winningRow.get(n))) {
                        countMatches++;
                    }
                }
                /* Update the value at the correct position in the Map */
                int currentValue = resultMap.get(countMatches) + 1;
                resultMap.put(countMatches, currentValue);
            }

            /* Output the accumulated time across all iterations converted to seconds */
            long stopTime = System.nanoTime();
            double totalTimeElapsed = stopTime - startTime;
            System.out.printf("Time Elapsed %.2fs", totalTimeElapsed / 1000000000);

            System.out.println("\nYou ran " + numberOfLoops + " iterations");
            System.out.println("This is equal to playing the EuroJackpot each week for " + numberOfLoops / 52 + " years");
            printUserResult(resultMap, numberOfLoops);

            System.out.println("Would you like to play again? - Y/N");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                System.out.println("Exiting EuroJackpot Simulator...");
                break;
            }
        }
    }

    private static ArrayList<Integer> generateRow() {
        /* Making sure no duplicates end up in the first 5 digits as well as no duplicates between the last 2 digits

         * The last 2 digits may contain numbers already defined in the first 5 */
        Random rand = new Random();
        ArrayList<Integer> myRow = new ArrayList<>();
        HashSet<Integer> usedNumbers = new HashSet<>(); //HashSet by default cannot contain duplicates
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

        /* Loop through results -> present them in an easily read format with percentage calculation */

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
        /* TreeMap by default is sorted by its keys */
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
