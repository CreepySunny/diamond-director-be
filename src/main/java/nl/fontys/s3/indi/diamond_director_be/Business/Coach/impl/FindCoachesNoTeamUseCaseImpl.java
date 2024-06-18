package nl.fontys.s3.indi.diamond_director_be.Business.Coach.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Converter.CoachConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Persistance.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindCoachesNoTeamUseCaseImpl implements FindCoachesNoTeamUseCase {
    private final CoachRepository coachRepository;

    @Override
    @Transactional
    public List<Coaches> findCoachesWithNoTeam() {
        return coachRepository.findByTeamIsNull().stream()
                .map(CoachConverter::convert)
                .toList();
    }
}
