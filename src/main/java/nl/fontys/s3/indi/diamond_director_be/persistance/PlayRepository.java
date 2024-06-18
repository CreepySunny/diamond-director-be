package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayRepository extends JpaRepository<PlayEntity, Long> {
    List<PlayEntity> findByBatter(PlayerEntity playerEntity);

    List<PlayEntity> findByPitcher(PlayerEntity playerEntity);


    @Query("SELECT p FROM PlayEntity p JOIN p.fielders f WHERE p.batter = :batter AND f.fielder = :fielderPosition")
    List<PlayEntity> findPlaysByBatterAndFielderPosition(@Param("batter") PlayerEntity batter, @Param("fielderPosition") PlayerPosition fielderPosition);

    @Query("SELECT p FROM PlayEntity p JOIN p.fielders f WHERE p.pitcher = :pitcher AND f.fielder = :fielderPosition")
    List<PlayEntity> findPlaysByPitcherAndFielderPosition(@Param("pitcher") PlayerEntity pitcher, @Param("fielderPosition") PlayerPosition fielderPosition);

    @Query("SELECT COUNT(p) FROM PlayEntity p JOIN p.fielders f WHERE p.batter = :batter AND f.fielder = :fielderPosition")
    Long countPlaysByBatterAndFielderPosition(@Param("batter") PlayerEntity batter, @Param("fielderPosition") PlayerPosition fielderPosition);

    @Query("SELECT COUNT(p) FROM PlayEntity p JOIN p.fielders f WHERE p.pitcher = :pitcher AND f.fielder = :fielderPosition")
    Long countPlaysByPitcherAndFielderPosition(@Param("pitcher") PlayerEntity pitcher, @Param("fielderPosition") PlayerPosition fielderPosition);

    @Query("SELECT COUNT(p) FROM PlayEntity p WHERE p.batter = :batter")
    Long countPlaysByBatter(@Param("batter") PlayerEntity batter);

    @Query("SELECT COUNT(p) FROM PlayEntity p WHERE p.pitcher = :pitcher")
    Long countPlaysByPitcher(@Param("pitcher") PlayerEntity pitcher);
}
