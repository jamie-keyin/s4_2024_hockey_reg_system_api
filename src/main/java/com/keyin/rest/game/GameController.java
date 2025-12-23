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

    @GetMapping("/game")
    public List<Game> getAllGames() {return gameService.getAllGames();}

    @GetMapping("game_search")
    public List<Game> getGamesBySearchCriteria(@RequestParam(value = "home_team_name", required = false) String homeTeamName,
                                               @RequestParam(value = "away_team_name", required = false) String awayTeamName,
                                               @RequestParam(value = "location", required = false) String location) {
        List<Game> results = new ArrayList<Game>();
        if (homeTeamName != null) {results = gameService.getGamesByHomeTeamName(homeTeamName);}
        else if (awayTeamName != null) {results = gameService.getGamesByAwayTeamName(awayTeamName);}
        else if (location != null) {results = gameService.getGamesByLocation(location);}
        return results;}

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable long id) {
        Game gameToReturn = gameService.getGameByID(id);
        if (gameToReturn == null) {return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(gameToReturn);}

    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        System.out.println("Creating game at: " + game.getLocation());
        return gameService.createGame(game);}

    @PutMapping("/game/{id}")
    public ResponseEntity<Game> updateGameByID(@PathVariable long id, @RequestBody Game game) {
        return ResponseEntity.ok(gameService.updateGameByID(id, game));}

    @DeleteMapping("/game/{id}")
    public void deleteGameByID(@PathVariable long id) {gameService.deleteGameByID(id);}
} 