package nl.fontys.s3.indi.diamond_director_be.Persistance;

import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayFielderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayFielderRepository extends JpaRepository<PlayFielderEntity, Long> {
}
