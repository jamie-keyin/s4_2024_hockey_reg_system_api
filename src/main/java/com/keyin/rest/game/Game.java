package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.*;
import java.util.Calendar;

@Entity
public class Game {
    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1,
            initialValue = 1
    )

    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_team_id")   // matches Team’s ID column
    private Team homeTeam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar scheduled;

    // No-arg constructor (required by JPA)
    public Game() {
    }

    // Convenience constructor (optional)
    public Game(Team homeTeam, Team awayTeam, String location, Calendar scheduled) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.scheduled = scheduled;
    }

    // Getters and setters

    public long getId(){
        return id;
    }

    public void setId(long id){
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

    public Calendar getScheduled() {
        return scheduled;
    }

    public void setScheduled(Calendar scheduled) {
        this.scheduled = scheduled;
    }
}
