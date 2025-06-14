package com.keyin.rest.game;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/game_search")
    public ResponseEntity<List<Game>> searchGames(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "homeTeamId", required = false) Long homeTeamId,
            @RequestParam(value = "awayTeamId", required = false) Long awayTeamId,
            @RequestParam(value = "homeTeamName", required = false) String homeTeamName,
            @RequestParam(value = "awayTeamName", required = false) String awayTeamName) {

        try {
            // Build the map of search terms using your GameSearchCriteria enum keys
            Map<String, Object> searchTerms = new HashMap<>();

            if (location != null && !location.isEmpty()) {
                searchTerms.put(GameSearchCriteria.LOCATION.getKey(), location);
            }
            if (date != null && !date.isEmpty()) {
                searchTerms.put(GameSearchCriteria.DATE.getKey(), date);
            }
            if (homeTeamId != null) {
                searchTerms.put(GameSearchCriteria.HOME_TEAM_ID.getKey(), homeTeamId);
            }
            if (awayTeamId != null) {
                searchTerms.put(GameSearchCriteria.AWAY_TEAM_ID.getKey(), awayTeamId);
            }
            if (homeTeamName != null && !homeTeamName.isEmpty()) {
                searchTerms.put(GameSearchCriteria.HOME_TEAM_NAME.getKey(), homeTeamName);
            }
            if (awayTeamName != null && !awayTeamName.isEmpty()) {
                searchTerms.put(GameSearchCriteria.AWAY_TEAM_NAME.getKey(), awayTeamName);
            }

            if (searchTerms.isEmpty()) {
                return ResponseEntity.ok(gameService.getAllGames());
            }

            return ResponseEntity.ok(gameService.findGamesByCriteria(searchTerms));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
