package com.keyin.rest.game;

import com.keyin.rest.division.Division;
import com.keyin.rest.player.Player;
import com.keyin.rest.team.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Game getGameById(long id) {
        Optional<Game> result = gameRepository.findById(id);

        return result.orElse(null);
    }


    public Game createGame(Game newGame){
        String homeName = newGame.getHomeTeam().getName();
        String awayName = newGame.getAwayTeam().getName();


        if (homeName != null) {
            Team home = teamService.findByName(homeName);
            if (home == null) {
                home = teamService.createTeam(newGame.getHomeTeam());
            }
            newGame.setHomeTeam(home);
        }


        if (awayName != null) {
            Team away = teamService.findByName(awayName);
            if (away == null) {
                away = teamService.createTeam(newGame.getAwayTeam());
            }
            newGame.setAwayTeam(away);
        }

        return gameRepository.save(newGame);
    }


}
