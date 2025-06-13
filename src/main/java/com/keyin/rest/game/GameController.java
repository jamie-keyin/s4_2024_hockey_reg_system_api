package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Defines API endpoints for Game operations.
 */
@RestController
@CrossOrigin // Enables frontend access from different origins
public class GameController {
    @Autowired private GameService gameService;

    /**
     * Retrieves all games.
     */
    @GetMapping("/games") // HTTP GET request to return all stored games
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    /**
     * Creates a new game and returns the result.
     * Allows adding new games via JSON requests.
     */
    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        try {
            return ResponseEntity.ok(gameService.createGame(game));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Returns 400 error if invalid input
        }
    }
}
