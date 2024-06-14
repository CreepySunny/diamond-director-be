package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;

@Entity
@Table(name = "play_fielder")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayFielderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "play_id")
    private PlayEntity play;

    @Column(name = "fielder_position")
    @Enumerated(EnumType.STRING)
    private PlayerPosition fielder;
}
