//Description: Data Transfer Object for Team, cleans up the output and reduces the JSON within JSON look.
//Author: DC Elliott
//Date: Jun 13/2025

package com.keyin.rest.dto;

import com.keyin.rest.team.Team;
import com.keyin.rest.player.Player;

import java.util.List;
import java.util.stream.Collectors;

//Cuts down the output to just names
public class TeamDTO {
    private long id;
    private String name;
    private String divisionName;
    private List<String> playerNames;
    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.divisionName = team.getDivision() != null ? team.getDivision().getName() : null;
        this.playerNames = team.getPlayers() != null
                ? team.getPlayers().stream()
                .map(player -> player.getFirstName() + " " + player.getLastName())
                .collect(Collectors.toList())
                : null;
    }

    public long getId() {return id; }

    public String getName() {return name; }

    public String getDivisionName() { return divisionName; }

    public List<String> getPlayerNames() { return playerNames; }
}
