package com.keyin.rest.team;

import com.keyin.rest.player.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository  extends CrudRepository<Team, Long> {
    public List<Team> findByPlayers_LastName(String lastName);

    public List<Team> findByDivision_Name(String divisionName);

    public Team findByName(String name);
}
