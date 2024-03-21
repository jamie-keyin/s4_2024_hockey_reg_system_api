package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() { return (List<Player>) playerRepository.findAll(); }

    public Player getPlayerById(long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);

        return playerOptional.orElse(null);
    }

    public void deletePlayerById(long id) { playerRepository.deleteById(id);}

    public Player createPlayer (Player newPlayer) {return playerRepository.save(newPlayer); }

    public Player updatePlayer(long id, Player updatedPlayer) {
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);

        if (playerToUpdateOptional.isPresent()) {
            Player playerToUpdate = playerToUpdateOptional.get();
            playerToUpdate.setBirthday(updatedPlayer.getBirthday());
            playerToUpdate.setFirstName(updatedPlayer.getFirstName());
            playerToUpdate.setLastName(updatedPlayer.getLastName());

            return playerRepository.save(playerToUpdate);
        }

        return null;
    }


}
