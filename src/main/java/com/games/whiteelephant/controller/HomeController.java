package com.games.whiteelephant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.games.whiteelephant.service.WhiteElephantService;

@Controller
public class HomeController {

    @Autowired
    private WhiteElephantService WhiteElephantService;
    
    @GetMapping("/")
    public String home() {
        return "index";  // Looks for index.html in src/main/resources/templates/
    }

    @GetMapping("/main")
    public String main() {
        return "index";  // Looks for index.html in src/main/resources/templates/
    }

    @GetMapping("/randomNumbers")
    public String  generateWhiteElephantList(@RequestParam int n, Model model){
        List<Integer> randomNumbers = WhiteElephantService.generateRandomList(n);
        model.addAttribute("numbers", randomNumbers);
        return "index"; 
    }
}