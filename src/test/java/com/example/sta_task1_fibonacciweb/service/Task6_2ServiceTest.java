package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6_2ServiceTest {

    private final Task6_2Service task6_2Service = new Task6_2Service();

    private final int[][] TEST_MATRIX = {
            {1, -2, 3},
            {-4, 5, -6},
            {7, -8, 9}
    };

    @Test
    void sumPositiveElements_WithFullLimits_ShouldReturnCorrectSum() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 3, 3);

        assertEquals(25, getSumFromResult(result.getResultText())); // 1 + 3 + 5 + 7 + 9 = 25
        assertEquals(5, result.getHighlightedCells().size());
    }

    @Test
    void sumPositiveElements_WithPartialLimits_ShouldReturnPartialSum() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 2, 2);

        assertEquals(6, getSumFromResult(result.getResultText())); // 1 + 5 = 6
        assertEquals(2, result.getHighlightedCells().size());
    }

    @Test
    void sumPositiveElements_WithZeroLimits_ShouldReturnZero() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 0, 0);

        assertTrue(result.getResultText().contains("0"));
        assertTrue(result.getHighlightedCells().isEmpty());
    }

    @Test
    void sumPositiveElements_WithNegativeLimits_ShouldReturnZero() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, -1, -1);

        assertTrue(result.getResultText().contains("0"));
        assertTrue(result.getHighlightedCells().isEmpty());
    }

    @Test
    void sumPositiveElements_WithLimitsLargerThanMatrix_ShouldCalculateFullSum() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 10, 10);

        assertEquals(25, getSumFromResult(result.getResultText()));
        assertEquals(5, result.getHighlightedCells().size());
    }

    @Test
    void sumPositiveElements_WithPartialRows_ShouldCalculateCorrectly() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 1, 3);

        assertEquals(4, getSumFromResult(result.getResultText())); // 1 + 3 = 4
        assertEquals(2, result.getHighlightedCells().size());
    }

    @Test
    void sumPositiveElements_WithPartialColumns_ShouldCalculateCorrectly() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 3, 1);

        assertEquals(8, getSumFromResult(result.getResultText())); // 1 + 5 + 7 = 13
        assertEquals(2, result.getHighlightedCells().size());
    }

    @Test
    void sumPositiveElements_ShouldHighlightCorrectCells() {
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(TEST_MATRIX, 3, 3);

        // Проверяем, что подсвечены правильные ячейки
        assertTrue(containsCell(result.getHighlightedCells(), 0, 0)); // 1
        assertTrue(containsCell(result.getHighlightedCells(), 0, 2)); // 3
        assertTrue(containsCell(result.getHighlightedCells(), 1, 1)); // 5
        assertTrue(containsCell(result.getHighlightedCells(), 2, 0)); // 7
        assertTrue(containsCell(result.getHighlightedCells(), 2, 2)); // 9

        // Проверяем, что отрицательные не подсвечены
        assertFalse(containsCell(result.getHighlightedCells(), 0, 1)); // -2
        assertFalse(containsCell(result.getHighlightedCells(), 1, 0)); // -4
        assertFalse(containsCell(result.getHighlightedCells(), 1, 2)); // -6
        assertFalse(containsCell(result.getHighlightedCells(), 2, 1)); // -8
    }

    @Test
    void sumPositiveElements_WithAllNegativeMatrix_ShouldReturnZero() {
        int[][] negativeMatrix = {
                {-1, -2},
                {-3, -4}
        };
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(negativeMatrix, 2, 2);

        assertEquals(0, getSumFromResult(result.getResultText()));
        assertTrue(result.getHighlightedCells().isEmpty());
    }

    @Test
    void sumPositiveElements_WithAllPositiveMatrix_ShouldReturnCorrectSum() {
        int[][] positiveMatrix = {
                {1, 2},
                {3, 4}
        };
        Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(positiveMatrix, 2, 2);

        assertEquals(10, getSumFromResult(result.getResultText())); // 1+2+3+4=10
        assertEquals(4, result.getHighlightedCells().size());
    }

    private int getSumFromResult(String resultText) {
        // Extract sum from result text like "Final sum of positive elements: 25"
        String[] parts = resultText.split("Final sum of positive elements: ");
        if (parts.length > 1) {
            String sumPart = parts[1].split("<")[0];
            return Integer.parseInt(sumPart.trim());
        }
        return 0;
    }

    private boolean containsCell(java.util.List<int[]> highlightedCells, int row, int col) {
        return highlightedCells.stream()
                .anyMatch(cell -> cell[0] == row && cell[1] == col);
    }
}