package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    // POST endpoint to create a new game
    @PostMapping("/game")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        try {
            Game newGame = gameService.createGame(game);
            return ResponseEntity.ok(newGame);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST endpoint to create games in bulk
    // Example input JSON for POST request to create multiple games:
    //    [
    //        {
    //            "homeTeam": { "name": "Dragons" },
    //            "awayTeam": { "id": 2 },
    //            "location": "Arena A",
    //            "scheduledDate": "2025-07-15T18:30:00"
    //        },
    //        {
    //            "homeTeam": { "id": 3 },
    //            "awayTeam": { "name": "Tigers" },
    //            "location": "Arena B",
    //            "scheduledDate": "2025-07-16T19:00:00"
    //        }
    //    ]
    @PostMapping("/games")
    public ResponseEntity<?> createGames(@RequestBody List<Game> games) {
        try {
            List<Game> newGames = new ArrayList<>(games.size());
            for (Game game : games) {
                Game newGame = gameService.createGame(game);
                newGames.add(newGame);
            }
            return ResponseEntity.ok(newGames);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET endpoint to retrieve all games
    @GetMapping("/games")
    public ResponseEntity<?> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }
}
