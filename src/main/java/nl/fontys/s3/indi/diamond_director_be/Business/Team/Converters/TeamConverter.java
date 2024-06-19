package nl.fontys.s3.indi.diamond_director_be.Business.Team.Converters;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.Converter.CoachConverter;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.Converter.PlayerConverter;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.Domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.TeamEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TeamConverter {
    private TeamConverter(){}

    public static TeamEntity convert(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName(team.getTeamName());
        List<CoachEntity> coachEntities = team.getCoaches().stream()
                .map(CoachConverter::convert)
                .collect(Collectors.toList());
        teamEntity.setCoaches(coachEntities);
        List<PlayerEntity> playerEntities = team.getRooster().stream()
                .map(PlayerConverter::convert)
                .collect(Collectors.toList());
        teamEntity.setPlayers(playerEntities);
        return teamEntity;
    }

    public static Team convert(TeamEntity teamEntity) {
        Team team = Team.builder()
                .id(teamEntity.getTeamId())
                .teamName(teamEntity.getTeamName())
                .build();
        List<Coaches> coaches = teamEntity.getCoaches().stream()
                .map(CoachConverter::convert)
                .collect(Collectors.toList());
        team.setCoaches(coaches);
        List<Player> rooster = teamEntity.getPlayers().stream()
                .map(PlayerConverter::convert)
                .collect(Collectors.toList());
        team.setRooster(rooster);
        return team;
    }
}
