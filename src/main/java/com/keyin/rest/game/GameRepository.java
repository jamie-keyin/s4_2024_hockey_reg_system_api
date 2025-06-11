package com.keyin.rest.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.keyin.rest.team.Team;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List <Game>findByTeam_Home(Team homeTeam);
    List <Game>findByTeam_Away(Team awayTeam);
    List<Game> findByTeam_HomeId(Long homeTeamId);
    List<Game> findByTeam_AwayId(Long awayTeamId);
    List <Game>findByLocation(String location);
    List<Game>findByGameDate(LocalDateTime gameDate);

}
