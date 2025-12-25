package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
    
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/game_search")
    public List<Game> searchGames(
            @RequestParam(value = "team_id", required = false) Long teamId,
            @RequestParam(value = "home_team_id", required = false) Long homeTeamId,
            @RequestParam(value = "away_team_id", required = false) Long awayTeamId,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "start_date", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "end_date", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        if (teamId != null) {
            return gameService.getGamesByTeam(teamId);
        } else if (homeTeamId != null) {
            return gameService.getGamesByHomeTeam(homeTeamId);
        } else if (awayTeamId != null) {
            return gameService.getGamesByAwayTeam(awayTeamId);
        } else if (location != null) {
            return gameService.getGamesByLocation(location);
        } else if (startDate != null && endDate != null) {
            return gameService.getGamesByDateRange(startDate, endDate);
        }
        
        return gameService.getAllGames();
    }
    
    @PostMapping("/game")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        try {
            Game createdGame = gameService.createGame(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the game: " + e.getMessage());
        }
    }
    
    @PutMapping("/game/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game gameDetails) {
        try {
            Game updatedGame = gameService.updateGame(id, gameDetails);
            
            if (updatedGame == null) {
                return ResponseEntity.notFound().build();
            }
            
            return ResponseEntity.ok(updatedGame);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the game: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable Long id) {
        Game game = gameService.getGameById(id);
        
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        
        gameService.deleteGameById(id);
        return ResponseEntity.noContent().build();
    }
} 