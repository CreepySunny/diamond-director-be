package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;

@Entity
@Data
@Builder
@Table(name = "")
@NoArgsConstructor
@AllArgsConstructor
public class LineUpCardEntryEntity {
    private int lineUpPosition;
    private PlayerPosition fieldingPosition;
    private PlayerEntity player;
}
