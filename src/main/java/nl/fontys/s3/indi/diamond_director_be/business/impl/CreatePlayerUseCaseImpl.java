package nl.fontys.s3.indi.diamond_director_be.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreatePlayerUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreatePlayerResponse;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreatePlayerUseCaseImpl implements CreatePlayerUseCase {
    private PlayerRepository playerRepository;
    @Override
    @Transactional
    public CreatePlayerResponse create(CreatePlayerRequest request) {

        Date date = Date.from(request.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant());

        PlayerEntity savedPlayer = playerRepository.save(PlayerEntity.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .handed_bats(request.getHanded_bats())
                        .dateOfBirth(date)
                        .handed_throws(request.getHanded_throws())
                        .position(request.getPosition())
                        .height(request.getHeight())
                        .weight(request.getWeight())
                .build());

        return CreatePlayerResponse.builder()
                .id(savedPlayer.getId())
                .build();
    }
}
