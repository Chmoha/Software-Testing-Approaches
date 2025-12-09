package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class Task6_2Service {

    public static class SumResult {
        private final String resultText;
        private final List<int[]> highlightedCells; // [row, col] pairs

        public SumResult(String resultText, List<int[]> highlightedCells) {
            this.resultText = resultText;
            this.highlightedCells = highlightedCells;
        }

        public String getResultText() { return resultText; }
        public List<int[]> getHighlightedCells() { return highlightedCells; }
    }

    public SumResult sumPositiveElementsIterative(int[][] matrix, int outerLimit, int innerLimit) {
        if (outerLimit <= 0 || innerLimit <= 0) {
            return new SumResult("Sum of positive elements: 0 (limits are <= 0)", new ArrayList<>());
        }

        StringBuilder result = new StringBuilder();
        List<int[]> highlightedCells = new ArrayList<>();
        int sum = 0;
        int totalIterations = 0;
        final int rows = matrix.length;
        final int maxOuter = Math.min(outerLimit, rows);

        result.append("Sum of Positive Elements Process:<br>");
        result.append("Outer limit (rows): ").append(maxOuter).append("<br>");
        result.append("Inner limit (columns): ").append(Math.min(innerLimit, matrix[0].length)).append("<br><br>");

        for (int i = 0; i < maxOuter; i++) {
            final int cols = matrix[i].length;
            final int maxInner = Math.min(innerLimit, cols);

            result.append("Row ").append(i).append(": ");
            int rowSum = 0;
            int rowIterations = 0;

            for (int j = 0; j < maxInner; j++) {
                totalIterations++;
                rowIterations++;

                if (matrix[i][j] > 0) {
                    sum += matrix[i][j];
                    rowSum += matrix[i][j];
                    highlightedCells.add(new int[]{i, j}); // Добавляем координаты для подсветки
                    result.append(String.format("[%d][%d]=%d(+) ", i, j, matrix[i][j]));
                } else {
                    result.append(String.format("[%d][%d]=%d ", i, j, matrix[i][j]));
                }
            }

            result.append("→ Row sum: ").append(rowSum);
            result.append(" (iterations: ").append(rowIterations).append(")<br>");
        }

        result.append("<br>Final sum of positive elements: ").append(sum);
        result.append("<br>Total iterations performed: ").append(totalIterations);

        return new SumResult(result.toString(), highlightedCells);
    }

    public String sortMatrixIterative(int[][] matrix, int outerLimit, int innerLimit) {
        if (outerLimit <= 0 || innerLimit <= 0 || innerLimit > matrix.length * matrix[0].length) {
            return "Matrix unchanged (invalid limits)";
        }

        StringBuilder result = new StringBuilder();

        // Flatten the matrix for sorting
        int[] flatArray = flattenMatrix(matrix);
        final int n = flatArray.length;

        result.append("Matrix Sorting Process (Bubble Sort):<br>");
        result.append("Original matrix (flattened): ").append(Arrays.toString(flatArray)).append("<br>");
        result.append("Outer limit: ").append(Math.min(outerLimit, n - 1)).append("<br>");
        result.append("Inner limit: ").append(Math.min(innerLimit, n)).append("<br><br>");

        int[] resultArray = flatArray.clone();
        int totalIterations = 0;
        final int maxOuter = Math.min(outerLimit, n - 1);

        for (int i = 0; i < maxOuter; i++) {
            boolean swapped = false;
            result.append("Outer iteration ").append(i + 1).append(": ");
            final int maxInner = Math.min(innerLimit, n - i - 1);

            for (int j = 0; j < maxInner; j++) {
                totalIterations++;

                if (resultArray[j] > resultArray[j + 1]) {
                    // Swap elements
                    int temp = resultArray[j];
                    resultArray[j] = resultArray[j + 1];
                    resultArray[j + 1] = temp;
                    swapped = true;

                    result.append(String.format("swap(%d↔%d) ", resultArray[j], resultArray[j + 1]));
                }
            }

            result.append(swapped ? "→ swapped<br>" : "→ no swap<br>");

            // Early termination if no swaps occurred
            if (!swapped) {
                result.append("Early termination: no swaps in this pass<br>");
                break;
            }
        }

        // Convert back to matrix
        int[][] sortedMatrix = unflattenMatrix(resultArray, matrix.length, matrix[0].length);

        result.append("<br>Sorted matrix (flattened): ").append(Arrays.toString(resultArray));
        result.append("<br><br>Sorted matrix:<br>").append(matrixToString(sortedMatrix));
        result.append("<br>Total iterations performed: ").append(totalIterations);

        return result.toString();
    }

    private int[] flattenMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] flat = new int[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flat[i * cols + j] = matrix[i][j];
            }
        }
        return flat;
    }

    private int[][] unflattenMatrix(int[] flatArray, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = flatArray[i * cols + j];
            }
        }
        return matrix;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append("<br>");
        }
        return sb.toString();
    }

}