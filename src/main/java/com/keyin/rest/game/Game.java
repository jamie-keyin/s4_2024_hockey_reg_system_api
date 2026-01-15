package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
public class Game {
    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "game_sequence")
    private long id;

    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

    private String location;
    private Calendar date;

    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    public Team getHomeTeam(){
        return this.homeTeam;
    }
    public void setHomeTeam(Team team){
        this.homeTeam = team;
    }

    public Team getAwayTeam(){
        return this.awayTeam;
    }
    public void setAwayTeam(Team team){
        this.awayTeam = team;
    }

    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public Calendar getDate(){
        return this.date;
    }
    public void setDate(Calendar date){
        this.date = date;
    }
}
