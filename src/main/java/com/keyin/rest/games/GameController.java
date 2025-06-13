package com.keyin.rest.games;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // create a new game
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game saved = gameService.createGame(game);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // return games stored
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

}
