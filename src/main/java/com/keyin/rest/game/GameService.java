package com.keyin.rest.game;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private List<Game> games = new ArrayList<>();

    public List<Game> getAllGames() {
        return games;
    }

    public Game addGame(Game game) {
        game.setId(System.currentTimeMillis()); // generate ID
        games.add(game);
        return game;
    }
}