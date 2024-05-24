package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayRepository extends JpaRepository<PlayEntity, Long> {
}
