package com.keyin.rest.player;

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
        Optional<Player> playerOptional = playerRepository.findById(id);

        return playerOptional.orElse(null);
    }

    public Player getPlayerByLastName(String lastName) {
        return playerRepository.findByLastName(lastName);
    }

    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }

    public Player createPlayer(Player newPlayer) { return playerRepository.save(newPlayer); }

    // added by KET: method to create multiple players
    // sample JSON:
    //    [
    //        {
    //            "firstName": "Alice",
    //            "lastName": "Smith",
    //            "birthday": "2012-05-15"
    //        },
    //        {
    //            "firstName": "Bob",
    //            "lastName": "Johnson",
    //            "birthday": "2013-07-10"
    //        }
    //    ]
    public List<Player> createPlayers(List<Player> newPlayers) {
        return (List<Player>) playerRepository.saveAll(newPlayers);
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
