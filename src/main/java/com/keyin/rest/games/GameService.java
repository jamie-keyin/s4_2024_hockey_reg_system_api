package com.keyin.rest.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gamesRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gamesRepository.findAll();
    }

    public Game createGame(Game game) {
        return gamesRepository.save(game);
    }
}