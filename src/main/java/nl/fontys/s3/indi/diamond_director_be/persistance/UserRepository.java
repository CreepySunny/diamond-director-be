package nl.fontys.s3.indi.diamond_director_be.persistance;

import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
