package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
    
    public Game findByHomeTeam(String homeTeam);

    public Game findByLocation(String location);
}
