package nl.fontys.s3.indi.diamond_director_be.Persistance;

import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerEntity> findByTeamIsNull();
    Optional<PlayerEntity> findByUserEntityId(Long userId);
}