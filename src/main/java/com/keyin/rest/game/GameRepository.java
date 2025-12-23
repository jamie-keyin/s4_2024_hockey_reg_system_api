package com.keyin.rest.game;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    public List<Game> findByHomeTeam_Name(String teamName);

    public List<Game> findByAwayTeam_Name(String teamName);

    public List<Game> findByLocation(String location);

    public List<Game> findByScheduledDateBetween(LocalDateTime start, LocalDateTime end);
} 