package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.Bases;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.InningHalves;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.PlayResult;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@Table(name = "play")
@NoArgsConstructor
@AllArgsConstructor
public class PlayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "batter_id")
    private PlayerEntity batter;

    @ManyToOne
    @JoinColumn(name = "pitcher_id")
    private PlayerEntity pitcher;

    @Column(name = "play_result")
    @Enumerated(EnumType.STRING)
    private PlayResult playResult;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL)
    private List<PlayFielderEntity> fielders;

    @NotNull
    @Column(name = "rbi")
    private Integer rbi;

    @NotNull
    @Column(name = "inning")
    private Integer inning;

    @NotNull
    @Column(name = "half")
    @Enumerated(EnumType.STRING)
    private InningHalves half;
}
