package com.keyin.rest.player;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @CrossOrigin public class PlayerController {

    @Autowired private PlayerService playerService;

    @GetMapping("/player") // Endpoint to retrieve all players
    public List<Player> getAllPlayers() { return playerService.getAllPlayers(); }

    @GetMapping("/player/{id}") // Endpoint to retrieve a player by ID
    public Player getPlayerById(@PathVariable long id) { return playerService.getPlayerById(id); }

    @PostMapping("/player") // Endpoint to create a new player
    public Player createPlayer(@RequestBody Player player) { return playerService.createPlayer(player); }

    @PutMapping("/player/{id}") // Endpoint to update an existing player
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    @DeleteMapping("/player/{id}") // Endpoint to delete a player by ID
    public void deletePlayerById(@PathVariable long id) { playerService.deletePlayerById(id); }
}
