package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayRepository extends JpaRepository<PlayEntity, Long> {
    List<PlayEntity> findByBatter(PlayerEntity playerEntity);

    List<PlayEntity> findByPitcher(PlayerEntity playerEntity);
}
