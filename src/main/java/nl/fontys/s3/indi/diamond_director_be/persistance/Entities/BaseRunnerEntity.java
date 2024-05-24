package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.GameState.Enums.Bases;

@Entity
@Data
@Builder
@Table(name = "base_runner")
@NoArgsConstructor
@AllArgsConstructor
public class BaseRunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayEntity play;

    @Enumerated(EnumType.STRING)
    private Bases base;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;
}
