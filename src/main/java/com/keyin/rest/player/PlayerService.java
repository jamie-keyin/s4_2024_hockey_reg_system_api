package com.keyin.rest.player;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

    public Player getPlayerById(long id) {
        Optional<Player> divisionOptional = playerRepository.findById(id);
        return divisionOptional.orElse(null);
    }

    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }

    public Player createPlayer(Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    public Player updatePlayer(long id, Player updatedPlayer) {
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);
        if (playerToUpdateOptional.isPresent()) {
            Player playerToUpdate = playerToUpdateOptional.get();
            playerToUpdate.setFirstName(updatedPlayer.getFirstName());
            playerToUpdate.setLastName(updatedPlayer.getLastName());
            playerToUpdate.setBirthday(updatedPlayer.getBirthday());
            return playerRepository.save(playerToUpdate);
        }
        return null;
    }
}
