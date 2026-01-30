package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    // Attributes

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamService teamService;

    // Methods

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Game createGame(Game newGame) {
        Long homeTeamId = newGame.getHomeTeam().getId();
        Long awayTeamId = newGame.getAwayTeam().getId();

        if (homeTeamId != null) {
            Team homeTeam = teamService.getTeamById(homeTeamId);

            if (homeTeamId == null) {
                homeTeam = teamService.createTeam(newGame.getHomeTeam());
            }
        }

        if (awayTeamId != null) {
            Team awayTeam = teamService.getTeamById(awayTeamId);

            if (awayTeamId == null) {
                awayTeam = teamService.createTeam(newGame.getAwayTeam());
            }
        }

        return gameRepository.save(newGame);
    }
}
