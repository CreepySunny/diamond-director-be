package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String season;

    private int homeScore, awayScore, inning, outs;

    @OneToOne
    @JoinColumn(name = "away_lineup_card_id")
    private LineUpCardEntity awayLineUpCard;

    @OneToOne
    @JoinColumn(name = "home_lineup_card_id")
    private LineUpCardEntity homeLineUpCard;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PlayEntity> plays;
}
