package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}