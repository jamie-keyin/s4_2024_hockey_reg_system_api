package com.keyin.rest.game;

import com.keyin.rest.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable long id) {
        return gameService.getGameById(id);
    }

    @PostMapping("/games")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }
}
