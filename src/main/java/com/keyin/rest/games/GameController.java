package com.keyin.rest.games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin

public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping("/games")
    public ResponseEntity<?> createGame(@RequestBody Game newGame) {

        try {
            Game createdGame = gameService.createGame(newGame);
            return ResponseEntity.ok(createdGame);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}