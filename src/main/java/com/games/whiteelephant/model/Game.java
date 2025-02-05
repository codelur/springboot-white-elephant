package com.games.whiteelephant.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    private String name;
    private int players;
    private List<Integer> list;
    
    // Default constructor (Jackson requires it)
    public Game() {}

    public Game(String name, int players, List<Integer> list) {
        this.name = name;
        this.players = players;
        this.list = list;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPlayers() {
        return players;
    }
    public void setPlayers(int players) {
        this.players = players;
    }
    public List<Integer> getList() {
        return list;
    }
    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Game{name='" + name + "', players=" + players + ", list=" + list + "}";
    }
    
    
}
