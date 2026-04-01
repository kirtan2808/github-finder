package com.githubfinder.githubfinder.controller;

import com.githubfinder.githubfinder.entity.User;
import com.githubfinder.githubfinder.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        repo.save(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {

        User u = repo.findByUsername(user.getUsername());

        if (u != null && u.getPassword().equals(user.getPassword())) {
            session.setAttribute("name", u.getName());
            session.setAttribute("username", u.getUsername());

            return "home"; // 🔥 CHANGE HERE
        }

        model.addAttribute("error", "Invalid login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}