package com.company;

import java.util.*;

public class Game {
    private final Result result;
    private final User user;

    public Game() {
        this.user = new User();
        this.result = new Result();
    }

    public Result getResult() {
        return result;
    }

    private int getSimulationsToRun() {
        return user.getSimulationsToRun();
    }

    public User getUser() {
        return user;
    }

    public void start() {
        getUser().chooseNumbers();
        getUser().chooseSimulations();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            long[] timeElapsed = runSimulations();
            long startTime = timeElapsed[0];
            long endTime = timeElapsed[1];

            getResult()
                    .printElapsedTimeResult(startTime, endTime, getSimulationsToRun());
            getResult()
                    .printFinalResult(getSimulationsToRun());


            printMenu();

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    resetGame();
                    done = true;
                    start();
                    break;
                case "2":
                    getResult().resetResultMap();
                    break;
                case "3":
                    done = true;
                    break;
            }
        }
    }

    public void resetGame() {
        getUser().setSimulationsToRun(0);
        getUser().getTicket().clear();
        getResult().resetResultMap();
    }

    public void printMenu() {
        System.out.println("********* Menu *********");
        System.out.println("1. Play again.");
        System.out.println("2. Rerun simulation.");
        System.out.println("3. Exit.");
    }

    private long[] runSimulations() {
        int simulationsToRun = getSimulationsToRun();
        List<Integer> usersTicket = user.getTicket();
        if (usersTicket.size() != 7) {
            return new long[]{};
        }
        long startTime = System.nanoTime();

        for (int i = 0; i < simulationsToRun; i++) {
            List<Integer> winningTicket = generateTicket();

            int matches = 0;
            for (int j = 0; j < winningTicket.size(); j++) {
                if (winningTicket.get(j).equals(usersTicket.get(j))) {
                    matches++;
                }
            }
            int currentValue = getResult().getResultMap().get(matches);
            getResult().getResultMap().put(matches, currentValue + 1);
        }
        long stopTime = System.nanoTime();
        return new long[]{startTime, stopTime};
    }

    private List<Integer> generateTicket() {
        /* Making sure no duplicates end up in the first 5 digits as well as no duplicates between the last 2 digits
         * The last 2 digits may contain numbers already defined in the first 5 */
        Random rand = new Random();
        ArrayList<Integer> ticket = new ArrayList<>();
        HashSet<Integer> usedNumbers = new HashSet<>(); //HashSet by default cannot contain duplicates
        int bounds;
        while (ticket.size() < 7) {
            bounds = ticket.size() < 5 ? 50 : 10;
            int nextRand = rand.nextInt(bounds) + 1;
            if (ticket.size() == 5) {
                usedNumbers.clear();
            }
            if (!usedNumbers.contains(nextRand)) {
                ticket.add(nextRand);
                usedNumbers.add(nextRand);
            }
        }
        return ticket;
    }
}
