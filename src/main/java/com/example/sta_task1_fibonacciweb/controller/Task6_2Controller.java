package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task6_2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task6_2")
public class Task6_2Controller {

    @Autowired
    private Task6_2Service task6_2Service;

    // Default 10x10 matrix
    private final int[][] DEFAULT_MATRIX = {
            {1, 8, 2, -3, 10, 9, -20, -100, 0, 3},
            {4, -5, 6, 7, -8, 11, 12, -13, 14, 15},
            {-1, 2, -3, 4, -5, 6, -7, 8, -9, 10},
            {11, -12, 13, -14, 15, -16, 17, -18, 19, -20},
            {21, 22, -23, 24, -25, 26, -27, 28, -29, 30},
            {-31, 32, -33, 34, -35, 36, -37, 38, -39, 40},
            {41, -42, 43, -44, 45, -46, 47, -48, 49, -50},
            {51, 52, 53, -54, 55, -56, 57, -58, 59, -60},
            {-61, 62, -63, 64, -65, 66, -67, 68, -69, 70},
            {71, -72, 73, -74, 75, -76, 77, -78, 79, -80}
    };

    @GetMapping
    public String task6_2Page(Model model) {
        setupModelForPage(model);
        return "task6_2";
    }

    @PostMapping("/sort")
    public String sortMatrix(
            @RequestParam("matrixInput") String matrixInput,
            @RequestParam("outerLimit") int outerLimit,
            @RequestParam("innerLimit") int innerLimit,
            Model model) {

        try {
            int[][] matrix = parseMatrix(matrixInput);
            String result = task6_2Service.sortMatrixIterative(matrix, outerLimit, innerLimit);
            model.addAttribute("sortResult", result);
            model.addAttribute("matrixInput", matrixInput);
            model.addAttribute("outerLimit", outerLimit);
            model.addAttribute("innerLimit", innerLimit);
        } catch (Exception e) {
            model.addAttribute("sortError", "Error: " + e.getMessage());
        }

        setupModelForPage(model);
        return "task6_2";
    }

    @PostMapping("/sum")
    public String sumMatrix(
            @RequestParam("matrixInput") String matrixInput,
            @RequestParam("outerLimit") int outerLimit,
            @RequestParam("innerLimit") int innerLimit,
            Model model) {

        try {
            int[][] matrix = parseMatrix(matrixInput);
            Task6_2Service.SumResult result = task6_2Service.sumPositiveElementsIterative(matrix, outerLimit, innerLimit);
            model.addAttribute("sumResult", result.getResultText());
            model.addAttribute("highlightedCells", result.getHighlightedCells());
            model.addAttribute("matrixInput", matrixInput);
            model.addAttribute("outerLimit", outerLimit);
            model.addAttribute("innerLimit", innerLimit);
        } catch (Exception e) {
            model.addAttribute("sumError", "Error: " + e.getMessage());
        }

        setupModelForPage(model);
        return "task6_2";
    }

    private void setupModelForPage(Model model) {
        model.addAttribute("title", "Task 6.2 - Matrix Operations");
        model.addAttribute("defaultMatrix", DEFAULT_MATRIX);
        model.addAttribute("matrixString", matrixToString(DEFAULT_MATRIX));
    }

    private int[][] parseMatrix(String matrixInput) {
        String[] rows = matrixInput.split(";");
        int rowCount = rows.length;
        int colCount = rows[0].split(",").length;

        int[][] matrix = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            String[] cols = rows[i].split(",");
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = Integer.parseInt(cols[j].trim());
            }
        }
        return matrix;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    sb.append(",");
                }
            }
            if (i < matrix.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}