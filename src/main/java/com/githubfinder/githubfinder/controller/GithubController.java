package com.githubfinder.githubfinder.controller;

import com.githubfinder.githubfinder.model.GithubUser;
import com.githubfinder.githubfinder.entity.SearchHistory;
import com.githubfinder.githubfinder.repository.SearchHistoryRepository;
import com.githubfinder.githubfinder.service.GithubService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GithubController {

    @Autowired
    private GithubService service;

    @Autowired
    private SearchHistoryRepository historyRepo;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {

        if (session.getAttribute("username") == null) {
            return "login";
        }

        String currentUser = (String) session.getAttribute("username");

        // 🔥 ONLY LAST 5
        List<SearchHistory> historyList =
                historyRepo.findTop5ByUsernameOrderByIdDesc(currentUser);

        model.addAttribute("history", historyList);
        model.addAttribute("count", historyList.size());

        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String username, Model model, HttpSession session) {

        GithubUser user = service.getUser(username);

        if (user == null || user.getLogin() == null) {
            model.addAttribute("error", "User not found!");
            return "index";
        }

        String currentUser = (String) session.getAttribute("username");

        // SAVE
        SearchHistory history = new SearchHistory();
        history.setUsername(currentUser);
        history.setSearchedUser(username);
        historyRepo.save(history);

        // 🔥 FETCH ONLY LAST 5
        List<SearchHistory> historyList =
                historyRepo.findTop5ByUsernameOrderByIdDesc(currentUser);

        model.addAttribute("user", user);

        List<String> repos = service.getRepos(username);
        model.addAttribute("repos", repos);

        model.addAttribute("history", historyList);
        model.addAttribute("count", historyList.size());
        return "result";
    }

    // 🔥 DELETE SINGLE HISTORY
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        historyRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(HttpSession session) {

        String currentUser = (String) session.getAttribute("username");

        if (currentUser == null) {
            return "redirect:/login";
        }

        historyRepo.deleteAllByUsername(currentUser);

        return "redirect:/";
    }
}