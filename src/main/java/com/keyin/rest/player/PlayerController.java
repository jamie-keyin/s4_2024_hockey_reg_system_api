package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    public List<Player> getAllPlayers() { return playerService.getAllPlayers();}

    @GetMapping("/player/{id}")
    public Player getPlayerById(@PathVariable long id) {return playerService.getPlayerById(id);}

    @PostMapping("/player")
    public Player createPlayer(@RequestBody Player player) { return playerService.createPlayer(player);}

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody Player player) {
        return ResponseEntity.ok(playerService.updatePlayer(id, player));
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayerById(@PathVariable long id) { playerService.deletePlayerById(id);}
}
