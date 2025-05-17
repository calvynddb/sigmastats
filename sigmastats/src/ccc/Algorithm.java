package ccc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Algorithm {
    public static double[] parseInput(String input) {
        String[] parts = input.trim().split("[ ,]+");
        double[] data = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            data[i] = Double.parseDouble(parts[i]);
        }
        return data;
    }
    
    public static int count(double[] data) {
        int count = data.length;
        return count;
    }

    public static double sum(double[] data) {
        double total = 0;
        for (double d : data) total += d;
        return total;
    }

    public static double mean(double[] data) {
        return sum(data) / data.length;
    }

    public static double median(double[] data) {
        double[] sorted = data.clone();
        Arrays.sort(sorted);
        int n = sorted.length;
        return (n % 2 == 0) ? (sorted[n / 2 - 1] + sorted[n / 2]) / 2 : sorted[n / 2];
    }

    public static double mode(double[] data) {
        double[] sorted = data.clone();
        Arrays.sort(sorted);

        double mode = sorted[0];
        int maxCount = 1;
        int currentCount = 1;

        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] == sorted[i - 1]) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                mode = sorted[i];
            }
        }

        return mode;
    }

    public static double max(double[] data) {
        double max = data[0];
        for (double d : data) if (d > max) max = d;
        return max;
    }

    public static double min(double[] data) {
        double min = data[0];
        for (double d : data) if (d < min) min = d;
        return min;
    }

    public static double geometricMean(double[] data) {
        double product = 1.0;
        for (double d : data) {
            if (d <= 0) return Double.NaN; 
            product *= d;
        }
        return Math.pow(product, 1.0 / data.length);
    }

    public static double variance(double[] data) {
        double m = mean(data);
        double sum = 0;
        for (double d : data) sum += Math.pow(d - m, 2);
        return sum / data.length;
    }

    public static double stdDev(double[] data) {
        return Math.sqrt(variance(data));
    }

    public static double sampleVariance(double[] data) {
        double m = mean(data);
        double sum = 0;
        for (double d : data) sum += Math.pow(d - m, 2);
        return sum / (data.length - 1);
    }

    public static double sampleStdDev(double[] data) {
        return Math.sqrt(sampleVariance(data));
    }

    public static double getCriticalTValue(double confidence, int df) {
        if (df <= 0) return Double.NaN;
        if (confidence == 0.95) {
            if (df <= 30) return 2.045; // simplified lookup
            return 1.96;
        }
        return 1.96; // fallback
    }
    

}
