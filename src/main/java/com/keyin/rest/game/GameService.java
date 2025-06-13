package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDateTime;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {return (List<Game>) gameRepository.findAll();}

    public List<Game> getGamesByTeam(Team team) {
    List<Game> homeGames = gameRepository.findByTeam_Home(team);
    List<Game> awayGames = gameRepository.findByTeam_Away(team);
    return Stream.concat(homeGames.stream(), awayGames.stream()).collect(Collectors.toList());
    }

    public Game getGameById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        return gameOptional.orElse(null);
    }

    public List<Game> getGameByLocation(String location) {
        return gameRepository.findByLocation(location);
    }

    public List<Game> getGameByDate(LocalDateTime gameDate) {
        return gameRepository.findByGameDate(gameDate);
    }
    public List<Game> getGameByTeamHome(Team homeTeam) {
        return gameRepository.findByTeam_Home(homeTeam);
    }

    public List<Game> getGameByTeamAway(Team awayTeam) {
        return gameRepository.findByTeam_Away(awayTeam);
    }

    public void deleteGameByID(long id) {gameRepository.deleteById(id);}

    public Game createGame(Game newGame) { return gameRepository.save(newGame);}

    public Game updateGame(long id, Game updatedGame) {
        if (id != updatedGame.getId()) {
            throw new DataIntegrityViolationException("Game ID mismatch");
        }

        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);

        if (gameToUpdateOptional.isPresent()) {
            Game gameToUpdate = gameToUpdateOptional.get();

            gameToUpdate.setAwayTeam(updatedGame.getAwayTeam());
            gameToUpdate.setHomeTeam(updatedGame.getHomeTeam());
            gameToUpdate.setLocation(updatedGame.getLocation());
            gameToUpdate.setGameDate(updatedGame.getGameDate());

            return gameRepository.save(gameToUpdate);
        }
        throw new EntityNotFoundException("Game not found with ID " + id);
    }
}
