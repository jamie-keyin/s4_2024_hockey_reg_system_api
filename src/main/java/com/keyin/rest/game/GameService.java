package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            game.setHomeTeam(updatedGame.getAwayTeam());
            game.setAwayTeam(updatedGame.getAwayTeam());
            game.setLocation(updatedGame.getLocation());
            game.setScheduledDate(updatedGame.getScheduledDate());
            return gameRepository.save(game);
        } else {
            updatedGame.setGameId(id);
            return gameRepository.save(updatedGame);
        }
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}