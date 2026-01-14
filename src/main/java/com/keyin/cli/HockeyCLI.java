package com.keyin.cli;

import com.keyin.rest.game.Game;
import com.keyin.rest.game.GameService;
import com.keyin.rest.team.Team;
import com.keyin.rest.team.TeamService;
import com.keyin.rest.division.Division;
import com.keyin.rest.division.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class HockeyCLI implements CommandLineRunner {

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @Autowired
    private DivisionService divisionService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\nWelcome to the DevOps Hockey System CLI!");

        while (running) {
            System.out.println("\n1. Add a team");
            System.out.println("2. Add a game");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Enter team name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter division name: ");
                    String divisionName = scanner.nextLine();

                    Division division = divisionService.findByName(divisionName);
                    if (division == null) {
                        division = new Division();
                        division.setName(divisionName);
                        division = divisionService.createDivision(division);
                        System.out.println("New division created: " + divisionName);
                    }

                    Team team = new Team();
                    team.setName(name);
                    team.setDivision(division);

                    teamService.createTeam(team);
                    System.out.println("Success! Team created: " + name + " in division: " + divisionName);
                }

                case "2" -> {
                    System.out.println("\n  Current Teams:");
                    System.out.println("----------------------------");
                    System.out.printf("%-5s | %-20s\n", "ID", "Team Name");
                    System.out.println("----------------------------");

                    for (Team team : teamService.getAllTeams()) {
                        System.out.printf("%-5d | %-20s\n", team.getId(), team.getName());
                    }

                    System.out.print("\nEnter home team ID: ");
                    long homeId = Long.parseLong(scanner.nextLine());

                    System.out.print("Enter away team ID: ");
                    long awayId = Long.parseLong(scanner.nextLine());

                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();

                    LocalDateTime now = LocalDateTime.now();

                    Team home = teamService.getTeamById(homeId);
                    Team away = teamService.getTeamById(awayId);

                    Game game = new Game();
                    game.setHomeTeam(home);
                    game.setAwayTeam(away);
                    game.setLocation(location);
                    game.setScheduledDate(now);

                    gameService.createGame(game);
                    System.out.println("Success! Game created between " + home.getName() + " and " + away.getName() + " at " + location);
                }

                case "0" -> {
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                }

                default -> System.out.println("Error: Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
