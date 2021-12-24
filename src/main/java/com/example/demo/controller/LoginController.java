package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        return "/login";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser,
                          HttpServletResponse response, HttpServletRequest request) {
        if (user.getEmail().equals("a@a.com") && user.getPassword().equals("1")) {
            setUser = user.getEmail();
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(700);
            response.addCookie(cookie);
            Cookie[] cookies = request.getCookies();
            for (Cookie ck : cookies) {
                if (ck.getName().equals("setUser")) {
                    model.addAttribute("cookieValue", ck);
                    break;
                } else {
                    ck.setValue("");
                    model.addAttribute("cookieValue", ck);
                    break;
                }
            }
            model.addAttribute("message", "Login success. Welcome ");
        } else {
            user.setEmail("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "/login";
    }
}
