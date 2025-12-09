package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task6Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task6")
public class Task6Controller {

    @Autowired
    private Task6Service task6Service;

    // Default array
    private final int[] DEFAULT_ARRAY = {1, 8, 2, -3, 10, 9, -20, -100, 0, 3, 4};

    @GetMapping
    public String task6Page(Model model) {
        setupModelForPage(model);
        return "task6";
    }

    @PostMapping("/sum")
    public String calculateSum(
            @RequestParam("arrayInput") String arrayInput,
            @RequestParam("iteration") int iteration,
            Model model) {

        try {
            int[] array = parseArray(arrayInput);
            String result = task6Service.calculateSumPositiveIterative(array, iteration);
            model.addAttribute("sumResult", result);
            model.addAttribute("arrayInput", arrayInput);
            model.addAttribute("iteration", iteration);
        } catch (Exception e) {
            model.addAttribute("sumError", "Error: " + e.getMessage());
        }

        setupModelForPage(model);
        return "task6";
    }

    @PostMapping("/search")
    public String binarySearch(
            @RequestParam("arrayInput") String arrayInput,
            @RequestParam("key") int key,
            @RequestParam("iteration") int iteration,
            Model model) {

        try {
            int[] array = parseArray(arrayInput);
            String result = task6Service.binarySearchIterative(array, key, iteration);
            model.addAttribute("searchResult", result);
            model.addAttribute("arrayInput", arrayInput);
            model.addAttribute("key", key);
            model.addAttribute("iteration", iteration);
        } catch (Exception e) {
            model.addAttribute("searchError", "Error: " + e.getMessage());
        }

        setupModelForPage(model);
        return "task6";
    }

    @PostMapping("/sort")
    public String bubbleSort(
            @RequestParam("arrayInput") String arrayInput,
            @RequestParam("outerLimit") int outerLimit,
            @RequestParam("innerLimit") int innerLimit,
            Model model) {

        try {
            int[] array = parseArray(arrayInput);
            String result = task6Service.bubbleSortIterative(array, outerLimit, innerLimit);
            model.addAttribute("sortResult", result);
            model.addAttribute("arrayInput", arrayInput);
            model.addAttribute("outerLimit", outerLimit);
            model.addAttribute("innerLimit", innerLimit);
        } catch (Exception e) {
            model.addAttribute("sortError", "Error: " + e.getMessage());
        }

        setupModelForPage(model);
        return "task6";
    }

    private void setupModelForPage(Model model) {
        model.addAttribute("title", "Task 6 - Array Operations");
        model.addAttribute("defaultArray", DEFAULT_ARRAY);
        model.addAttribute("arrayString", arrayToString(DEFAULT_ARRAY));
    }

    private int[] parseArray(String arrayInput) {
        String[] elements = arrayInput.split(",");
        int[] array = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            array[i] = Integer.parseInt(elements[i].trim());
        }
        return array;
    }

    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}