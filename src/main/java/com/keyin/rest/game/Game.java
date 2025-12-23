package com.keyin.rest.game;

import java.time.LocalDateTime;

import com.keyin.rest.team.Team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;


@Entity
public class Game {
    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @Column(nullable = false)
    private String location;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    public Game() {}
    public Game(Team homeTeam, Team awayTeam, String location, LocalDateTime scheduledDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.scheduledDate = scheduledDate;
    }

    public long getID() {return id;}
    public void setID(long id) {this.id = id;}

    public Team getHomeTeam() {return homeTeam;}
    public void setHomeTeam(Team homeTeam) {this.homeTeam = homeTeam;}

    public Team getAwayTeam() {return awayTeam;}
    public void setAwayTeam(Team awayTeam) {this.awayTeam = awayTeam;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public LocalDateTime getScheduledDate() {return scheduledDate;}
    public void setScheduledDate(LocalDateTime scheduledDate) {this.scheduledDate = scheduledDate;}
} 