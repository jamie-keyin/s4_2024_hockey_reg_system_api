# /Game API Documentation

Based on code forked from Jamie Cornick https://github.com/jamie-keyin/s4_2024_hockey_reg_system_api

I have tried to stay with the original code structure and naming variants. For instance, the search endpoint is /game_search where I might have named it game/search.

A key difference in this implementation is that more specific exceptions have been added at the GameService level to improve error handling.

A robust search function has been implemented at the /search endpoint. It supports flexible date matching (by year, month, or day) and allows for combining multiple search criteria. The GameSearchCriteria enum was added to make this dynamic search logic more secure and maintainable by preventing illegal or unexpected search parameters.

## Get All Games
   Retrieves a list of all games in the database.

Endpoint: GET /game

Response: 200 OK

Example Request:

`curl http://localhost:8080/game`

## Get Game By ID
   Retrieves a single game by its unique ID.

Endpoint: GET /game/{id}

Response:

200 OK if the game is found.

404 Not Found if no game with the given ID exists.

Example Request (for game with ID 1):

`curl http://localhost:8080/game/1`

## Create a New Game
   Adds a new game to the database. The homeTeam and awayTeam objects must contain the ID of existing teams.

Endpoint: POST /game

Request Body: A JSON object representing the game.

Response: 200 OK with the created game object, including its new ID.

Example Request:

`curl -X POST http://localhost:8080/game \
-H "Content-Type: application/json" \
-d '{
"homeTeam": {
"id": 1
},
"awayTeam": {
"id": 2
},
"location": "Paradise",
"gameDate": "2025-06-14T19:00:00"
}
`

## Update a Game
   Updates the details of an existing game.

Endpoint: PUT /game/{id}

Request Body: A JSON object with the updated game data. The ID in the body must match the ID in the URL.

Response:

200 OK with the updated game object.

404 Not Found if the game doesn't exist.

Example Request (to update game with ID 1):

`curl -X PUT http://localhost:8080/game/1 \
-H "Content-Type: application/json" \
-d '{
"id": 1,
"homeTeam": {
"id": 1
},
"awayTeam": {
"id": 2
},
"location": "Mount Pearl",
"gameDate": "2025-06-15T20:00:00"
}`


##Delete a Game
   Deletes a game from the database by its ID.

Endpoint: DELETE /game/{id}

Response: 200 OK

Example Request (to delete game with ID 1):

`curl -X DELETE http://localhost:8080/game/1`

## Search for Games
   Performs a dynamic search for games based on one or more criteria. All provided criteria are combined with an AND condition. If no parameters are provided, it returns all games.

Endpoint: GET /game_search

Query Parameters:

location (String): The city or venue of the game.

date (String): The date of the game. Can be a full year (YYYY), year and month (YYYY-MM), a specific day (YYYY-MM-DD), or a full timestamp.

homeTeamId (Long): The ID of the home team.

awayTeamId (Long): The ID of the away team.

homeTeamName (String): The name of the home team.

awayTeamName (String): The name of the away team.

Response: 200 OK with a list of matching games.

### Search Examples
Find all games in a specific location:

`curl "http://localhost:8080/game_search?location=St. John's"`

Find all games played during a specific month (e.g., June 2025):

`curl "http://localhost:8080/game_search?date=2025-06"`

Find all games where a specific team was the home team:

`curl "http://localhost:8080/game_search?homeTeamName= Growlers"`

Find games by combining multiple criteria (location and home team ID):

`curl "http://localhost:8080/game_search?location=Paradise&homeTeamId=1"`

Find games on a specific day when a specific team was the away team:

`curl "http://localhost:8080/game_search?date=2025-06-14&awayTeamName=Sports Team`

