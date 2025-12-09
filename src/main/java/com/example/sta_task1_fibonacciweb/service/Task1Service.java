package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class Task1Service {
    private final Random random = new Random();

    public Map<String, Object> solve(double a, double b, double c) {
        Map<String, Object> result = new HashMap<>();

        // Всегда кладём ключ discriminant (строкой) чтобы шаблон не ныл при отсутствии ключа.
        if (a == 0.0) {
            result.put("discriminant", "-");
            if (b == 0.0) {
                result.put("status", c == 0.0 ? "Infinitely many solutions" : "No solution");
            } else {
                double x = -c / b;
                result.put("status", "Linear equation (one root)");
                result.put("x", String.format("%.4f", x));
            }
            return result;
        }

        double D = b * b - 4 * a * c;
        result.put("discriminant", String.format("%.2f", D));

        // Используем порог для сравнения с нулём — безопаснее для double
        double eps = 1e-9;
        if (D > eps) {
            double x1 = (-b + Math.sqrt(D)) / (2 * a);
            double x2 = (-b - Math.sqrt(D)) / (2 * a);
            result.put("status", "Two real roots");
            result.put("x1", String.format("%.4f", x1));
            result.put("x2", String.format("%.4f", x2));
        } else if (Math.abs(D) <= eps) {
            double x = -b / (2 * a);
            result.put("status", "Zero solution, One real root");
            result.put("x", String.format("%.4f", x));
        } else {
            result.put("status", "Complex roots (no real solution)");
        }

        return result;
    }

    public double[] randomABC() {
        // -100.00 … +100.00
        double a = (random.nextInt(20001) - 10000) / 100.0;
        double b = (random.nextInt(20001) - 10000) / 100.0;
        double c = (random.nextInt(20001) - 10000) / 100.0;
        return new double[]{a, b, c};
    }
}
