package nl.fontys.s3.indi.diamond_director_be.persistance.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Table(name = "player")
@Builder @AllArgsConstructor @NoArgsConstructor @Entity @Data
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "firstName")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 244)
    @Column(name = "lastName")
    private String lastName;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "handedBats")
    private String handed_bats;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "handedThrows")
    private String handed_throws;

    @NotNull
    @Column(name = "position")
    private Position position;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @Column(name = "height")
    private Double height;

    @NotNull
    @Column(name = "weight")
    private Double weight;
}
