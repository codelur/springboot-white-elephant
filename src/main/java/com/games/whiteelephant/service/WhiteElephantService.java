package com.games.whiteelephant.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.games.whiteelephant.model.Game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WhiteElephantService {


    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Integer> generateRandomList(int n) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers, new Random()); 

        return numbers;
    }

    public List<Game> getWhiteElephantGames(){

        try {
            // Load JSON file from resources
            return objectMapper.readValue(
                new ClassPathResource("data/white-elephant-games.json").getInputStream(),
                    new TypeReference<List<Game>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list if reading fails
        }
    }

    public Game getWhiteElephantGameList(String gameName){
        List<Game> games = this.getWhiteElephantGames();
        return games.stream().filter(game-> game.getName().equals(gameName))
        .findFirst().orElse(null);
    }

    public boolean gameExists(String name) {
        List<Game> games = this.getWhiteElephantGames();
        return games.stream()
        .anyMatch(game -> game.getName().equalsIgnoreCase(name));
    }

    
}