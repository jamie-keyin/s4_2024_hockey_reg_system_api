package com.keyin.rest.game;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final List<Game> games = new ArrayList<>();

    @GetMapping
    public List<Game> getAllGames() {
        return games;
    }

    @PostMapping
    public Game createGame(@RequestBody Game newGame) {
        games.add(newGame);
        return newGame;
    }
}
