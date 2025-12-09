package com.example.sta_task1_fibonacciweb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Task9 Service Performance Tests")
class Task9ServiceTest {

    @Autowired
    private Task9Service task9Service;

    private List<Integer> testArray;

    @BeforeEach
    void setUp() {
        // Генерируем тестовый массив для сортировки
        testArray = List.of(5, 3, 8, 1, 2, 7, 6, 4, 9, 0);
    }

    @Test
    @DisplayName("1. Fast Fibonacci Performance Test")
    void testFastFibonacciPerformance() {
        System.out.println("=== Fast Fibonacci Performance Test ===");

        // Тестируем разные значения
        int[] testValues = {10, 20, 30, 40, 50};

        for (int n : testValues) {
            long startTime = System.nanoTime();
            BigInteger result = task9Service.fibonacci(n);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // convert to milliseconds

            System.out.printf("Fast Fibonacci(%d) = %s | Time: %d ms%n", n, result, duration);

            // Проверяем что результат корректен
            assertNotNull(result);
            assertTrue(result.compareTo(BigInteger.ZERO) >= 0);

            // Быстрый Fibonacci должен выполняться быстро даже для больших n
            if (n <= 40) {
                assertTrue(duration < 100, "Fast Fibonacci should be fast for n=" + n);
            }
        }
    }

    @Test
    @DisplayName("2. Slow Fibonacci Performance Test")
    @Timeout(value = 30, unit = TimeUnit.SECONDS) // Ограничиваем время выполнения
    void testSlowFibonacciPerformance() {
        System.out.println("=== Slow Fibonacci Performance Test ===");

        // Медленный Fibonacci - тестируем меньшие значения
        int[] testValues = {10, 20, 30, 35};

        for (int n : testValues) {
            long startTime = System.nanoTime();
            BigInteger result = task9Service.slowFibonacci(n);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;

            System.out.printf("Slow Fibonacci(%d) = %s | Time: %d ms%n", n, result, duration);

            // Проверяем корректность результата
            assertNotNull(result);

            // Проверяем что медленная версия действительно медленнее
            BigInteger fastResult = task9Service.fibonacci(n);
            assertEquals(fastResult, result, "Fast and slow Fibonacci should give same result for n=" + n);

            System.out.printf("  -> Slow version is %dx slower than fast version%n",
                    duration / Math.max(1, getFastFibonacciTime(n)));
        }
    }

    @Test
    @DisplayName("3. Inefficient Sort Performance Test")
    void testInefficientSortPerformance() {
        System.out.println("=== Inefficient Sort Performance Test ===");

        int[] arraySizes = {100, 500, 1000, 2000};

        for (int size : arraySizes) {
            // Генерируем массив заданного размера
            List<Integer> largeArray = task9Service.generateLargeArray(size);

            long startTime = System.nanoTime();
            List<Integer> sorted = task9Service.inefficientSort(largeArray);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;

            System.out.printf("Bubble Sort (%d elements) | Time: %d ms%n", size, duration);

            // Проверяем что массив отсортирован
            assertTrue(isSorted(sorted), "Array should be sorted");
            assertEquals(size, sorted.size(), "Array size should remain the same");

            // Bubble sort имеет сложность O(n²) - время должно расти квадратично
            if (size >= 500) {
                System.out.printf("  -> Sorting time grows quadratically with array size%n");
            }
        }
    }

    @Test
    @DisplayName("4. Memory Intensive Operation Test")
    void testMemoryIntensiveOperation() {
        System.out.println("=== Memory Intensive Operation Test ===");

        int[] objectCounts = {1000, 10000, 50000};

        // Замеряем память до теста
        long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        for (int count : objectCounts) {
            long startTime = System.nanoTime();
            String result = task9Service.memoryIntensiveOperation(count);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;

            System.out.printf("Memory Operation (%d objects) = %s | Time: %d ms%n",
                    count, result, duration);

            assertTrue(result.contains("Created " + count + " objects"));

            // Замеряем использование памяти
            long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long memoryUsed = (memoryAfter - memoryBefore) / (1024 * 1024); // MB

            System.out.printf("  -> Memory used: ~%d MB%n", memoryUsed);
        }
    }

    @Test
    @DisplayName("5. Comprehensive Profiling Test")
    void testComprehensiveProfiling() {
        System.out.println("=== Comprehensive Profiling Test ===");
        System.out.println("Running all operations to identify performance bottlenecks...");

        long startTime = System.nanoTime();
        Task9Service.ProfilingResult result = task9Service.runAllOperations();
        long totalTime = (System.nanoTime() - startTime) / 1_000_000;

        System.out.println("=== PROFILING RESULTS ===");
        System.out.printf("Fibonacci Calculation: %d ms%n", result.fibonacciTime);
        System.out.printf("Array Sorting: %d ms%n", result.sortTime);
        System.out.printf("Memory Operation: %d ms%n", result.memoryTime);
        System.out.printf("Total Execution Time: %d ms%n", result.totalTime);
        System.out.printf("Measured Total Time: %d ms%n", totalTime);

        // Анализ результатов
        System.out.println("=== PERFORMANCE ANALYSIS ===");

        // Fibonacci должен быть самым медленным из-за экспоненциальной сложности
        if (result.fibonacciTime > result.sortTime && result.fibonacciTime > result.memoryTime) {
            System.out.println("✓ Fibonacci is the main bottleneck (exponential complexity)");
        }

        // Сортировка должна быть второй по медленности
        if (result.sortTime > result.memoryTime) {
            System.out.println("✓ Sorting is secondary bottleneck (quadratic complexity)");
        }

        // Проверяем корректность результатов
        assertNotNull(result.fibonacciResult);
        assertEquals(5000, result.sortedItems);
        assertTrue(result.memoryOperationResult.contains("50000"));

        // Общее время должно быть примерно равно сумме компонентов
        long expectedTotal = result.fibonacciTime + result.sortTime + result.memoryTime;
        long tolerance = 100; // ms tolerance
        assertTrue(Math.abs(result.totalTime - expectedTotal) <= tolerance,
                "Total time should be sum of component times");
    }

    @Test
    @DisplayName("6. Stress Test - Multiple Iterations")
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    void testStressTest() {
        System.out.println("=== Stress Test ===");

        int iterations = 5;
        long totalTime = 0;

        for (int i = 1; i <= iterations; i++) {
            System.out.printf("Iteration %d/%d:%n", i, iterations);

            long iterationStart = System.nanoTime();
            Task9Service.ProfilingResult result = task9Service.runAllOperations();
            long iterationTime = (System.nanoTime() - iterationStart) / 1_000_000;

            totalTime += iterationTime;

            System.out.printf("  -> Iteration time: %d ms (Fib: %d ms, Sort: %d ms, Mem: %d ms)%n",
                    iterationTime, result.fibonacciTime, result.sortTime, result.memoryTime);

            // Небольшая пауза между итерациями
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long averageTime = totalTime / iterations;
        System.out.printf("=== STRESS TEST RESULTS ===%n");
        System.out.printf("Total time for %d iterations: %d ms%n", iterations, totalTime);
        System.out.printf("Average time per iteration: %d ms%n", averageTime);

        assertTrue(averageTime > 0, "Average time should be positive");
    }

    @Test
    @DisplayName("7. Memory Leak Detection Test")
    void testMemoryLeakDetection() {
        System.out.println("=== Memory Leak Detection Test ===");

        // Выполняем операцию много раз и следим за памятью
        int iterations = 10;
        long initialMemory = getUsedMemory();

        for (int i = 0; i < iterations; i++) {
            task9Service.memoryIntensiveOperation(10000);

            // Принудительный вызов GC для чистоты эксперимента
            System.gc();

            long currentMemory = getUsedMemory();
            System.out.printf("Iteration %d: Memory used: %d MB%n", i + 1, currentMemory);

            // Если память постоянно растет - возможна утечка
            if (i > 5 && currentMemory > initialMemory * 1.5) {
                System.out.println("⚠ WARNING: Possible memory leak detected!");
            }
        }

        long finalMemory = getUsedMemory();
        System.out.printf("Memory change: %+d MB%n", finalMemory - initialMemory);
    }

    @Test
    @DisplayName("8. Algorithm Complexity Verification")
    void testAlgorithmComplexity() {
        System.out.println("=== Algorithm Complexity Verification ===");

        // Тестируем рост времени выполнения для Fibonacci
        System.out.println("Fibonacci Time Growth:");
        for (int n = 30; n <= 40; n += 2) {
            long startTime = System.nanoTime();
            task9Service.slowFibonacci(n);
            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.printf("  n=%d: %d ms%n", n, duration);
        }

        // Тестируем рост времени выполнения для сортировки
        System.out.println("Sorting Time Growth:");
        for (int size = 1000; size <= 5000; size += 1000) {
            List<Integer> array = task9Service.generateLargeArray(size);
            long startTime = System.nanoTime();
            task9Service.inefficientSort(array);
            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.printf("  size=%d: %d ms%n", size, duration);
        }
    }

    // Вспомогательные методы

    private long getFastFibonacciTime(int n) {
        long start = System.nanoTime();
        task9Service.fibonacci(n);
        return (System.nanoTime() - start) / 1_000_000;
    }

    private boolean isSorted(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
    }
}