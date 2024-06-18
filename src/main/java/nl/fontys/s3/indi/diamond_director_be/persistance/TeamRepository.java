package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    @Query("SELECT g.homeTeam FROM GameEntity g WHERE g.id = :gameId")
    Optional<TeamEntity> findHomeTeamByGameId(@Param("gameId") Long gameId);

    @Query("SELECT g.awayTeam FROM GameEntity g WHERE g.id = :gameId")
    Optional<TeamEntity> findAwayTeamByGameId(@Param("gameId") Long gameId);

    Optional<TeamEntity> findByTeamName(String teamName);

    Optional<TeamEntity> findByCoachesContains(CoachEntity coachEntity);
}
