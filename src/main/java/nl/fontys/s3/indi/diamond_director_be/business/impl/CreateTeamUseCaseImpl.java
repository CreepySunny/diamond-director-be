package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.Exceptions.NO_COACH_EXCEPTION;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateTeamUseCaseImpl implements CreateTeamUseCase {
    private TeamRepository teamRepository;
    private CoachRepository coachRepository;

    @Override
    public Long createTeam(CreateTeamRequest request) {
        CoachEntity foundCoach = coachRepository.findByUserEntityId(request.getCreatingUserId()).orElseThrow(NO_COACH_EXCEPTION::new);
        TeamEntity savedTeamEntity = TeamEntity.builder()
                .teamName(request.getTeamName())
                .coaches(List.of(foundCoach))
                .players(new ArrayList<>())
                .build();

        savedTeamEntity = teamRepository.save(savedTeamEntity);

        return savedTeamEntity.getTeamId();
    }
}
