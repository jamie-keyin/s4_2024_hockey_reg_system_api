package com.keyin.rest.games;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface GameRepository extends CrudRepository<Game, Long>{

}

