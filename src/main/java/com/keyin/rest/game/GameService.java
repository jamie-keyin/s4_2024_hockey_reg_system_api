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
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private TeamRepository teamRepository;
    
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
    
    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }
    
    public Game createGame(Game game) {
        // Validate that home team and away team are different
        if (game.getHomeTeam() != null && game.getAwayTeam() != null) {
            if (game.getHomeTeam().getId() == game.getAwayTeam().getId()) {
                throw new IllegalArgumentException("Home team and away team cannot be the same");
            }
        }
        
        // Validate that both teams exist
        if (game.getHomeTeam() != null) {
            Optional<Team> homeTeam = teamRepository.findById(game.getHomeTeam().getId());
            if (homeTeam.isEmpty()) {
                throw new IllegalArgumentException("Home team does not exist");
            }
            game.setHomeTeam(homeTeam.get());
        }
        
        if (game.getAwayTeam() != null) {
            Optional<Team> awayTeam = teamRepository.findById(game.getAwayTeam().getId());
            if (awayTeam.isEmpty()) {
                throw new IllegalArgumentException("Away team does not exist");
            }
            game.setAwayTeam(awayTeam.get());
        }
        
        return gameRepository.save(game);
    }
    
    public Game updateGame(Long id, Game gameDetails) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            
            // Validate that home team and away team are different
            if (gameDetails.getHomeTeam() != null && gameDetails.getAwayTeam() != null) {
                if (gameDetails.getHomeTeam().getId() == gameDetails.getAwayTeam().getId()) {
                    throw new IllegalArgumentException("Home team and away team cannot be the same");
                }
            }
            
            if (gameDetails.getHomeTeam() != null) {
                Optional<Team> homeTeam = teamRepository.findById(gameDetails.getHomeTeam().getId());
                if (homeTeam.isEmpty()) {
                    throw new IllegalArgumentException("Home team does not exist");
                }
                game.setHomeTeam(homeTeam.get());
            }
            
            if (gameDetails.getAwayTeam() != null) {
                Optional<Team> awayTeam = teamRepository.findById(gameDetails.getAwayTeam().getId());
                if (awayTeam.isEmpty()) {
                    throw new IllegalArgumentException("Away team does not exist");
                }
                game.setAwayTeam(awayTeam.get());
            }
            
            if (gameDetails.getLocation() != null) {
                game.setLocation(gameDetails.getLocation());
            }
            
            if (gameDetails.getScheduledDate() != null) {
                game.setScheduledDate(gameDetails.getScheduledDate());
            }
            
            return gameRepository.save(game);
        }
        return null;
    }
    
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
    
    public List<Game> getGamesByTeam(Long teamId) {
        return gameRepository.findByTeamId(teamId);
    }
    
    public List<Game> getGamesByHomeTeam(Long homeTeamId) {
        return gameRepository.findByHomeTeamId(homeTeamId);
    }
    
    public List<Game> getGamesByAwayTeam(Long awayTeamId) {
        return gameRepository.findByAwayTeamId(awayTeamId);
    }
    
    public List<Game> getGamesByLocation(String location) {
        return gameRepository.findByLocationContainingIgnoreCase(location);
    }
    
    public List<Game> getGamesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return gameRepository.findByScheduledDateBetween(startDate, endDate);
    }
} 