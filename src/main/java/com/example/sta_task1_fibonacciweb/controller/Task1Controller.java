package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task1Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Task1Controller {

    private final Task1Service task1Service;

    public Task1Controller(Task1Service task1Service) {
        this.task1Service = task1Service;
    }

    @GetMapping("/task1")
    public String task1(Model model) {
        model.addAttribute("title", "Task 1");
        model.addAttribute("a", "");
        model.addAttribute("b", "");
        model.addAttribute("c", "");
        return "task1";
    }

    @PostMapping("/task1/solve")
    public String solve(@RequestParam String a,
                        @RequestParam String b,
                        @RequestParam String c,
                        Model model) {
        model.addAttribute("title", "Task 1");
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);

        try {
            double da = parseInput(a);
            double db = parseInput(b);
            double dc = parseInput(c);

            if (Math.abs(da) > 1_000_000_000 ||
                    Math.abs(db) > 1_000_000_000 ||
                    Math.abs(dc) > 1_000_000_000) {
                model.addAttribute("error", "Число слишком большое (допустимый диапазон: -1,000,000,000 … 1,000,000,000)");
                return "task1";
            }

            model.addAttribute("result", task1Service.solve(da, db, dc));
        } catch (NumberFormatException ex) {
            model.addAttribute("error", "Ошибка: введите число, либо 'pi' или 'e'");
        }

        return "task1";
    }

    @PostMapping("/task1/random")
    public String random(Model model) {
        model.addAttribute("title", "Task 1");
        double[] abc = task1Service.randomABC();
        model.addAttribute("a", abc[0]);
        model.addAttribute("b", abc[1]);
        model.addAttribute("c", abc[2]);

        // сразу решаем, чтобы пользователь видел ответ
        model.addAttribute("result", task1Service.solve(abc[0], abc[1], abc[2]));
        return "task1";
    }

    private double parseInput(String input) {
        String normalized = input.trim().toLowerCase();
        switch (normalized) {
            case "pi": return Math.round(Math.PI * 100.0) / 100.0; // 3.14
            case "e": return Math.round(Math.E * 100.0) / 100.0;   // 2.72
            default: return Double.parseDouble(normalized);
        }
    }
}
