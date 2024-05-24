package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private PlayResult playResult;

    @ManyToMany
    @JoinTable(
            name = "play_fielders",
            joinColumns = @JoinColumn(name = "play_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<PlayerEntity> fielders;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BaseRunnerEntity> baseRunners = new ArrayList<>();


    private Integer rbi;
    private Integer inning;

    @Enumerated(EnumType.STRING)
    private InningHalves half;
}
