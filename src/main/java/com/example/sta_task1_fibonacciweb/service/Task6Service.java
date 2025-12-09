package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class Task6Service {

    public String calculateSumPositiveIterative(int[] array, int iteration) {
        StringBuilder result = new StringBuilder();
        int sum = 0;
        int iterationsDone = 0;

        result.append("Iteration process:<br>");

        for (int i = 0; i < array.length && iterationsDone < iteration; i++, iterationsDone++) {
            if (array[i] > 0) {
                sum += array[i];
                result.append(String.format("Iteration %d: Added %d, Current sum = %d<br>",
                        iterationsDone + 1, array[i], sum));
            } else {
                result.append(String.format("Iteration %d: Skipped %d (not positive)<br>",
                        iterationsDone + 1, array[i]));
            }
        }

        result.append(String.format("<br>Final sum after %d iterations: %d", iterationsDone, sum));
        return result.toString();
    }

    public String binarySearchIterative(int[] array, int key, int iteration) {
        StringBuilder result = new StringBuilder();
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        result.append("Original array: ").append(Arrays.toString(array)).append("<br>");
        result.append("Sorted array: ").append(Arrays.toString(sortedArray)).append("<br>");
        result.append("Searching for: ").append(key).append("<br><br>");

        int left = 0;
        int right = sortedArray.length - 1;
        int iterationsDone = 0;
        boolean found = false;

        result.append("Binary search process:<br>");

        while (left <= right && iterationsDone < iteration) {
            iterationsDone++;
            int mid = left + (right - left) / 2;

            result.append(String.format("Iteration %d: left=%d, right=%d, mid=%d (value=%d)<br>",
                    iterationsDone, left, right, mid, sortedArray[mid]));

            if (sortedArray[mid] == key) {
                result.append(String.format("✓ Found at index %d after %d iterations",
                        mid, iterationsDone));
                found = true;
                break;
            } else if (sortedArray[mid] < key) {
                left = mid + 1;
                result.append("→ Searching right half<br>");
            } else {
                right = mid - 1;
                result.append("← Searching left half<br>");
            }
        }

        if (!found) {
            result.append(String.format("<br>Key not found after %d iterations", iterationsDone));
        }

        return result.toString();
    }

    public String bubbleSortIterative(int[] array, int outerLimit, int innerLimit) {
        StringBuilder result = new StringBuilder();
        int[] sortedArray = array.clone();
        final int n = sortedArray.length;

        result.append("Bubble Sort Process:<br>");
        result.append("Original array: ").append(Arrays.toString(array)).append("<br>");
        result.append("Outer limit: ").append(Math.min(outerLimit, n - 1)).append("<br>");
        result.append("Inner limit: ").append(Math.min(innerLimit, n)).append("<br><br>");

        int totalIterations = 0;
        final int maxOuter = Math.min(outerLimit, n - 1);

        for (int i = 0; i < maxOuter; i++) {
            boolean swapped = false;
            result.append("Outer iteration ").append(i + 1).append(": ");
            final int maxInner = Math.min(innerLimit, n - i - 1);

            for (int j = 0; j < maxInner; j++) {
                totalIterations++;

                if (sortedArray[j] > sortedArray[j + 1]) {
                    // Swap elements
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    swapped = true;

                    result.append(String.format("swap(%d↔%d) ", sortedArray[j], sortedArray[j + 1]));
                }
            }

            result.append(swapped ? "→ swapped<br>" : "→ no swap<br>");

            // Early termination if no swaps occurred
            if (!swapped) {
                result.append("Early termination: no swaps in this pass<br>");
                break;
            }
        }

        result.append("<br>Sorted array: ").append(Arrays.toString(sortedArray));
        result.append("<br>Total iterations performed: ").append(totalIterations);

        return result.toString();
    }
}