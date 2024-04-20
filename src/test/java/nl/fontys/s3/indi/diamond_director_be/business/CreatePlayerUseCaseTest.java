package nl.fontys.s3.indi.diamond_director_be.business;

import jakarta.transaction.Transactional;
import nl.fontys.s3.indi.diamond_director_be.business.impl.CreatePlayerUseCaseImpl;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePlayerUseCaseTest {
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private CreatePlayerUseCaseImpl createPlayerUseCase;

    @Test
    @Transactional
    void testCreatePlayer() {
        // Given
        CreatePlayerRequest request = new CreatePlayerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setHanded_bats("Right");
        request.setHanded_throws("Right");
        request.setPosition(Position.starter);
        request.setDateOfBirth(LocalDate.of(1998, 1, 8));
        request.setHeight(180.0);
        request.setWeight(75.0);

        Date date = Date.from(request.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        PlayerEntity playerEntity = PlayerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .handed_bats(request.getHanded_bats())
                .handed_throws(request.getHanded_throws())
                .position(request.getPosition())
                .dateOfBirth(date)
                .height(request.getHeight())
                .weight(request.getWeight())
                .id(1L) // Assuming the player ID will be set after saving
                .build();

        when(playerRepository.save(any(PlayerEntity.class))).thenReturn(playerEntity);

        // When
        CreatePlayerResponse response = createPlayerUseCase.create(request);

        // Then
        verify(playerRepository, times(1)).save(any(PlayerEntity.class));
        assert response.getId() != null; // Assuming id field in response can't be null
    }
}