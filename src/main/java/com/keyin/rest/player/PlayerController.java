package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/player_search")
    public Player getPlayerByLastName(@RequestParam("last_name") String lastName) {
        return playerService.getPlayerByLastName(lastName);
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // added by KET: POST endpoint to create multiple players
    @PostMapping("/players")
    public List<Player> createPlayers(@RequestBody List<Player> newPlayers) {
        return playerService.createPlayers(newPlayers);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayerById(@PathVariable long id) {
        playerService.deletePlayerById(id);
    }
}
