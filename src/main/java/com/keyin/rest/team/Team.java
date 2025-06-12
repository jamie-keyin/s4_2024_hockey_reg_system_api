package com.keyin.rest.team;

import com.keyin.rest.division.Division;
import com.keyin.rest.player.Player;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {
    @Id
    @SequenceGenerator(name = "team_sequence", sequenceName = "team_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "team_sequence")
    private long id;

    private String name;

    @ManyToMany
    private List<Player> players;

    @ManyToOne
    private Division division;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
