package com.keyin.rest.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    List<Player> getAllPlayers() {
        return this.playerService.readAllPlayers();
    }

    @GetMapping("{id}")
    ResponseEntity<Player> getPlayerById(@PathVariable(name = "id") Long id) {
        return this.playerService.readPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    Player postPlayer(@RequestBody Player player) {
        return this.playerService.createPlayer(player);
    }

    @PutMapping("{id}")
    ResponseEntity<Player> putPlayer(@PathVariable(name = "id") Long id, @RequestBody Player updatedPlayer) {
        return this.playerService.updatePlayerById(id, updatedPlayer);
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deletePlayer(@PathVariable(name = "id") Long id) {
        return this.playerService.deletePlayerById(id);
    }
}
