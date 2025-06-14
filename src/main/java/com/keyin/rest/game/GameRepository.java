package com.keyin.rest.game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByHomeTeam_Name(String teamName);
    List<Game> findByAwayTeam_Name(String teamName);
    List<Game> findByHomeTeam_Id(Long id);
    List<Game> findByAwayTeam_Id(Long id);
    List<Game>findByLocation(String location);
    List<Game>findByGameDate(LocalDateTime gameDate);
    List<Game> findByGameDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    @Query("SELECT game FROM Game game WHERE game.homeTeam.id = :teamId OR game.awayTeam.id = :teamId")
    List<Game> findByAnyTeamId(@Param("teamId") Long teamId);
    @Query("SELECT game FROM Game game WHERE game.homeTeam.name = :teamName OR game.awayTeam.name = :teamName")
    List<Game> findByAnyTeamName(@Param("teamName") String teamName);
}
