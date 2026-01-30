package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.*;

@Entity
public class Game {

    // Attributes

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "game_sequence")
    private long gameId;

    @ManyToOne
    public Team homeTeam;

    @ManyToOne
    public Team awayTeam;

    public String gameLocation;
    public String gameDate;

    // Constructor

    public Game(Team homeTeam, Team awayTeam, String gameLocation, String gameDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameLocation = gameLocation;
        this.gameDate = gameDate;
    }

    // Getters & Setters


    public long getGameId() {
        return gameId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
}
