package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "lineup_card")
@NoArgsConstructor
@AllArgsConstructor
public class LineUpCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @OneToMany(mappedBy = "lineupCard", cascade = CascadeType.ALL)
    private List<LineUpCardEntryEntity> lineUpCardEntries;

    @OneToMany(mappedBy = "lineupCard")
    private List<PlayerEntity> substitutions;
}
