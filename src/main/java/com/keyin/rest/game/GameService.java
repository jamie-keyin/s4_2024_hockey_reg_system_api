//Description: Again mirrored from existing classes, has the basic functionality plus
// the additional methods required for the added GET calls, PUT and DELETE routes.
//Author: DC Elliott
//Date: Jun 13/2025


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
        return gameRepository.findById(id).orElse(null);
    }

    public Game createGame(Game newGame) {
        return gameRepository.save(newGame);
    }

    public Game updateGame(long id, Game updatedGame) {
        Optional<Game> optionalGame = gameRepository.findById(id);

        if (optionalGame.isPresent()) {
            Game existing = optionalGame.get();
            existing.setHomeTeam(updatedGame.getHomeTeam());
            existing.setAwayTeam(updatedGame.getAwayTeam());
            existing.setLocation(updatedGame.getLocation());
            existing.setScheduledDate(updatedGame.getScheduledDate());
            return gameRepository.save(existing);
        }

        return null;
    }

    public void deleteGame(long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> getGamesByLocation(String location) {
        return gameRepository.findByLocationContainingIgnoreCase(location);
    }


}

