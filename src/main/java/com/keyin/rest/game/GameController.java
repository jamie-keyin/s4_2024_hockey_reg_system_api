package com.keyin.rest.game;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionRepository;
import com.keyin.rest.player.Player;
import com.keyin.rest.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public List<Game> getAllGames() {return  gameService.getAllGames();}

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getTeamById(@PathVariable long id) {
        Game gameToReturn = gameService.getGameById(id);

        if(gameToReturn == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gameToReturn);
    }

    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable long id, @RequestBody Game game) {
        return ResponseEntity.ok(gameService.updateGame(id, game));
    }

    @DeleteMapping("/game/{id}")
    public void deleteGameById(@PathVariable long id) {gameService.deleteGameByID(id);}
}
