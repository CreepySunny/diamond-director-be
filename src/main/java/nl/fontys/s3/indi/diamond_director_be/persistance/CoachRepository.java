package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, Long> {
    Optional<CoachEntity> findByUserEntityId(Long id);

    Optional<CoachEntity> findByUserEntityEmail(String email);

    List<CoachEntity> findCoachEntitiesByTeamIs(TeamEntity team);

    List<CoachEntity> findByTeamEmpty();
}
