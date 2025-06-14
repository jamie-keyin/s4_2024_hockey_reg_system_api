//Description: Data Transfer Object for Game, cleans up the output and reduces the JSON within JSON look.
//Author: DC Elliott
//Date: Jun 13/2025


package com.keyin.rest.dto;

import com.keyin.rest.game.Game;

import java.time.LocalDateTime;

public class GameDTO {
    private long id;
    private String location;
    private LocalDateTime scheduledAt;
    private String homeTeamName;
    private String awayTeamName;

    //Cuts down the output to just names instead of complete JSON objects.
    public GameDTO(Game game) {
        this.id = game.getId();
        this.location = game.getLocation();
        this.scheduledAt = game.getScheduledDate();
        this.homeTeamName = game.getHomeTeam() != null ? game.getHomeTeam().getName() : null;
        this.awayTeamName = game.getAwayTeam() != null ? game.getAwayTeam().getName() : null;
    }

    public long getId() {return id; }

    public String getLocation() {return location; }

    public LocalDateTime getScheduledAt() {return scheduledAt; }

    public String getHomeTeamName() {return homeTeamName; }

    public String getAwayTeamName() {return awayTeamName; }
}
