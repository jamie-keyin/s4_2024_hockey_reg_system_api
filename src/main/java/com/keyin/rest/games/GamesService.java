package com.keyin.rest.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
    @Autowired
    private GamesRepository gamesRepository;

    public List<Games> getAllGames() {
        return (List<Games>) gamesRepository.findAll();
    }

    public Games createGame(Games game) {
        return gamesRepository.save(game);
    }
}
