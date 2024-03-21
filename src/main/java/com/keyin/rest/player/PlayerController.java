package com.keyin.rest.player;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // Endpoint to retrieve all players
    @GetMapping("/player")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // Endpoint to retrieve a player by ID
    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable long id) {
        return playerService.getPlayerById(id);
    }

    // Endpoint to create a new player
    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // Endpoint to update an existing player
    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    // Endpoint to delete a player by ID
    @DeleteMapping("/player/{id}")
    public void deletePlayerById(@PathVariable long id) {
        playerService.deletePlayerById(id);
    }
}
