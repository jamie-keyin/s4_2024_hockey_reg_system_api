package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamService teamService;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game gameRequest) {
        // Checks teams first, to make sure they exist
        Team home = teamService.getTeamById(gameRequest.getHomeTeam().getId());
        Team away = teamService.getTeamById(gameRequest.getAwayTeam().getId());

        // Print both teams for debugging
        System.out.println("Home Team: " + (home != null ? home.getName() : "null"));
        System.out.println("Away Team: " + (away != null ? away.getName() : "null"));

        if (home == null || away == null) {
            throw new IllegalArgumentException("Both homeTeam and awayTeam must exist");
        }
        if (home.getId() == away.getId()) {
            throw new IllegalArgumentException("homeTeam and awayTeam cannot be the same");
        }

        Game g = new Game();
        g.setHomeTeam(home);
        g.setAwayTeam(away);
        g.setLocation(gameRequest.getLocation());
        g.setScheduledDate(gameRequest.getScheduledDate());

        return gameRepository.save(g);
    }
}
