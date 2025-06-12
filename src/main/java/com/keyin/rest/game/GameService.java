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

    public Team getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    //      example input JSON for POST request to create a game:
    //      both "id" and "name" can be used for home and away teams
    //      any combination of these is allowed, or both can be used
    //      {
    //         "homeTeam": {"name": "Dragons"},
    //         "awayTeam": {"id": 2},
    //         "location": "Arena A",
    //         "scheduledDate": "2025-07-15T18:30:00"
    //      }
    public Game createGame(Game game) {
        Team homeInput = game.getHomeTeam();
        Team awayInput = game.getAwayTeam();

        Team homeTeam = null;
        Team awayTeam = null;

        // homeTeam
        if (homeInput != null) {
            if (homeInput.getId() != 0) {
                homeTeam = teamRepository.findById(homeInput.getId()).orElse(null);
            } else if (homeInput.getName() != null) {
                homeTeam = teamRepository.findByName(homeInput.getName());
            }
        }

        // awayTeam
        if (awayInput != null) {
            if (awayInput.getId() != 0) {
                awayTeam = teamRepository.findById(awayInput.getId()).orElse(null);
            } else if (awayInput.getName() != null) {
                awayTeam = teamRepository.findByName(awayInput.getName());
            }
        }

        // throw an exception if needed, this will be the message in the POST-response
        if (homeTeam == null && awayTeam == null) {
            throw new IllegalArgumentException("Both home team and away teams are not in the database.");
        } else if (homeTeam == null) {
            throw new IllegalArgumentException("Home team is not in the database.");
        } else if (awayTeam == null) {
            throw new IllegalArgumentException("Away team is not in the database.");
        }

        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);

        return gameRepository.save(game);
    }

    public List<Game> getAllGames() { return ((List<Game>) gameRepository.findAll()); }
}
