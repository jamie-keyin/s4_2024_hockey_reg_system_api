package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Game createGame(Game game) {
        Team homeTeam = teamRepository.findById(game.getHomeTeam().getId()).orElse(null);
        Team awayTeam = teamRepository.findById(game.getAwayTeam().getId()).orElse(null);

        if (homeTeam != null && awayTeam != null) {
            game.setHomeTeam(homeTeam);
            game.setAwayTeam(awayTeam);
            return gameRepository.save(game);
        }

        return null;
    }
}

