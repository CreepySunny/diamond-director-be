package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    @Query("SELECT g FROM GameEntity g " +
            "JOIN g.homeTeam h " +
            "JOIN h.coaches hc " +
            "JOIN hc.userEntity hcu " +
            "JOIN g.awayTeam a " +
            "JOIN a.coaches ac " +
            "JOIN ac.userEntity acu " +
            "WHERE hcu.id = :userId OR acu.id = :userId")
    List<GameEntity> findGamesByCoachId(@Param("userId") Long coachId);
}
