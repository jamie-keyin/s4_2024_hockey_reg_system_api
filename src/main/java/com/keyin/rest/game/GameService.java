package com.keyin.rest.game;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository repo;

    public List<Game> findAll() {
        return repo.findAll();
    }

    public Game save(Game game) {
        return repo.save(game);
    }
}
