package com.keyin.rest.team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository  extends CrudRepository<Team, Long> {
    public List<Team> findByPlayers_LastName(String lastName);

    public List<Team> findByDivision_Name(String divisionName);

    public Team findByName(String name);
}
