package com.keyin.rest.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    Player createPlayer(Player player) {
        return this.playerRepository.save(player);
    }

    List<Player> readAllPlayers() {
        return this.playerRepository.findAll();
    }

    Optional<Player> readPlayerById(Long id) {
        return this.playerRepository.findById(id);
    }

    ResponseEntity<Player> updatePlayerById(Long id, Player updatedPlayer) {
        Optional<Player> playerOptional = this.playerRepository.findById(id);

        if (playerOptional.isPresent()) {
            Player existedPlayer = playerOptional.get();

            existedPlayer.setFirstName(updatedPlayer.getFirstName());
            existedPlayer.setLastName(updatedPlayer.getLastName());
            existedPlayer.setBirthday(updatedPlayer.getBirthday());

            return new ResponseEntity<>(this.playerRepository.save(existedPlayer), HttpStatus.OK);
        }

        return new ResponseEntity<>(this.playerRepository.save(updatedPlayer), HttpStatus.CREATED);
    }

    ResponseEntity<String> deletePlayerById(Long id) {
        Optional<Player> playerOptional = this.playerRepository.findById(id);

        return playerOptional.map(player -> {
                    this.playerRepository.deleteById(player.getId());

                    return ResponseEntity.ok("player successfully deleted");
                }).orElse(new ResponseEntity<>("there is no player with id: " + id, HttpStatus.NOT_FOUND)
        );
    }
}
