package com.keyin.rest.game;

import jakarta.persistence.*;
import com.keyin.rest.team.Team;
import java.time.LocalDateTime;

@Entity
public class Game {
    @Id
    @SequenceGenerator(name = "game_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "game_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    private String location;

    @Column(name = "game_date")
    private LocalDateTime gameDate;

    //Getters and Setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Team getHomeTeam() {return homeTeam;}

    public void setHomeTeam(Team homeTeam) {this.homeTeam = homeTeam;}

    public Team getAwayTeam() {return awayTeam;}

    public void setAwayTeam(Team awayTeam) {this.awayTeam = awayTeam;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public LocalDateTime getGameDate() {return gameDate;}

    public void setGameDate(LocalDateTime gameDate) {this.gameDate = gameDate;}
}
