package com.games.whiteelephant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.games.whiteelephant.model.Game;
import com.games.whiteelephant.service.WhiteElephantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/games/white-elephant")
public class GamesController {

    @Autowired
    WhiteElephantService whiteElephantService;

    @GetMapping("all")
    public ResponseEntity<List<Game>>  getWhiteElephantGames() {
        List<Game> games = whiteElephantService.getWhiteElephantGames();
        return ResponseEntity.ok(games);
    }

    
}
