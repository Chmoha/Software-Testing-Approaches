package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Task3Controller {

    private final Task3Service task3Service;

    public Task3Controller(Task3Service task3Service) {
        this.task3Service = task3Service;
    }

    @GetMapping("/task3")
    public String task3(Model model) {
        model.addAttribute("title", "Task 3");
        model.addAttribute("a", "");
        model.addAttribute("b", "");
        model.addAttribute("c", "");
        model.addAttribute("accuracy", 4);
        return "task3";
    }

    @PostMapping("/task3/solve")
    public String solve(@RequestParam String a,
                        @RequestParam String b,
                        @RequestParam String c,
                        @RequestParam(defaultValue = "4") int accuracy,
                        Model model) {
        model.addAttribute("title", "Task 3");
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);
        model.addAttribute("accuracy", accuracy);

        try {
            double da = Double.parseDouble(a.trim());
            double db = Double.parseDouble(b.trim());
            double dc = Double.parseDouble(c.trim());

            // диапазон коэффициентов [-1e9; 1e9]
            double MIN = -1e9;
            double MAX = 1e9;

            if (da < MIN || da > MAX || db < MIN || db > MAX || dc < MIN || dc > MAX) {
                model.addAttribute("error", "Ошибка: коэффициенты должны быть в диапазоне от -1e9 до 1e9!");
                return "task3";
            }

            if (accuracy < 1) accuracy = 1;
            if (accuracy > 10) accuracy = 10;

            model.addAttribute("result", task3Service.solve(da, db, dc, accuracy));

        } catch (NumberFormatException ex) {
            model.addAttribute("error", "Ошибка: введите корректные числовые значения!");
        }

        return "task3";
    }


    @PostMapping("/task3/random")
    public String random(@RequestParam(defaultValue = "4") int accuracy, Model model) {
        model.addAttribute("title", "Task 3");
        model.addAttribute("accuracy", accuracy);
        double[] abc = task3Service.randomABC();
        model.addAttribute("a", abc[0]);
        model.addAttribute("b", abc[1]);
        model.addAttribute("c", abc[2]);
        model.addAttribute("result", task3Service.solve(abc[0], abc[1], abc[2], accuracy));
        return "task3";
    }
}
