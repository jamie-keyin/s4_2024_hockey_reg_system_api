INSERT INTO division (id, name, start_birth_year, end_birth_year) VALUES
(1, 'Junior Division', 2005, 2010),
(2, 'Senior Division', 2000, 2004),
(3, 'Veteran Division', 1995, 1999),
(4, 'Elite Division', 1990, 1994);

INSERT INTO team (id, name, division_id) VALUES
(1, 'Ice Kings', 1),
(2, 'Fire Dragons', 1),
(3, 'Earth Warriors', 2),
(4, 'Wind Spirits', 2);

INSERT INTO player (id, first_name, last_name, birthday) VALUES
(1, 'John', 'Doe', '2005-01-15'),
(2, 'Jane', 'Smith', '2006-02-20'),
(3, 'Alice', 'Johnson', '2007-03-25'),
(4, 'Bob', 'Brown', '2008-04-30'),
(5, 'Charlie', 'Davis', '2009-05-05'),
(6, 'Eve', 'Wilson', '2010-06-10');

INSERT INTO game (id, home_team_id, away_team_id, location, scheduled_date) VALUES
(1, 1, 2, 'Stadium A', '2023-10-01'),
(2, 3, 4, 'Stadium B', '2023-10-02'),
(3, 1, 3, 'Stadium C', '2023-10-03'),
(4, 2, 4, 'Stadium D', '2023-10-04');

INSERT INTO team_players (team_id, players_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 1),
(4, 3);



