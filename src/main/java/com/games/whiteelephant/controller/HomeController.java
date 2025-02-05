package com.games.whiteelephant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.games.whiteelephant.model.Game;
import com.games.whiteelephant.service.WhiteElephantService;

@Controller
public class HomeController {

    @Autowired
    private WhiteElephantService whiteElephantService;
    
    @GetMapping("/")
    public String home() {
        return "index";  // Looks for index.html in src/main/resources/templates/
    }

    @GetMapping("/main")
    public String main() {
        return "index";  // Looks for index.html in src/main/resources/templates/
    }

    @GetMapping("/randomNumbers")
    public String  generateWhiteElephantList(@RequestParam int n,@RequestParam String gameName, Model model){
        boolean gameExists = whiteElephantService.gameExists(gameName);
        if(!gameExists){
        List<Integer> randomNumbers = whiteElephantService.generateRandomList(n);
        model.addAttribute("gameList", randomNumbers);}
        else{
            model.addAttribute("gameExists", true);
        }
        return "index"; 
    }

    @GetMapping("select-game")
    public String  getWhiteElephantList(@RequestParam String selectedGame, Model model) {
        Game game = whiteElephantService.getWhiteElephantGameList(selectedGame);
        model.addAttribute("gameList", game.getList());
        return "index";
    }
}