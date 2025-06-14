// Program description: This is a basic REST API for managing hockey games.
// Author: Nicholas Power SD12
// Date: June 13, 2025

package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Schema(description = "Represents a scheduled hockey game between two teams.")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the game", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    @Schema(description = "Home team for the game")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    @Schema(description = "Away team for the game")
    private Team awayTeam;

    @Schema(description = "Location where the game is played", example = "St. John's Arena")
    private String location;

    @Schema(description = "Date the game is scheduled", example = "2025-06-20")
    private LocalDate scheduledDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
