package nl.fontys.s3.indi.diamond_director_be.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.Player;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Team {
    List<Coaches> coaches;
    List<Player> rooster;
    List<Player> lineUpCard;
    List<Player> substitutions;
}
