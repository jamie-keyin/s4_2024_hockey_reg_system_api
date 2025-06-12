package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "game_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    private String location;

    private LocalDateTime scheduledDate;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Team getHomeTeam() { return homeTeam; }

    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public Team getAwayTeam() { return awayTeam; }

    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getScheduledDate() { return scheduledDate; }

    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }
}
