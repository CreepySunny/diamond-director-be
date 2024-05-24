package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.LineUpCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineUpCardRepository extends JpaRepository<LineUpCardEntity, Long> {
}
