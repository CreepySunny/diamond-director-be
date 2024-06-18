package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.domain.Team.Team;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;

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
