package nl.fontys.s3.indi.diamond_director_be.business.Converters;

import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Team;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.CoachEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.PlayerEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.TeamEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TeamConverter {
    private TeamConverter(){}

    public static TeamEntity convert(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName(team.getTeamName());
        List<CoachEntity> coachEntities = team.getCoaches().stream()
                .map(coach -> CoachConverter.convert(coach))
                .collect(Collectors.toList());
        teamEntity.setCoaches(coachEntities);
        List<PlayerEntity> playerEntities = team.getRooster().stream()
                .map(player -> PlayerConverter.convert(player))
                .collect(Collectors.toList());
        teamEntity.setPlayers(playerEntities);
        return teamEntity;
    }

    public static Team convert(TeamEntity teamEntity) {
        Team team = Team.builder()
                .teamName(teamEntity.getTeamName())
                .build();
        List<Coaches> coaches = teamEntity.getCoaches().stream()
                .map(coachEntity -> CoachConverter.convert(coachEntity))
                .collect(Collectors.toList());
        team.setCoaches(coaches);
        List<Player> rooster = teamEntity.getPlayers().stream()
                .map(playerEntity -> PlayerConverter.convert(playerEntity))
                .collect(Collectors.toList());
        team.setRooster(rooster);
        return team;
    }
}
