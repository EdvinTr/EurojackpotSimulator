package com.company;

import com.sun.source.tree.Tree;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

public class Result {
    private Map<Integer, Integer> resultMap;

    public Result() {
        this.resultMap = generateEmptyResultMap();
    }

    public Map<Integer, Integer> getResultMap() {
        return resultMap;
    }

    public void resetResultMap() {
        resultMap = generateEmptyResultMap();
    }

    private TreeMap<Integer, Integer> generateEmptyResultMap() {
        /* TreeMap by default is sorted by its keys */
        TreeMap<Integer, Integer> result = new TreeMap<>();
        for (int i = 0; i <= 7; i++) {
            result.put(i, 0);
        }
        return result;
    }

    public void printFinalResult(int simulations) {
        String pattern = "";
        String formattedPercentage;

        for (Map.Entry<Integer, Integer> entry : getResultMap().entrySet()) {
            double percentageResult = ((double) entry.getValue() / simulations) * 100;

            if (percentageResult > 1) {
                pattern = "#.#";
            } else if (percentageResult < 1) {
                pattern = "#.#####";
            }
            DecimalFormat df = new DecimalFormat(pattern);
            df.setRoundingMode(RoundingMode.CEILING);
            formattedPercentage = df.format(percentageResult);
            if (entry.getKey() == 7 && entry.getValue() > 0) {
                System.out.println("-----------------EUROJACKPOT WIN-------------------");
            }
            System.out.printf("Number of [%s] | %s | (%s%%)\n", entry.getKey(), entry.getValue(), formattedPercentage);
        }
    }

    public void printElapsedTimeResult(long startTime, long endTime, int simulations) {
        double totalTimeElapsed = endTime - startTime;
        System.out.printf("Time Elapsed %.2fs", totalTimeElapsed / 1000000000);
        System.out.println("\nYou ran " + simulations + " iterations");
        System.out.println("This is equal to playing the EuroJackpot each week for " + simulations / 52 + " years");
    }
}
