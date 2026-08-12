package com.keyin.rest.games;

import com.keyin.rest.team.Team;
import org.springframework.stereotype.Service;
import com.keyin.rest.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamService teamService;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    // Create a new Game //
    public Game createGame(Game newGame) {
        // Validate that home and away teams are different //
        if (newGame.getHomeTeam() != null && newGame.getAwayTeam() != null &&
                newGame.getHomeTeam().getId() == newGame.getAwayTeam().getId()) {
            throw new IllegalArgumentException("Home team and away team cannot be the same");
        }

        // Ensure home team exists //
        if (newGame.getHomeTeam() != null && newGame.getHomeTeam().getId() > 0) {
            Team homeTeam = teamService.getTeamById(newGame.getHomeTeam().getId());
            if (homeTeam == null) {
                throw new IllegalArgumentException("Home team with ID " + newGame.getHomeTeam().getId() + " not found");
            }
            newGame.setHomeTeam(homeTeam);
        }

        // Ensure away team exists //
        if (newGame.getAwayTeam() != null && newGame.getAwayTeam().getId() > 0) {
            Team awayTeam = teamService.getTeamById(newGame.getAwayTeam().getId());
            if (awayTeam == null) {
                throw new IllegalArgumentException("Home team with ID " + newGame.getAwayTeam().getId() + " not found");
        }
            newGame.setAwayTeam(awayTeam);
        }

    return gameRepository.save(newGame);

    }
}
