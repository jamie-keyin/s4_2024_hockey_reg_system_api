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

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game existingGame = gameOptional.get();
            existingGame.setGameDateTime(gameDetails.getGameDateTime());
            existingGame.setHomeTeam(gameDetails.getHomeTeam());
            existingGame.setAwayTeam(gameDetails.getAwayTeam());
            existingGame.setHomeTeamScore(gameDetails.getHomeTeamScore());
            existingGame.setAwayTeamScore(gameDetails.getAwayTeamScore());
            return gameRepository.save(existingGame);
        }
        return null;
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
