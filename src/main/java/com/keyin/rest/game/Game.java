package com.keyin.rest.game;

import java.time.LocalDateTime;

public class Game {

    private String homeTeam;
    private String awayTeam;
    private String location;
    private LocalDateTime gameDate;

    public Game() {} // empty constructor

    public Game(String homeTeam, String awayTeam, String location, LocalDateTime gameDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.gameDate = gameDate;
    }

    // getters and setters


    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
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
