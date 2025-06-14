//Description: THe controller for Game. Basic functionality prescribed in assignment with added
//             routes found in other object controllers: additional GET calls, PUT and DELETE
//Author: DC Elliott
//Date: Jun 13/2025

package com.keyin.rest.game;

import com.keyin.rest.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    @Autowired
    private GameService gameService;


    //The general GET returns a DTO object to clean up the list of multiple games.
    @GetMapping("/game")
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames()
                .stream()
                .map(GameDTO::new)
                .toList();
    }

    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable long id) {
        return gameService.getGameById(id);
    }

    @GetMapping("/game_search")
    public List<Game> searchGamesByLocation(@RequestParam String location) {
        return gameService.getGamesByLocation(location);
    }


    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }
    @PutMapping("/game/{id}")
    public Game updateGame(@PathVariable long id, @RequestBody Game game) {
        return gameService.updateGame(id, game);
    }

    @DeleteMapping("/game/{id}")
    public void deleteGame(@PathVariable long id) {
        gameService.deleteGame(id);
    }
}

