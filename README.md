# DevOps Hockey Management System

A simple, user-friendly command-line interface (CLI) for managing hockey teams, divisions, and games. This Spring Boot application provides both REST API endpoints and an interactive CLI for managing hockey league data.

## Features

- **Team Management**: Create and manage hockey teams with division assignments
- **Division Management**: Automatically create divisions when they don't exist
- **Game Scheduling**: Schedule games between teams with location and timestamp
- **User-Friendly CLI**: Interactive command-line interface with helpful prompts and data display
- **REST API**: Full REST endpoints for programmatic access to all functionality

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Maven** (for building the project)
- **MySQL Server** (for database storage)

## Database Setup

1. **Install MySQL**: Download and install MySQL from [mysql.com](https://dev.mysql.com/downloads/mysql/)

2. **Create Database**: Create a database for the hockey system:
   ```sql
   CREATE DATABASE hockey_db_2024;
   ```

3. **Configure Database Connection**: 
   - Open `src/main/resources/application.properties`
   - Update the database password to match your MySQL installation:
   ```properties
   spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE
   ```

## Running the Application

1. **Clone the Repository**:
   ```bash
   git clone <your-repository-url>
   cd hockey-management-system
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   
   Alternatively, you can run the main class directly:
   - Navigate to `RestServiceApplication.java`
   - Run the main method in your IDE

## Using the CLI

Once the application starts, you'll see the interactive CLI menu:

```
Welcome to the DevOps Hockey System CLI!

1. Add a team
2. Add a game
0. Exit
Choose an option:
```

### Creating Teams

1. Select option `1` to add a team
2. Enter the team name when prompted
3. Enter the division name
   - If the division doesn't exist, it will be automatically created
   - This ensures seamless team creation without pre-existing division requirements

### Creating Games

1. Select option `2` to add a game
2. The CLI will display all current teams in a formatted table:
   ```
   Current Teams:
   ----------------------------
   ID    | Team Name
   ----------------------------
   1     | Toronto Maple Leafs
   2     | Montreal Canadiens
   ----------------------------
   ```
3. Enter the home team ID and away team ID from the displayed list
4. Enter the game location
5. The game will be scheduled with the current timestamp

### Best Practices

- **Create Teams First**: It's recommended to create teams before scheduling games
- **Use Postman for API Testing**: You can also interact with the system using REST endpoints via Postman or similar tools
- **Division Management**: The system automatically handles division creation, so you don't need to worry about pre-creating divisions

## Technical Architecture

### CommandLineRunner Implementation

```java
public class HockeyCLI implements CommandLineRunner {
```

The `CommandLineRunner` interface is used to execute code after the Spring Boot application context is loaded. This approach provides several benefits:

- **Automatic Execution**: The CLI starts immediately when the application launches
- **Spring Integration**: Full access to Spring's dependency injection and all autowired services
- **Clean Separation**: Keeps CLI logic separate from web controllers while maintaining access to business logic
- **Lifecycle Management**: Proper integration with Spring Boot's application lifecycle

### JpaRepository Choice

The project uses `JpaRepository` instead of `CrudRepository` in the "Game" folder for the following reasons:

- **Advanced Query Capabilities**: JpaRepository provides additional methods for potential future complex database operations

### User Experience Features

The CLI is designed with user-friendliness in mind:

- **Clear Menus**: Simple numbered options with descriptive text
- **Data Display**: When creating games, all available teams are listed with their IDs for easy reference
- **Automatic Creation**: Divisions are created automatically if they don't exist
- **Input Validation**: Clear error messages for invalid inputs
- **Success Feedback**: Confirmation messages for successful operations

## Development and Testing

### Using Postman

You can test the API endpoints using Postman:

1. Start the application
2. Import the API endpoints into Postman
3. Use POST requests to create teams and games
4. Use GET requests to retrieve data

### Database Verification

After creating teams and games through the CLI, you can verify the data in MySQL:

```sql
USE hockey_db_2024;
SELECT * FROM teams;
SELECT * FROM games;
SELECT * FROM divisions;
```

## Troubleshooting

### Common Issues

1. **Database Connection Error**: Verify MySQL is running and credentials are correct in `application.properties`
2. **Port Conflicts**: Default port is 8080, change in `application.properties` if needed
3. **Maven Build Issues**: Ensure Java 17+ is installed and JAVA_HOME is set correctly

### Error Messages

- `Error: Invalid choice. Try again.` - Enter a valid menu option (0, 1, or 2)
- Database errors - Check MySQL connection and credentials
- Team not found errors - Verify team IDs when creating games
- 
## CLI Output Configuration

To provide a clean, user-friendly CLI experience, several configuration settings have been applied to reduce verbose output:

### Current Configuration (Clean CLI)

```properties
spring.jpa.show-sql=false
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
logging.level.org.hibernate.SQL=off
logging.level.org.hibernate.type.descriptor.sql=off
```

### Why These Settings Were Applied

- **`spring.jpa.show-sql=false`**: Prevents Hibernate from printing SQL statements to the console, keeping the CLI output clean and focused on user interactions
- **`spring.autoconfigure.exclude=SecurityAutoConfiguration`**: Disables Spring Security's default configuration, eliminating security-related startup messages and login prompts that aren't needed for this CLI application
- **`logging.level.org.hibernate.SQL=off`**: Turns off Hibernate SQL logging at the framework level
- **`logging.level.org.hibernate.type.descriptor.sql=off`**: Disables parameter binding logging, which would otherwise show SQL parameter values

