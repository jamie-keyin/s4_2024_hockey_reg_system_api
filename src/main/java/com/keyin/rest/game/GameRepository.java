package com.keyin.rest.game;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {
    private List<Game> games = new ArrayList<>();

    public List<Game> findAll() {
        return games;
    }

    public void save(Game game) {
        game.setId(System.currentTimeMillis());
        games.add(game);
    }
}