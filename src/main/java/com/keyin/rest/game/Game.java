package com.keyin.rest.game;

import jakarta.persistence.*;
import com.keyin.rest.team.*;
import java.time.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private String homeTeam;
    private String awayTeam;
    private String location;
    private LocalDate scheduledDate;

    // Getters
    public long getGameId() {
        return gameId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    // Setters
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
