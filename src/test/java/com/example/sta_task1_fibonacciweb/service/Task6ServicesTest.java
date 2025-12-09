package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6ServiceTest {

    private final Task6Service service = new Task6Service();

    @Test
    void calculateSumPositiveIterative_WithPositiveNumbers_ShouldSumCorrectly() {
        int[] array = {1, -2, 3, -4, 5};
        String result = service.calculateSumPositiveIterative(array, 5);

        assertTrue(result.contains("Final sum after 5 iterations: 9")); // 1 + 3 + 5 = 9
        assertTrue(result.contains("Added 1"));
        assertTrue(result.contains("Skipped -2"));
    }

    @Test
    void calculateSumPositiveIterative_WithIterationLimit_ShouldStopEarly() {
        int[] array = {1, 2, 3, 4, 5};
        String result = service.calculateSumPositiveIterative(array, 3);

        assertTrue(result.contains("Final sum after 3 iterations: 6")); // 1 + 2 + 3 = 6
        assertTrue(result.contains("Iteration 3"));
        assertFalse(result.contains("Iteration 4")); // Should not process beyond limit
    }

    @Test
    void calculateSumPositiveIterative_WithAllNegativeNumbers_ShouldReturnZero() {
        int[] array = {-1, -2, -3, -4, -5};
        String result = service.calculateSumPositiveIterative(array, 5);

        assertTrue(result.contains("Final sum after 5 iterations: 0"));
        assertTrue(result.contains("Skipped -1"));
    }

    @Test
    void calculateSumPositiveIterative_WithEmptyArray_ShouldHandleGracefully() {
        int[] array = {};
        String result = service.calculateSumPositiveIterative(array, 5);

        assertTrue(result.contains("Final sum after 0 iterations: 0"));
    }

    @Test
    void binarySearchIterative_WithExistingKey_ShouldFind() {
        int[] array = {5, 2, 8, 1, 9};
        String result = service.binarySearchIterative(array, 8, 10);

        assertTrue(result.contains("Found at index"));
        assertTrue(result.contains("Binary search process"));
        assertTrue(result.contains("Sorted array: [1, 2, 5, 8, 9]"));
    }

    @Test
    void binarySearchIterative_WithNonExistingKey_ShouldNotFind() {
        int[] array = {1, 2, 3, 4, 5};
        String result = service.binarySearchIterative(array, 10, 5);

        assertTrue(result.contains("Key not found after"));
        assertTrue(result.contains("Binary search process"));
    }

    @Test
    void binarySearchIterative_WithIterationLimit_ShouldStopEarly() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String result = service.binarySearchIterative(array, 10, 2);

        assertTrue(result.contains("Key not found after 2 iterations"));
        assertTrue(result.contains("Iteration 1"));
        assertTrue(result.contains("Iteration 2"));
        assertFalse(result.contains("Iteration 3")); // Should not exceed limit
    }

    @Test
    void binarySearchIterative_WithSingleElement_ShouldHandle() {
        int[] array = {5};
        String result = service.binarySearchIterative(array, 5, 3);

        assertTrue(result.contains("Found at index 0"));
    }

    @Test
    void binarySearchIterative_WithEmptyArray_ShouldHandle() {
        int[] array = {};
        String result = service.binarySearchIterative(array, 5, 3);

        assertTrue(result.contains("Key not found after 0 iterations"));
    }

    // Новые тесты для Bubble Sort
    @Test
    void bubbleSortIterative_WithValidLimits_ShouldSort() {
        int[] array = {3, 1, 4, 2};
        String result = service.bubbleSortIterative(array, 3, 4);

        assertTrue(result.contains("Bubble Sort Process"));
        assertTrue(result.contains("Sorted array: [1, 2, 3, 4]"));
        assertTrue(result.contains("Total iterations performed"));
    }

    @Test
    void bubbleSortIterative_WithPartialLimits_ShouldPartiallySort() {
        int[] array = {3, 1, 4, 2};
        String result = service.bubbleSortIterative(array, 1, 2);

        assertTrue(result.contains("Bubble Sort Process"));
        assertTrue(result.contains("Total iterations performed: 2"));
    }

    @Test
    void bubbleSortIterative_WithZeroLimits_ShouldNotSort() {
        int[] array = {3, 1, 4, 2};
        String result = service.bubbleSortIterative(array, 0, 0);

        assertTrue(result.contains("Bubble Sort Process"));
        assertTrue(result.contains("Sorted array: [3, 1, 4, 2]")); // unchanged
    }

    @Test
    void bubbleSortIterative_WithAlreadySorted_ShouldEarlyTerminate() {
        int[] array = {1, 2, 3, 4};
        String result = service.bubbleSortIterative(array, 5, 5);

        assertTrue(result.contains("Early termination: no swaps in this pass"));
    }

    @Test
    void bubbleSortIterative_WithSingleElement_ShouldHandle() {
        int[] array = {5};
        String result = service.bubbleSortIterative(array, 3, 3);

        assertTrue(result.contains("Sorted array: [5]"));
    }

    @Test
    void bubbleSortIterative_WithEmptyArray_ShouldHandle() {
        int[] array = {};
        String result = service.bubbleSortIterative(array, 3, 3);

        assertTrue(result.contains("Sorted array: []"));
    }
}