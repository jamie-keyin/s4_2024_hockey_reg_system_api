package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public GameService(GameRepository gameRepository,
                       TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(Game incoming) {
        Team home = teamRepository.findById(incoming.getHomeTeam().getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Home team not found"));

        Team away = teamRepository.findById(incoming.getAwayTeam().getId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Away team not found"));

        if (home.getId() == away.getId()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Home and away teams must be different"
            );
        }

        incoming.setHomeTeam(home);
        incoming.setAwayTeam(away);

        return gameRepository.save(incoming);
    }
}
