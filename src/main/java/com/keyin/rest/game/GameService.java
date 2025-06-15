package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public GameService(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(GameRequest gameRequest) {
        Optional<Team> homeTeamOptional = teamRepository.findById(gameRequest.getHomeTeamId());
        Optional<Team> awayTeamOptional = teamRepository.findById(gameRequest.getAwayTeamId());

        if (homeTeamOptional.isEmpty()) {
            throw new IllegalArgumentException("Home team with ID" + gameRequest.getHomeTeamId() + " not found." );
        }
        if (awayTeamOptional.isEmpty()) {
            throw new IllegalArgumentException("Away team with ID" + gameRequest.getAwayTeamId() + " not found." );
        }

        Game newGame = new Game(
                homeTeamOptional.get(),
                awayTeamOptional.get(),
                gameRequest.getLocation(),
                gameRequest.getScheduledDate()
                );
        return gameRepository.save(newGame);

    }

    public static class GameRequest {
        private Long homeTeamId;
        private Long awayTeamId;
        private String location;
        private LocalDateTime scheduledDate;

        // Getters and Setters
        public Long getHomeTeamId() {
            return homeTeamId;
        }

        public void setHomeTeamId(Long homeTeamId) {
            this.homeTeamId = homeTeamId;
        }

        public Long getAwayTeamId() {
            return awayTeamId;
        }

        public void setAwayTeamId(Long awayTeamId) {
            this.awayTeamId = awayTeamId;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LocalDateTime getScheduledDate() {
            return scheduledDate;
        }

        public void setScheduledDate(LocalDateTime scheduledDate) {
            this.scheduledDate = scheduledDate;
        }

    }

}
