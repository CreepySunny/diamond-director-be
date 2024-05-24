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
@Table(name = "")
@NoArgsConstructor
@AllArgsConstructor
public class LineUpCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TeamEntity team;
    private List<LineUpCardEntryEntity> lineUpCardEntries;
    private List<PlayerEntity> substitutions;
}
