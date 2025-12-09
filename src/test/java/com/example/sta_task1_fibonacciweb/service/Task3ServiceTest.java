package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Task3ServiceTest {

    private Task3Service service;

    @BeforeEach
    void setUp() {
        service = new Task3Service();
    }

    @Test
    @DisplayName("Discriminants accuracy test")
    void testDiscriminantAccuracy() {
        double[][] coefficients = {
                {1, 2, 1},     // D = 0
                {1, 5, 6},     // D = 1
                {1, 4, 1},     // D = 12
                {2, 4, 2},     // D = 0
                {1, -3, 2},    // D = 1
                {0.5, 3.2, 0.7}, // D = 8.84
                {3, -7, 2},    // D = 25
        };

        // D
        List<Double> expected = Arrays.asList(0.0, 1.0, 12.0, 0.0, 1.0, 8.84, 25.0);

        // Actual results
        List<Double> actual = Arrays.stream(coefficients)
                .map(arr -> service.calculateDiscriminant(arr[0], arr[1], arr[2]))
                .collect(Collectors.toList());

        // ТОчность = 6
        List<String> expectedRounded = expected.stream()
                .map(v -> String.format("%.6f", v))
                .collect(Collectors.toList());

        List<String> actualRounded = actual.stream()
                .map(v -> String.format("%.6f", v))
                .collect(Collectors.toList());

        assertIterableEquals(expectedRounded, actualRounded,
                "Expected and actual results are different");
    }
    @Test
    @DisplayName("Discriminant accuracy test")
    void testDiscriminantDifferentAccuracy() {
        double a = 1, b = 1, c = 0.001; // D = 0.9960
        double D = service.calculateDiscriminant(a, b, c);

        List<String> expected = Arrays.asList("1,0", "0,996", "0,99600");
        List<String> actual = Arrays.asList(
                String.format("%.1f", D),
                String.format("%.3f", D),
                String.format("%.5f", D)
        );

        assertIterableEquals(expected, actual,
                "Rounding of the discriminant to different precisions was performed incorrectly");
    }

}
