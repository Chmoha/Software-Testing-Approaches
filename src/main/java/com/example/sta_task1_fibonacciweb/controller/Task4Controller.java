package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task4Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/task4")
public class Task4Controller {

    private final Task4Service service;

    public Task4Controller(Task4Service service) {
        this.service = service;
    }

    @GetMapping
    public String showForm() {
        return "task4";
    }

    private static final int MIN_VALUE = -1_000_000;
    private static final int MAX_VALUE = 1_000_000;

    @PostMapping("/search")
    public String search(
            @RequestParam String arrayInput,
            @RequestParam String key,
            Model model
    ) {
        model.addAttribute("array", arrayInput);
        model.addAttribute("key", key);

        try {
            int keyInt;
            try {
                keyInt = Integer.parseInt(key.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("⚠️ Key must be an integer!");
            }

            int[] arr = parseArray(arrayInput);

            int index = service.searchIndexOriginalArray(arr, keyInt);
            boolean found = service.binarySearchLogic(arr, keyInt); // проверка логики

            if (index != -1) {
                model.addAttribute("result", "✅ Element found at index: " + index + " (L=" + (index+1) + ")");
            } else {
                model.addAttribute("result", "❌ Element not found.");
            }

        } catch (IllegalArgumentException e) {
            model.addAttribute("result", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("result", "⚠️ Invalid input format! Please enter integers separated by commas.");
        }

        return "task4";
    }


    private int[] parseArray(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("⚠️ Array cannot be empty!");
        }

        String[] parts = input.split(",");
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            String token = parts[i].trim();

            // Проверяем что это число и не выходит за пределы int
            long longValue;
            try {
                longValue = Long.parseLong(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "⚠️ Invalid array element: \"" + token + "\". Must be an integer within -1,000,000 to 1,000,000!"
                );
            }

            if (longValue < MIN_VALUE || longValue > MAX_VALUE) {
                throw new IllegalArgumentException(
                        "⚠️ Array elements must be between -1,000,000 and 1,000,000! Invalid value: " + longValue
                );
            }

            numbers[i] = (int) longValue;
        }

        return numbers;
    }


}
