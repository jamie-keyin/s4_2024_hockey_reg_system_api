package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found"));
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(long id, Player player) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player not found");
        }
        player.setId(id); // Ensure the ID is set for update
        return playerRepository.save(player);
    }

    public void deletePlayer(long id) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player not found");
        }
        playerRepository.deleteById(id);
    }
}
