# Diamond Director Backend

Diamond Director is a backend system for managing baseball teams, players, coaches, games, and statistics. It is built with Java, Spring Boot, and uses JPA for persistence. The project provides a RESTful API for team and player management, game tracking, and advanced baseball statistics calculations.

## Features

- **User Management**: Create and manage players, coaches, and users with authentication and role-based access.
- **Team Management**: Create teams, assign coaches and players, search for teams, and manage team rosters.
- **Game Management**: Create games, track plays, and associate games with teams and coaches.
- **Statistics**: Calculate advanced batting and pitching statistics for players using in-game play data.
- **Coach & Player Assignment**: Assign coaches and players to teams, and manage unassigned coaches.
- **REST API**: Exposes endpoints for all major operations, with security and validation.
- **Testing**: Comprehensive unit and integration tests using JUnit, Mockito, and Spring Test.

## Project Structure

- `src/main/java` - Main application source code
- `src/test/java` - Unit and integration tests
- `src/main/resources` - Application configuration and resources
- `build.gradle` - Gradle build configuration

## Key Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- H2/PostgreSQL (configurable)
- JUnit 5, Mockito
- Gradle

## Running the Application

1. **Clone the repository**
2. **Build the project**:
   ```sh
   ./gradlew build
   ```
3. **Run the application**:
   ```sh
   ./gradlew bootRun
   ```
4. **API Documentation**: The API is RESTful. You can use tools like Postman to interact with endpoints (see controller test files for example requests).

## Running Tests

To run all tests:
```sh
./gradlew test
```

## Example Endpoints

- `POST /coach` - Create a new coach
- `GET /coach/team/{teamName}` - Get coaches by team name
- `GET /coach/no-team` - List coaches not assigned to any team
- `POST /team` - Create a new team
- `GET /team/{userEmail}` - Get team by user email
- `PUT /team/assign/coach` - Assign coach to team
- `PUT /team/assign/player` - Assign player to team
- `GET /team` - List all teams
- `DELETE /team/{teamName}` - Delete a team by name
- `GET /team/search/{teamName}` - Search for teams by name

## Testing

- Unit and integration tests are located in `src/test/java`.
- Tests cover business logic, controllers, and repository operations.
- Example test classes:
  - `CoachControllerTest`
  - `TeamControllerTest`
  - `CalculateBattingStatisticsUseCaseImplTest`
  - `CalculatePitchingStatisticsUseCaseImplTest`
  - `CoachRepositoryTest`
