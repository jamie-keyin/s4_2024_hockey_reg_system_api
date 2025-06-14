package com.keyin.rest.game;
import com.keyin.rest.team.Team;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "game_sequence")
    private long id;
    @ManyToOne
    @JoinColumn(name = "home_team", referencedColumnName = "id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team", referencedColumnName = "id")
    private Team awayTeam;
    private String location;
    private LocalDateTime gameDate;

    public Game() {} // empty constructor

    public Game(Team homeTeam, Team awayTeam, String location, LocalDateTime gameDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.gameDate = gameDate;
    }

    // getters and setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDateTime gameDate) {
        this.gameDate = gameDate;
    }
}