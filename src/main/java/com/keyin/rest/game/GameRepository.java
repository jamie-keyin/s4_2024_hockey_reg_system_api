//Description: Repository class mirrored off the existing interfaces with a single custom query
//Author: DC Elliott
//Date: Jun 13/2025


package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByLocationContainingIgnoreCase(String location);
}

