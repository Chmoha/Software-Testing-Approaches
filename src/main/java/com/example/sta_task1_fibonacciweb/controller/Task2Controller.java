// Task2 - Fibonacci
package com.example.sta_task1_fibonacciweb.controller;

import com.example.sta_task1_fibonacciweb.service.Task2Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Task2Controller {

    private final Task2Service fibService;

    public Task2Controller(Task2Service fibService) {
        this.fibService = fibService;
    }

    @GetMapping("/task2")
    public String task2(Model model) {
        model.addAttribute("title", "Task 2 - Fibonacci");
        model.addAttribute("n", "");
        return "task2";
    }

    @PostMapping("/task2/calc")
    public String calculate(@RequestParam("n") int n, Model model) {
        try {
            var values = fibService.fibonacciWithPrevious(n);
            model.addAttribute("prev2", values[0]);
            model.addAttribute("prev1", values[1]);
            model.addAttribute("result", values[2]);
            model.addAttribute("n", n);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("n", n);
        }
        return "task2";
    }
}
