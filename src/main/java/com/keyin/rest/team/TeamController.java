package com.keyin.rest.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("team_search")
    public List<Team> getTeamsByPlayerLastName(@RequestParam(value = "player_last_name", required = false) String playerLastName,
                                               @RequestParam(value = "division_name", required = false) String divisionName) {
        List<Team> results = new ArrayList<Team>();

        if (playerLastName != null) {
            results =  teamService.getTeamsByPlayerLastName(playerLastName);
        } else if (divisionName != null) {
            results =  teamService.getTeamsByDivisionName(divisionName);
        }

        return results;
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable long id) {
        Team teamToReturn = teamService.getTeamById(id);

        if (teamToReturn == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(teamToReturn);
    }

    @PostMapping("/team")
    public Team createTeam(@RequestBody Team team) {
        System.out.println(team.getDivision());

        return teamService.createTeam(team);
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable long id, @RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(id, team));
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeamById(@PathVariable long id) {
        teamService.deleteTeamById(id);
    }
}
