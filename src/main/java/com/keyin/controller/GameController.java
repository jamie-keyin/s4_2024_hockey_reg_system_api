package com.keyin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyin.domain.Game;
import com.keyin.dto.GameDTO;
import com.keyin.repository.GameRepository;
import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(game ->
            new GameDTO(
                game.getId(),
                game.getHomeTeam().getName(),
                game.getAwayTeam().getName(),
                game.getLocation(),
                game.getScheduledDate()
            )
        ).collect(Collectors.toList());
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        // Validate teams
        Team home = teamRepository.findById(game.getHomeTeam().getId()).orElseThrow();
        Team away = teamRepository.findById(game.getAwayTeam().getId()).orElseThrow();

        game.setHomeTeam(home);
        game.setAwayTeam(away);

        return gameRepository.save(game);
    }
}
