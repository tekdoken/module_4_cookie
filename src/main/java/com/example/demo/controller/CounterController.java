package com.example.demo.controller;

import com.example.demo.model.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("counter")
public class CounterController  {
    @ModelAttribute("counter")
    public Counter setUpCounter() {
        return new Counter();
    }

    @GetMapping("")
    public String get(@ModelAttribute("counter") Counter counter, HttpServletRequest request) {
        counter.increment();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3);
        return "/counter";
    }


}
