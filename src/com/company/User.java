package com.company;

import java.util.*;

public class User {
    private final List<Integer> ticket;
    private int simulationsToRun;
    private final Scanner scanner = new Scanner(System.in);

    public User() {
        this.ticket = new ArrayList<>();
    }

    public List<Integer> getTicket() {
        return ticket;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int getSimulationsToRun() {
        return simulationsToRun;
    }

    public void setSimulationsToRun(int simulationsToRun) {
        this.simulationsToRun = simulationsToRun;
    }

    public void chooseNumbers() {
        if (getTicket().size() == 7) {
            System.out.println(getTicket());
            return;
        }
        if (getTicket().size() < 5) {
            System.out.println("Enter 5 numbers 1-50");
            chooseFirstFiveNumbers(); // recursion
        }
        if (getTicket().size() == 5) {
            System.out.println("Enter 2 numbers 1-10");
            chooseLastTwoNumbers(); // recursion
        }
    }

    private void chooseFirstFiveNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 5) {
            System.out.println(getTicket());
            String userInput = scanner.nextLine();
            try {
                int parsedInput = Integer.parseInt(userInput);
                if (parsedInput <= 0 || parsedInput > 50) {
                    throw new Exception();
                }
                if (!numbers.contains(parsedInput)) {
                    numbers.add(parsedInput);
                    getTicket().add(parsedInput);
                }
            } catch (Exception e) {
                System.out.println("Enter a number between 1 and 50");
            }
        }
        chooseNumbers();

    }

    private void chooseLastTwoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < 2) {
            System.out.println(getTicket());
            String userInput = scanner.nextLine();
            try {
                int parsedInput = Integer.parseInt(userInput);
                if (parsedInput <= 0 || parsedInput > 10) {
                    throw new Exception();
                }
                if (!numbers.contains(parsedInput)) {
                    numbers.add(parsedInput);
                    getTicket().add(parsedInput);
                }
            } catch (Exception e) {
                System.out.println("Enter a number between 1 and 10");
            }
        }
        chooseNumbers();
    }

    public void chooseSimulations() {
        System.out.println("How many simulations would you like to run? <1-2147483647>");
        while (true) {
            try {
                int inputSimulations = Integer.parseInt(scanner.nextLine());
                if (inputSimulations > 0 && inputSimulations < Integer.MAX_VALUE) {
                    simulationsToRun = inputSimulations;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Choose a number between 1 and 2147483647");
            }
        }
    }

}
