package com.example.sta_task1_fibonacciweb.service;

import com.example.sta_task1_fibonacciweb.model.BinarySearchResult;
import com.example.sta_task1_fibonacciweb.model.TestResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Task7Service {

    public BinarySearchResult binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        List<String> executionPath = new ArrayList<>();
        List<Integer> midValues = new ArrayList<>();

        boolean wentLeft = false;
        boolean wentRight = false;
        boolean loopEntered = false;

        executionPath.add("1: low = 0");
        executionPath.add("2: high = " + (arr.length - 1));

        if (arr.length == 0) {
            executionPath.add("3: array empty → return -1");
            return new BinarySearchResult(-1, executionPath, midValues, "Path 1");
        }

        while (low <= high) {
            loopEntered = true;
            executionPath.add("3: while (low <= high) - TRUE");
            int mid = (low + high) / 2;
            midValues.add(mid);
            executionPath.add("4: mid = " + mid);

            if (arr[mid] == target) {
                executionPath.add("5: arr[" + mid + "] == " + target + " - TRUE");
                executionPath.add("6: return " + mid);

                String pathType;
                if (!wentLeft && !wentRight) {
                    pathType = "Path 2";
                } else {
                    pathType = "Path 4";
                }

                return new BinarySearchResult(mid, executionPath, midValues, pathType);
            }

            executionPath.add("5: arr[" + mid + "] == " + target + " - FALSE");

            if (arr[mid] < target) {
                executionPath.add("7: arr[" + mid + "] < " + target + " - TRUE");
                low = mid + 1;
                wentRight = true;
                executionPath.add("8: low = " + low);
            } else {
                executionPath.add("7: arr[" + mid + "] < " + target + " - FALSE");
                high = mid - 1;
                wentLeft = true;
                executionPath.add("9: high = " + high);
            }

            executionPath.add("10: end of iteration");
        }

        executionPath.add("3: while (low <= high) - FALSE");
        executionPath.add("11: return -1");

        // Новая логика:
        // Если при поиске были движения вправо -> Path 3 (специфический case),
        // во всех остальных случаях (включая движение влево или отсутствие движений) -> Path 1.
        String pathType = wentRight ? "Path 3" : "Path 1";

        return new BinarySearchResult(-1, executionPath, midValues, pathType);
    }


    /**
     * Запуск всех тестовых случаев
     */
    public List<TestResult> runAllTests() {
        List<TestResult> results = new ArrayList<>();

        // Test Case 1
        results.add(runTestCase("TC-001", new int[]{}, 5, -1, "Path 1"));

        // Test Case 2
        results.add(runTestCase("TC-002", new int[]{1, 2, 3, 4, 5}, 3, 2, "Path 2"));

        // Test Case 3
        results.add(runTestCase("TC-003", new int[]{1, 2, 3, 4, 5}, 1, 0, "Path 4"));

        // Test Case 4
        results.add(runTestCase("TC-004", new int[]{1, 2, 3, 4, 5}, 5, 4, "Path 3"));

        // Test Case 5
        results.add(runTestCase("TC-005", new int[]{1, 3, 5, 7, 9}, 4, -1, "Path 3"));

        // Test Case 6
        results.add(runTestCase("TC-006", new int[]{10}, 10, 0, "Path 2"));

        // Test Case 7
        results.add(runTestCase("TC-007", new int[]{10}, 5, -1, "Path 1"));

        return results;
    }

    private TestResult runTestCase(String testId, int[] arr, int target, int expected, String expectedPath) {
        BinarySearchResult result = binarySearch(arr, target);
        boolean passed = result.getResult() == expected;

        return new TestResult(testId, arr, target, expected, result.getResult(),
                passed, result.getExecutionPath(), result.getPathType(), expectedPath);
    }

    /**
     * Проверка цикломатической сложности
     */
    public int calculateCyclomaticComplexity() {
        // Метод 1: E - N + 2
        int edges = 14;    // E
        int nodes = 12;    // N
        int v1 = edges - nodes + 2;

        // Метод 2: Количество регионов
        int v2 = 4;

        // Метод 3: P + 1
        int predicates = 3; // Узлы 3, 5, 7
        int v3 = predicates + 1;

        return v1; // Все методы дают 4
    }
}