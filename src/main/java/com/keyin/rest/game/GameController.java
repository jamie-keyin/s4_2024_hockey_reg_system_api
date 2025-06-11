package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    // Attributes
    @Autowired
    private GameService gameService;

    // Methods

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }
}
