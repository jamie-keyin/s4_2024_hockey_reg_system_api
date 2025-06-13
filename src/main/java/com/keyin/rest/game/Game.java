package com.keyin.rest.game;

import com.keyin.rest.team.Team; // Imports the Team entity
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 * Represents a hockey game with home and away teams, location, and scheduled date.
 */
@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "game_sequence")
    private long id; // Unique identifier for each game

    @ManyToOne(optional = false) // Links this game to a home team (many games per team)
    private Team homeTeam;

    @ManyToOne(optional = false) // Links this game to an away team (many games per team)
    private Team awayTeam;

    private String location; // Where the game takes place

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // Stores date in standard format
    private LocalDateTime scheduledDate;

    // Getters and Setters to allow access and modification of fields
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }
}
