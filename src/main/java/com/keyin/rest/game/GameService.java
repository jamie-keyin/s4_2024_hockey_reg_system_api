package com.keyin.rest.game;

import com.keyin.rest.team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CriteriaBuilder criteriaBuilder;

    @Autowired
    private EntityManager entityManager;

    public List<Game> getAllGames() {return (List<Game>) gameRepository.findAll();}


    public Game getGameById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        return gameOptional.orElse(null);
    }

    public void deleteGameByID(long id) {gameRepository.deleteById(id);}

    public Game createGame(Game newGame) { return gameRepository.save(newGame);}

    public Game updateGame(long id, Game updatedGame) {
        if (id != updatedGame.getId()) {
            throw new DataIntegrityViolationException("Game ID mismatch");
        }

        Optional<Game> gameToUpdateOptional = gameRepository.findById(id);

        if (gameToUpdateOptional.isPresent()) {
            Game gameToUpdate = gameToUpdateOptional.get();

            gameToUpdate.setAwayTeam(updatedGame.getAwayTeam());
            gameToUpdate.setHomeTeam(updatedGame.getHomeTeam());
            gameToUpdate.setLocation(updatedGame.getLocation());
            gameToUpdate.setGameDate(updatedGame.getGameDate());

            return gameRepository.save(gameToUpdate);
        }
        throw new EntityNotFoundException("Game not found with ID " + id);
    }

    /**
     * Performs a dynamic search for Games based on a map of criteria.
     * Keys in the map should correspond to the keys in GameSearchCriteria enum.
     * Multiple criteria are combined with an AND logic.
     *
     * @param searchTerms A map where keys are search criteria names (from GameSearchCriteria)
     * and values are the corresponding search values.
     * @return A list of games matching all provided criteria.
     * @throws IllegalArgumentException if any date/time format is invalid or a search value is not of the expected type.
     * @throws EntityNotFoundException if a team ID is provided for a non-existent team (optional validation within criteria processing).
     */
    public List<Game> findGamesByCriteria(Map<String, Object> searchTerms) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        Root<Game> game = cq.from(Game.class);

        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : searchTerms.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Using if-else if structure to apply predicates based on criteria key
            if (GameSearchCriteria.LOCATION.getKey().equals(key)) {
                if (value instanceof String) {
                    predicates.add(cb.equal(game.get("location"), (String) value));
                } else {
                    throw new IllegalArgumentException("Location search term must be a string.");
                }
            } else if (GameSearchCriteria.DATE.getKey().equals(key)) {
                if (value instanceof String) {
                    LocalDateTime startDateTime;
                    LocalDateTime endDateTime;
                    String dateInput = (String) value;

                    // Flexible date parsing logic
                    try {
                        LocalDateTime exactDateTime = LocalDateTime.parse(dateInput);
                        startDateTime = exactDateTime;
                        endDateTime = exactDateTime;
                    } catch (DateTimeParseException e1) {
                        try {
                            LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
                            startDateTime = date.atStartOfDay();
                            endDateTime = date.atTime(23, 59, 59, 999_999_999);
                        } catch (DateTimeParseException e2) {
                            try {
                                YearMonth yearMonth = YearMonth.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM"));
                                LocalDate firstDayOfMonth = yearMonth.atDay(1);
                                LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
                                startDateTime = firstDayOfMonth.atStartOfDay();
                                endDateTime = lastDayOfMonth.atTime(23, 59, 59, 999_999_999);
                            } catch (DateTimeParseException e3) {
                                try {
                                    Year year = Year.parse(dateInput, DateTimeFormatter.ofPattern("yyyy"));
                                    LocalDate firstDayOfYear = year.atDay(1);
                                    LocalDate lastDayOfYear = year.atMonth(12).atEndOfMonth();
                                    startDateTime = firstDayOfYear.atStartOfDay();
                                    endDateTime = lastDayOfYear.atTime(23, 59, 59, 999_999_999);
                                } catch (DateTimeParseException e4) {
                                    throw new IllegalArgumentException(
                                            "Invalid date format for search criteria '" + key + "': '" + dateInput + "'. " +
                                                    "Expected formats: 'YYYY-MM-DDTHH:MM:SS', 'YYYY-MM-DD', 'YYYY-MM', or 'YYYY'."
                                    );
                                }
                            }
                        }
                    }
                    predicates.add(cb.between(game.get("gameDate"), startDateTime, endDateTime));
                } else {
                    throw new IllegalArgumentException("Date search term must be a string.");
                }
            } else if (GameSearchCriteria.HOME_TEAM_ID.getKey().equals(key)) {
                if (value instanceof Number) {
                    Long teamId = ((Number) value).longValue();
                    // Optional: You could validate team existence here if you want an EntityNotFoundException
                    // teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Home Team not found with ID: " + teamId));
                    predicates.add(cb.equal(game.get("homeTeam").get("id"), teamId));
                } else {
                    throw new IllegalArgumentException("Home Team ID search term must be a number.");
                }
            } else if (GameSearchCriteria.AWAY_TEAM_ID.getKey().equals(key)) {
                if (value instanceof Number) {
                    Long teamId = ((Number) value).longValue();
                    // Optional: Validate team existence here
                    // teamRepository.findById(teamId).orElseThrow(() -> new new EntityNotFoundException("Away Team not found with ID: " + teamId));
                    predicates.add(cb.equal(game.get("awayTeam").get("id"), teamId));
                } else {
                    throw new IllegalArgumentException("Away Team ID search term must be a number.");
                }
            } else if (GameSearchCriteria.HOME_TEAM_NAME.getKey().equals(key)) {
                if (value instanceof String) {
                    String teamName = (String) value;
                    predicates.add(cb.equal(game.get("homeTeam").get("name"), teamName));
                } else {
                    throw new IllegalArgumentException("Home Team Name search term must be a string.");
                }
            } else if (GameSearchCriteria.AWAY_TEAM_NAME.getKey().equals(key)) {
                if (value instanceof String) {
                    String teamName = (String) value;
                    predicates.add(cb.equal(game.get("awayTeam").get("name"), teamName));
                } else {
                    throw new IllegalArgumentException("Away Team Name search term must be a string.");
                }
            }
            else {
                throw new IllegalArgumentException("Unknown search criteria key: " + key);
            }
        }

        // Combine all predicates with an AND operator (all conditions must be true)
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Execute the query and return the results
        return entityManager.createQuery(cq).getResultList();
    }
}
