package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayFielderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayFielderRepository extends JpaRepository<PlayFielderEntity, Long> {
}
