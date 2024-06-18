package nl.fontys.s3.indi.diamond_director_be.Business.Coach.Converter;

import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;

public final class CoachConverter {
    private CoachConverter() {}

    public static Coaches convert(CoachEntity coachEntity) {
        return Coaches.builder()
                .id(coachEntity.getId())
                .firstName(coachEntity.getFirstName())
                .lastName(coachEntity.getLastName())
                .dateOfBirth(coachEntity.getDateOfBirth())
                .position(coachEntity.getPosition())
                .canScoreKeep(coachEntity.getCanScoreKeep())
                .build();
    }

    public static CoachEntity convert(Coaches coaches) {
        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setId(coaches.getId());
        coachEntity.setFirstName(coaches.getFirstName());
        coachEntity.setLastName(coaches.getLastName());
        coachEntity.setDateOfBirth(coaches.getDateOfBirth());
        coachEntity.setPosition(coaches.getPosition());
        coachEntity.setCanScoreKeep(coaches.getCanScoreKeep());
        return coachEntity;
    }
}
