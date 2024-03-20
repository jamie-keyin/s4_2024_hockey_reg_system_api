package com.keyin.rest.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    public List<Player> getAllPlayers(){
        return (List<Player>) playerRepo.findAll();
    }

    public Player getPlayerById(long id){
        Optional<Player> playerOptional = playerRepo.findById(id);
        return playerOptional.orElse(null);
    }

    public void deletePlayerById(long id){
        playerRepo.deleteById(id);
    }

    public Player createPlayer(Player player){
        return playerRepo.save(player);
    }

    public Player updatePlayer(long id, Player updatePlayer) {
        Optional<Player> playerUpdateOptional = playerRepo.findById(id);

        if (playerUpdateOptional.isPresent()) {
            Player playerUpdate = playerUpdateOptional.get();
            playerUpdate.setFirstName(updatePlayer.getFirstName());
            playerUpdate.setLastName(updatePlayer.getLastName());
            playerUpdate.setBirthday(updatePlayer.getBirthday());

            return playerRepo.save(playerUpdate);
        }
        return null;
    }
}
