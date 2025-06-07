package com.keyin.rest.team;

import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionRepository;
import com.keyin.rest.division.DivisionService;
import com.keyin.rest.player.Player;
import com.keyin.rest.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DivisionService divisionService;
    @Autowired
    private PlayerRepository playerRepository;

    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    public Team getTeamById(long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);

        return teamOptional.orElse(null);
    }

    public List<Team> getTeamsByPlayerLastName(String playerLastName) {
        return teamRepository.findByPlayers_LastName(playerLastName);
    }

    public List<Team> getTeamsByDivisionName(String divisionName) {
        return teamRepository.findByDivision_Name(divisionName);
    }

    public void deleteTeamById(long id) {
        teamRepository.deleteById(id);
    }

    public Team createTeam(Team newTeam) {
        String divisionName = newTeam.getDivision().getName();

        if (divisionName != null) {
            Division division = divisionService.findByName(divisionName);

            if (division == null) {
                division = divisionService.createDivision(newTeam.getDivision());
            }

            newTeam.setDivision(division);
        }

        return teamRepository.save(newTeam);
    }

    public Team updateTeam(long id, Team updatedTeam) {
        Optional<Team> teamToUpdateOptional = teamRepository.findById(id);

        if (teamToUpdateOptional.isPresent()) {
            Team teamToUpdate = teamToUpdateOptional.get();

            teamToUpdate.setName(updatedTeam.getName());
            teamToUpdate.setDivision(divisionService.getDivisionById(updatedTeam.getDivision().getId()));

            teamToUpdate.setPlayers(updatedTeam.getPlayers());
            // update players

            return teamRepository.save(teamToUpdate);
        }

        return null;
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    public Team addPlayersToTeam(Long teamId, List<Player> players) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isEmpty()) return null;

        Team team = teamOptional.get();

        List<Player> savedPlayers = new ArrayList<>();
        for (Player player : players) {
            Player saved = playerRepository.save(player);
            savedPlayers.add(saved);
        }

        team.getPlayers().addAll(savedPlayers);
        return teamRepository.save(team);
    }
}
