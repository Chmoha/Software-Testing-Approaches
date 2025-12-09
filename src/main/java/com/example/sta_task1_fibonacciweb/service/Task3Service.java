package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Task3Service {
    private final Random random = new Random();

    public Map<String, Object> solve(double a, double b, double c, int accuracy) {
        Map<String, Object> result = new HashMap<>();

        if (a == 0.0) {
            result.put("discriminant", "-");
            if (b == 0.0) {
                result.put("status", c == 0.0 ? "Infinitely many solutions" : "No solution");
            } else {
                double x = -c / b;
                result.put("status", "Linear equation (one root)");
                result.put("x", format(x, accuracy));
            }
            return result;
        }

        double D = b * b - 4 * a * c;
        result.put("discriminant", format(D, accuracy));

        double eps = 1e-9;
        if (D > eps) {
            double x1 = (-b + Math.sqrt(D)) / (2 * a);
            double x2 = (-b - Math.sqrt(D)) / (2 * a);
            result.put("status", "Two real roots");
            result.put("x1", format(x1, accuracy));
            result.put("x2", format(x2, accuracy));
        } else if (Math.abs(D) <= eps) {
            double x = -b / (2 * a);
            result.put("status", "Zero solution, One real root");
            result.put("x", format(x, accuracy));
        } else {
            result.put("status", "Complex roots (no real solution)");
        }

        return result;
    }

    private String format(double number, int accuracy) {
        return String.format("%." + accuracy + "f", number);
    }

    public double[] randomABC() {
        double a = (random.nextInt(20001) - 10000) / 100.0;
        double b = (random.nextInt(20001) - 10000) / 100.0;
        double c = (random.nextInt(20001) - 10000) / 100.0;
        return new double[]{a, b, c};
    }

    public double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }
}
