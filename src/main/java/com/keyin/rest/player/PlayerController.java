package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player")
    public ResponseEntity<?> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable("id") Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }
    }

    @PostMapping("/player")
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body("Player created successfully");
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable("id") Long id, @RequestBody Player player) {
        boolean updated = playerService.updatePlayer(id, player);
        if (updated) {
            return ResponseEntity.ok("Player updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id) {
        boolean deleted = playerService.deletePlayer(id);
        if (deleted) {
            return ResponseEntity.ok("Player deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
        }
    }
}
