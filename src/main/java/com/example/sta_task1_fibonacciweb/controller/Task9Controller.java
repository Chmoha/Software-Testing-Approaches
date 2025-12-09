package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task9Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task9")
public class Task9Controller {

    @Autowired
    private Task9Service task9Service;

    @GetMapping
    public String task9Page(Model model) {
        model.addAttribute("title", "Task 9 - Profiling");
        return "task9";
    }

    // Быстрый Fibonacci
    @PostMapping("/fibonacci/fast")
    public String fastFibonacci(@RequestParam int n, Model model) {
        try {
            long startTime = System.currentTimeMillis();
            BigInteger result = task9Service.fibonacci(n);
            long executionTime = System.currentTimeMillis() - startTime;

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("fastFibResult", result);
            model.addAttribute("fastFibTime", executionTime);
            model.addAttribute("fastFibInput", n);

        } catch (IllegalArgumentException e) {
            model.addAttribute("fastFibError", e.getMessage());
        }
        return "task9";
    }

    // Медленный Fibonacci (для профилирования)
    @PostMapping("/fibonacci/slow")
    public String slowFibonacci(@RequestParam int n, Model model) {
        try {
            long startTime = System.currentTimeMillis();
            BigInteger result = task9Service.slowFibonacci(n);
            long executionTime = System.currentTimeMillis() - startTime;

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("slowFibResult", result);
            model.addAttribute("slowFibTime", executionTime);
            model.addAttribute("slowFibInput", n);

        } catch (Exception e) {
            model.addAttribute("slowFibError", "Error: " + e.getMessage());
        }
        return "task9";
    }

    // Неэффективная сортировка
    @PostMapping("/sort/inefficient")
    public String inefficientSort(@RequestParam String arrayInput, Model model) {
        try {
            // Парсинг входного массива
            List<Integer> array = parseArray(arrayInput);

            long startTime = System.currentTimeMillis();
            List<Integer> result = task9Service.inefficientSort(array);
            long executionTime = System.currentTimeMillis() - startTime;

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("sortResult", result);
            model.addAttribute("sortTime", executionTime);
            model.addAttribute("sortInput", arrayInput);
            model.addAttribute("arraySize", array.size());

        } catch (Exception e) {
            model.addAttribute("sortError", "Error: " + e.getMessage());
        }
        return "task9";
    }

    // Генерация и сортировка большого массива
    @PostMapping("/sort/large")
    public String sortLargeArray(@RequestParam int size, Model model) {
        try {
            long startTime = System.currentTimeMillis();
            List<Integer> array = task9Service.generateLargeArray(size);
            List<Integer> sorted = task9Service.inefficientSort(array);
            long executionTime = System.currentTimeMillis() - startTime;

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("largeSortResult", "Sorted " + sorted.size() + " elements");
            model.addAttribute("largeSortTime", executionTime);
            model.addAttribute("largeSortSize", size);

        } catch (Exception e) {
            model.addAttribute("largeSortError", "Error: " + e.getMessage());
        }
        return "task9";
    }

    // Операция с памятью
    @PostMapping("/memory")
    public String memoryIntensive(@RequestParam int count, Model model) {
        try {
            long startTime = System.currentTimeMillis();
            String result = task9Service.memoryIntensiveOperation(count);
            long executionTime = System.currentTimeMillis() - startTime;

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("memoryResult", result);
            model.addAttribute("memoryTime", executionTime);
            model.addAttribute("memoryCount", count);

        } catch (Exception e) {
            model.addAttribute("memoryError", "Error: " + e.getMessage());
        }
        return "task9";
    }

    // Запуск всех операций для профилирования
    @PostMapping("/run-all")
    public String runAllOperations(Model model) {
        try {
            Task9Service.ProfilingResult result = task9Service.runAllOperations();

            model.addAttribute("title", "Task 9 - Profiling");
            model.addAttribute("profilingResult", result);

        } catch (Exception e) {
            model.addAttribute("profilingError", "Error: " + e.getMessage());
        }
        return "task9";
    }

    // Вспомогательный метод для парсинга массива
    private List<Integer> parseArray(String input) {
        List<Integer> result = new ArrayList<>();
        String[] parts = input.split("[,\\s]+");
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                result.add(Integer.parseInt(part.trim()));
            }
        }
        return result;
    }
}