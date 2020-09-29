package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    static int Count = 0;
    static int max = 0;
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

//            Progress p = new Progress();
//            p.processing(numberOfLoops);

            List<Integer> countEachIterationList = new ArrayList<>();
            for (int i = 0; i < numberOfLoops; i++) {
                ArrayList<Integer> winningRow = generateRow();
                Count = i;
                int countMatches = 0;

                Iterator<Integer> winningRowIterator = winningRow.iterator();
                Iterator<Integer> myRowIterator = myRow.iterator();
                while (winningRowIterator.hasNext()) {
                    int myNum = myRowIterator.next();
                    if (myNum == winningRowIterator.next()) {
                        countMatches++;
                    }
                }
                countEachIterationList.add(countMatches);

            }
            System.out.println("\nYou ran " + numberOfLoops + " iterations");
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

//    public static class Progress {
//        Thread t;
//        public void processing(int max) {
//            t = new Thread(() -> {
//                System.out.print("Processing---------");
//                for (int i = 0; i <= 100; i++) {
//                    int x = (Count*100)/max;
//                    System.out.print(x);
//                    if (i < 10) {
//                        System.out.print(i + "%");
//                        System.out.print("\b\b");
//                    } else if (i >= 10 && i <= 99) {
//                        System.out.print(i + "%");
//                        System.out.print("\b\b\b");
//                    }
//                    if(i == 100){
//                        System.out.print(i + "%");
//                    }
//                    try {
//                        t.sleep(10);
//                    } catch (Exception e) {
//                    }
//
//                }
//            });
//            t.start();
//        }
//
//    }

}
