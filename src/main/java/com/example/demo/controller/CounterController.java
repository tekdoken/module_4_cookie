package com.example.demo.controller;

import com.example.demo.model.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("counter")
public class CounterController {
    @ModelAttribute("counter")
    public Counter setUpCounter() {
        return new Counter();
    }

    @GetMapping("")
    public String get(@ModelAttribute("counter") Counter counter, Model model) {
        counter.increment();
        return "/counter";
    }
}
