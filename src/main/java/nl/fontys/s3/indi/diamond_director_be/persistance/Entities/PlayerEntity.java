package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerHanded;
import nl.fontys.s3.indi.diamond_director_be.domain.Player.PlayerPosition;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Table(name = "player")
@Builder @AllArgsConstructor @NoArgsConstructor @Entity @Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "handed_bats")
    @Enumerated(EnumType.STRING)
    private PlayerHanded handedBats;

    @Column(name = "handed_throws")
    @Enumerated(EnumType.STRING)
    private PlayerHanded handedThrows;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @OneToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @OneToMany(mappedBy = "batter")
    private List<PlayEntity> playsAsBatter;

    @OneToMany(mappedBy = "pitcher")
    private List<PlayEntity> playsAsPitcher;
}
