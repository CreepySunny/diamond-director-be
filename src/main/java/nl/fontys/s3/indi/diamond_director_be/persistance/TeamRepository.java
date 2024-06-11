package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    Optional<TeamEntity> findByTeamName(String teamName);
}
