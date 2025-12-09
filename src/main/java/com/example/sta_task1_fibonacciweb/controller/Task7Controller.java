package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.model.BinarySearchResult;
import com.example.sta_task1_fibonacciweb.model.TestResult;
import com.example.sta_task1_fibonacciweb.service.Task7Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task7")
public class Task7Controller {

    private final Task7Service task7Service;

    public Task7Controller(Task7Service task7Service) {
        this.task7Service = task7Service;
    }

    @GetMapping
    public String showTask7Page(Model model) {
        model.addAttribute("title", "Task 7 - Basis Path Testing");
        model.addAttribute("cyclomaticComplexity", task7Service.calculateCyclomaticComplexity());
        return "task7";
    }

    @PostMapping("/search")
    public String performBinarySearch(
            @RequestParam String arrayInput,
            @RequestParam int target,
            Model model) {

        try {
            int[] arr = parseArray(arrayInput);
            BinarySearchResult result = task7Service.binarySearch(arr, target);

            model.addAttribute("searchResult", result);
            model.addAttribute("arrayInput", arrayInput);
            model.addAttribute("target", target);

        } catch (Exception e) {
            model.addAttribute("error", "Invalid input: " + e.getMessage());
        }

        model.addAttribute("title", "Task 7 - Basis Path Testing");
        model.addAttribute("cyclomaticComplexity", task7Service.calculateCyclomaticComplexity());
        return "task7";
    }

    @PostMapping("/run-tests") // Изменено на POST
    public String runAllTests(Model model) {
        List<TestResult> testResults = task7Service.runAllTests();

        long passedCount = testResults.stream().filter(TestResult::isPassed).count();
        long failedCount = testResults.size() - passedCount;

        model.addAttribute("testResults", testResults);
        model.addAttribute("passedCount", passedCount);
        model.addAttribute("failedCount", failedCount);
        model.addAttribute("totalCount", testResults.size());
        model.addAttribute("title", "Task 7 - Basis Path Testing");
        model.addAttribute("cyclomaticComplexity", task7Service.calculateCyclomaticComplexity());

        return "task7";
    }

    private int[] parseArray(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new int[0];
        }

        String[] parts = input.split("[,\\s]+");
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }

        return result;
    }
}