package com.keyin.rest.games;

import com.keyin.rest.team.Team;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER) // Suggested by Claude to use EAGER//
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.EAGER) // Suggested by Claude to use EAGER//
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    private Calendar gameDate;

    private String location;

    // Default constructor
    public Game() {
    }

    // Constructor with parameters
    public Game(Team homeTeam, Team awayTeam, Calendar gameDate, String location) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate = gameDate;
        this.location = location;
    }

    // Getters & Setters //

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getHomeTeam(){
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

    public Calendar getGameDate(){
        return gameDate;
    }

    public void setGameDate(Calendar gameDate){
        this.gameDate = gameDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
