package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    public Iterable<Game> findByHomeTeam(String homeTeam);
    public Iterable<Game> findByAwayTeam(String awayTeam);
}