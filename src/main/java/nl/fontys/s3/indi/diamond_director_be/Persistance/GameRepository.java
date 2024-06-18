package nl.fontys.s3.indi.diamond_director_be.Persistance;

import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    @Query("SELECT g FROM GameEntity g " +
            "JOIN g.homeTeam h " +
            "JOIN h.coaches hc " +
            "JOIN hc.userEntity hcu " +
            "JOIN g.awayTeam a " +
            "JOIN a.coaches ac " +
            "JOIN ac.userEntity acu " +
            "WHERE hcu.email = :userEmail OR acu.email = :userEmail")
    List<GameEntity> findGamesByCoachUserEmail(@Param("userEmail") String userEmail);
}