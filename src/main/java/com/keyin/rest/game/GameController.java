package com.keyin.rest.game;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@Tag(name = "Game API", description = "Endpoints for managing hockey games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Operation(summary = "Get all games", description = "Returns a list of all scheduled hockey games")
    @GetMapping
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    @Operation(summary = "Get a game by ID", description = "Retrieve a game by its unique ID")
    @GetMapping("/{id}")
    public Game getGameById(
            @Parameter(description = "ID of the game to retrieve") @PathVariable Long id
    ) {
        return gameRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Create a new game", description = "Add a new scheduled hockey game")
    @PostMapping
    public Game createGame(
            @Parameter(description = "Game object to be created") @RequestBody Game game
    ) {
        return gameRepository.save(game);
    }

    @Operation(summary = "Update an existing game", description = "Update the details of an existing game")
    @PutMapping("/{id}")
    public Game updateGame(
            @Parameter(description = "ID of the game to update") @PathVariable Long id,
            @Parameter(description = "Updated game object") @RequestBody Game updatedGame
    ) {
        return gameRepository.findById(id).map(existingGame -> {
            existingGame.setHomeTeam(updatedGame.getHomeTeam());
            existingGame.setAwayTeam(updatedGame.getAwayTeam());
            existingGame.setLocation(updatedGame.getLocation());
            existingGame.setScheduledDate(updatedGame.getScheduledDate());
            return gameRepository.save(existingGame);
        }).orElse(null);
    }

    @Operation(summary = "Delete a game", description = "Remove a game by its ID")
    @DeleteMapping("/{id}")
    public void deleteGame(
            @Parameter(description = "ID of the game to delete") @PathVariable Long id
    ) {
        gameRepository.deleteById(id);
    }
}
