package nl.fontys.s3.indi.diamond_director_be.domain.Player;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Player extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private PlayerHanded handed_bats;
    private PlayerHanded handed_throws;
    private PlayerPosition position;
    private LocalDate dateOfBirth;
    private Double height;
    private Double weight;
}
