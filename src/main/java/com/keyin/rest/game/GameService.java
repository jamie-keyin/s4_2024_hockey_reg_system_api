package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        return gameOptional.orElse(null);
    }

    public void deleteGameById(long id) {
        gameRepository.deleteById(id);
    }

    public Game createGame(Game newGame) {
        return gameRepository.save(newGame);
    }

    public Game updateGame(long id, Game updatedGame) {
        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);

        if (gameToUpdateOptional.isPresent()) {
            Game gameToUpdate = gameToUpdateOptional.get();

            gameToUpdate.setHomeTeam(updatedGame.getHomeTeam());
            gameToUpdate.setAwayTeam(updatedGame.getAwayTeam());
            gameToUpdate.setLocation(updatedGame.getLocation());
            gameToUpdate.setScheduledDate(updatedGame.getScheduledDate());

            return gameRepository.save(gameToUpdate);
        }

        return null;
    }
}