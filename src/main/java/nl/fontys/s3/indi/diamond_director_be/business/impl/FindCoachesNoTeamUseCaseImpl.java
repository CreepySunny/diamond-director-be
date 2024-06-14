package nl.fontys.s3.indi.diamond_director_be.business.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.Converters.CoachConverter;
import nl.fontys.s3.indi.diamond_director_be.business.FindCoachesNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.persistance.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindCoachesNoTeamUseCaseImpl implements FindCoachesNoTeamUseCase {
    private final CoachRepository coachRepository;

    @Override
    @Transactional
    public List<Coaches> findCoachesWithNoTeam() {
        return coachRepository.findByTeamEmpty().stream()
                .map(CoachConverter::convert)
                .toList();
    }
}
