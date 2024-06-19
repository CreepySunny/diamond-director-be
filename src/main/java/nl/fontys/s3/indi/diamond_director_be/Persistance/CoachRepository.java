package nl.fontys.s3.indi.diamond_director_be.Persistance;

import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, Long> {
    Optional<CoachEntity> findByUserEntityEmail(String email);

    List<CoachEntity> findByTeamIsNull();
}
