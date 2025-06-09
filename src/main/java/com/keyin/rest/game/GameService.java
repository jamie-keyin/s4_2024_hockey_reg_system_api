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

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(long id, Game update) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game existing = gameOptional.get();
            existing.setHomeTeam(update.getHomeTeam());
            existing.setAwayTeam(update.getAwayTeam());
            existing.setGameDate(update.getGameDate());
            return gameRepository.save(existing);
        }
        return null;
    }

    public void deleteGameById(long id) {
        gameRepository.deleteById(id);
    }
}
