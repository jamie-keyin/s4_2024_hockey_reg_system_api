package com.keyin.rest.game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamService;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamService teamService;


    public List<Game> getAllGames() {return (List<Game>) gameRepository.findAll();}


    public Game getGameByID(long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        return gameOptional.orElse(null);
    }

    
    public List<Game> getGamesByHomeTeamName(String teamName) {return gameRepository.findByHomeTeam_Name(teamName);}


    public List<Game> getGamesByAwayTeamName(String teamName) {return gameRepository.findByAwayTeam_Name(teamName);}


    public List<Game> getGamesByLocation(String location) {return gameRepository.findByLocation(location);}
    

    public List<Game> getGamesByScheduledDate(LocalDateTime start, LocalDateTime end) {return gameRepository.findByScheduledDateBetween(start, end);}


    public void deleteGameByID(long id) {gameRepository.deleteById(id);}


    public Game createGame(Game newGame) {
        // home team validation
        if (newGame.getHomeTeam() != null && newGame.getHomeTeam().getId() > 0) {
            Team homeTeam = teamService.getTeamById(newGame.getHomeTeam().getId());
            if (homeTeam != null) {
                newGame.setHomeTeam(homeTeam);
            }
        }

        // away team validation
        if (newGame.getAwayTeam() != null && newGame.getAwayTeam().getId() > 0) {
            Team awayTeam = teamService.getTeamById(newGame.getAwayTeam().getId());
            if (awayTeam != null) {newGame.setAwayTeam(awayTeam);}
        }
        return gameRepository.save(newGame);}

    public Game updateGameByID(long id, Game updatedGame) {
        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);
        if (gameToUpdateOptional.isPresent()) {
            Game gameToUpdate = gameToUpdateOptional.get();
            if (updatedGame.getHomeTeam() != null) {
                gameToUpdate.setHomeTeam(teamService.getTeamById(updatedGame.getHomeTeam().getId()));
            }
            if (updatedGame.getAwayTeam() != null) {
                gameToUpdate.setAwayTeam(teamService.getTeamById(updatedGame.getAwayTeam().getId()));
            }
            gameToUpdate.setLocation(updatedGame.getLocation());
            gameToUpdate.setScheduledDate(updatedGame.getScheduledDate());
            return gameRepository.save(gameToUpdate);
        }
        return null;
    }
} 