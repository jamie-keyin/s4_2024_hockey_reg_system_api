package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testCreateAndFindGame() {
        Team homeTeam = new Team();
        homeTeam.setTeamName("Home Team");
        entityManager.persist(homeTeam);

        Team awayTeam = new Team();
        awayTeam.setTeamName("Away Team");
        entityManager.persist(awayTeam);

        Game game = new Game();
        game.setGameDateTime(LocalDateTime.now());
        game.setHomeTeam(homeTeam);
        game.setAwayTeam(awayTeam);
        game.setHomeTeamScore(1);
        game.setAwayTeamScore(0);

        Game savedGame = gameRepository.save(game);
        assertNotNull(savedGame.getId());

        Game foundGame = gameRepository.findById(savedGame.getId()).orElse(null);
        assertNotNull(foundGame);
        assertEquals("Home Team", foundGame.getHomeTeam().getTeamName());
        assertEquals("Away Team", foundGame.getAwayTeam().getTeamName());
    }
}
