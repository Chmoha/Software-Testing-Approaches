package com.example.sta_task1_fibonacciweb.service;

import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class Task9Service {

    private static final int MAX_N = 100;
    private final Random random = new Random();

    // Оптимизированный метод Fibonacci
    public BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        if (n > MAX_N) {
            throw new IllegalArgumentException("Input must not exceed " + MAX_N);
        }

        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
        }
        return b;
    }

    // Медленный метод Fibonacci для профилирования
    public BigInteger slowFibonacci(int n) {
        if (n <= 1) return BigInteger.valueOf(n);
        return slowFibonacci(n - 1).add(slowFibonacci(n - 2));
    }

    // Неэффективная сортировка пузырьком
    public List<Integer> inefficientSort(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - 1; j++) {
                if (result.get(j) > result.get(j + 1)) {
                    int temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    // Операция, интенсивно использующая память
    public String memoryIntensiveOperation(int count) {
        List<String> bigList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 100; j++) {
                sb.append("data_").append(i).append("_").append(j);
            }
            bigList.add(sb.toString());
        }
        return "Created " + bigList.size() + " objects";
    }

    // Генерация большого массива для сортировки
    public List<Integer> generateLargeArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add(random.nextInt(10000));
        }
        return array;
    }

    // Комплексная операция для профилирования
    public ProfilingResult runAllOperations() {
        long startTime = System.currentTimeMillis();

        // 1. Медленный Fibonacci
        long fibStart = System.currentTimeMillis();
        BigInteger fibResult = slowFibonacci(35);
        long fibTime = System.currentTimeMillis() - fibStart;

        // 2. Неэффективная сортировка
        long sortStart = System.currentTimeMillis();
        List<Integer> largeArray = generateLargeArray(5000);
        List<Integer> sorted = inefficientSort(largeArray);
        long sortTime = System.currentTimeMillis() - sortStart;

        // 3. Операция с памятью
        long memoryStart = System.currentTimeMillis();
        String memoryResult = memoryIntensiveOperation(50000);
        long memoryTime = System.currentTimeMillis() - memoryStart;

        long totalTime = System.currentTimeMillis() - startTime;

        return new ProfilingResult(fibResult, sorted.size(), memoryResult, fibTime, sortTime, memoryTime, totalTime);
    }

    public static class ProfilingResult {
        public final BigInteger fibonacciResult;
        public final int sortedItems;
        public final String memoryOperationResult;
        public final long fibonacciTime;
        public final long sortTime;
        public final long memoryTime;
        public final long totalTime;

        public ProfilingResult(BigInteger fibonacciResult, int sortedItems, String memoryOperationResult,
                               long fibonacciTime, long sortTime, long memoryTime, long totalTime) {
            this.fibonacciResult = fibonacciResult;
            this.sortedItems = sortedItems;
            this.memoryOperationResult = memoryOperationResult;
            this.fibonacciTime = fibonacciTime;
            this.sortTime = sortTime;
            this.memoryTime = memoryTime;
            this.totalTime = totalTime;
        }
    }
}