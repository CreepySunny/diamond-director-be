package nl.fontys.s3.indi.diamond_director_be.Persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CoachPosition;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoachRepositoryTest {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findByUserEntityEmail_WhenExistingEmail_ReturnsCoachEntity() {
        UserEntity user = UserEntity.builder()
                .role(UserRoles.COACH)
                .email("test@example.com")
                .password("password")
                .build();

        entityManager.persist(user);
        entityManager.flush();

        CoachEntity coach = new CoachEntity();
        coach.setFirstName("Test");
        coach.setLastName("Coach");
        coach.setUserEntity(user);
        coach.setPosition(CoachPosition.HEAD);
        coach.setCanScoreKeep(true);

        coachRepository.save(coach);
        entityManager.flush();

        Optional<CoachEntity> foundCoachOptional = coachRepository.findByUserEntityEmail("test@example.com");

        assertTrue(foundCoachOptional.isPresent());
        assertEquals("Test Coach", foundCoachOptional.get().getFirstName() + " " + foundCoachOptional.get().getLastName());
    }

    @Test
    public void findByUserEntityEmail_WhenNonExistingEmail_ReturnsEmptyOptional() {
        Optional<CoachEntity> foundCoachOptional = coachRepository.findByUserEntityEmail("nonexistent@example.com");

        assertFalse(foundCoachOptional.isPresent());
    }

    @Test
    @Transactional
    public void findByTeamIsNull_ReturnsListOfCoachesWithNullTeam() {
        TeamEntity teamA = TeamEntity.builder()
                .teamName("Team A")
                .build();

        entityManager.persist(teamA);
        entityManager.flush();

        UserEntity user1 = UserEntity.builder()
                .email("test1@example.com")
                .password("password")
                .role(UserRoles.COACH)
                .build();

        entityManager.persist(user1);

        CoachEntity coach1 = CoachEntity.builder()
                .firstName("Coach 1")
                .lastName("Lastname")
                .dateOfBirth(LocalDate.of(1980, 1, 1))
                .userEntity(user1)
                .position(CoachPosition.PITCHING)
                .canScoreKeep(false)
                .team(null)
                .build();
        UserEntity user2 = UserEntity.builder()
                .email("test2@example.com")
                .password("password")
                .role(UserRoles.COACH)
                .build();

        entityManager.persist(user2);

        CoachEntity coach2 = CoachEntity.builder()
                .firstName("Coach 2")
                .lastName("Lastname")
                .dateOfBirth(LocalDate.of(1985, 1, 1))
                .userEntity(user2)
                .position(CoachPosition.HEAD)
                .canScoreKeep(false)
                .team(teamA)
                .build();

        coachRepository.save(coach1);
        coachRepository.save(coach2);
        entityManager.flush();

        List<CoachEntity> coachesWithNullTeam = coachRepository.findByTeamIsNull();

        assertEquals(1, coachesWithNullTeam.size());
        assertEquals("Coach 1", coachesWithNullTeam.get(0).getFirstName());
    }
}