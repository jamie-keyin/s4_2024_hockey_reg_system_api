//Description: This initializes the database with some test data
//Author: DC Elliott
//Date: Jun 13/2025



package com.keyin.rest;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionRepository;
import com.keyin.rest.player.Player;
import com.keyin.rest.player.PlayerRepository;
import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamRepository;
import com.keyin.rest.game.Game;
import com.keyin.rest.game.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


//The ease with which this Component annotation and CommandLineRunner interface allow the test data
//to be uploaded made me understand why Spring is popular.
@Component
public class DataLoader implements CommandLineRunner {

    private final DivisionRepository divisionRepo;
    private final PlayerRepository playerRepo;
    private final TeamRepository teamRepo;
    private final GameRepository gameRepo;

    public DataLoader(DivisionRepository divisionRepo, PlayerRepository playerRepo, TeamRepository teamRepo, GameRepository gameRepo) {
        this.divisionRepo = divisionRepo;
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
        this.gameRepo = gameRepo;
    }

    @Override
    public void run(String... args) {
        // Load Divisions
        Division bantam = new Division();
        bantam.setName("Bantam");
        bantam.setStartBirthYear("2009");
        bantam.setEndBirthYear("2010");
        divisionRepo.save(bantam);

        Division midget = new Division();
        midget.setName("PeeWee");
        midget.setStartBirthYear("2007");
        midget.setEndBirthYear("2008");
        divisionRepo.save(midget);

        // Load Players
        Player mark = new Player();
        mark.setFirstName("Mark");
        mark.setLastName("McGwire");
        Calendar dob1 = Calendar.getInstance();
        dob1.set(2009, Calendar.JANUARY, 15);
        mark.setBirthday(dob1);
        playerRepo.save(mark);

        Player babe = new Player();
        babe.setFirstName("Babe");
        babe.setLastName("Ruth");
        Calendar dob2 = Calendar.getInstance();
        dob2.set(2008, Calendar.JULY, 22);
        babe.setBirthday(dob2);
        playerRepo.save(babe);

        // Load Teams
        Team yankees = new Team();
        yankees.setName("Yankees");
        yankees.setDivision(bantam);
        yankees.setPlayers(List.of(mark));
        teamRepo.save(yankees);

        Team dodgers = new Team();
        dodgers.setName("Dodgers");
        dodgers.setDivision(midget);
        dodgers.setPlayers(List.of(babe));
        teamRepo.save(dodgers);

        // Load Game
        Game game = new Game();
        game.setHomeTeam(yankees);
        game.setAwayTeam(dodgers);
        game.setLocation("Downtown Arena");
        game.setScheduledDate(LocalDateTime.of(2025, 6, 15, 19, 0));
        gameRepo.save(game);

        System.out.println("Test data loaded successfully.");
    }
}
