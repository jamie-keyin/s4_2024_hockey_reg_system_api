package com.keyin.rest.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames(){
        return (List<Game>) gameRepository.findAll();
    }

    public Game createGame(Game newGame){
        return gameRepository.save(newGame);
    }
}
