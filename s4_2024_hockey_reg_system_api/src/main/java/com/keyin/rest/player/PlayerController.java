package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable long id) {
        playerService.deletePlayer(id);
    }
}
