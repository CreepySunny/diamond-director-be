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
    @Column(name = "season")
    private String season;

    @NotBlank
    @Column(name = "home_score")
    private int homeScore;

    @NotBlank
    @Column(name = "away_score")
    private int awayScore;

    @NotBlank
    @Column(name = "inning")
    private int inning;

    @NotBlank
    @Column(name = "outs")
    private int outs;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<PlayEntity> plays;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private TeamEntity homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private TeamEntity awayTeam;
}
