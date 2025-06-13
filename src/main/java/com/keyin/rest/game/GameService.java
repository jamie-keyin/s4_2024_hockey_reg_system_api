package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Business logic layer for Game operations.
 */
@Service
public class GameService {
    @Autowired private GameRepository gameRepo; // Handles database interactions
    @Autowired private TeamService teamService; // Allows validation and lookup of teams

    /**
     * Retrieves all games.
     */
    public List<Game> getAllGames() {
        return (List<Game>) gameRepo.findAll();
    }

    /**
     * Creates a new game, ensuring valid teams are provided.
     */
    public Game createGame(Game incoming) {
        if (incoming.getHomeTeam() == null || incoming.getAwayTeam() == null)
            throw new IllegalArgumentException("Home and Away teams required");

        if (incoming.getHomeTeam().getId() == incoming.getAwayTeam().getId())
            throw new IllegalArgumentException("Home and Away teams cannot be the same");

        Team home = teamService.getTeamById(incoming.getHomeTeam().getId());
        Team away = teamService.getTeamById(incoming.getAwayTeam().getId());

        incoming.setHomeTeam(home);
        incoming.setAwayTeam(away);

        return gameRepo.save(incoming);
    }
}
