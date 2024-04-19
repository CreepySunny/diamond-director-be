package nl.fontys.s3.indi.diamond_director_be.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.fontys.s3.indi.diamond_director_be.domain.Enums.Position;

import java.util.Date;

public class Player {
    private Long id;
    private String firstName;
    private String lastName;
    private String handed_bats;
    private String handed_throws;
    private Position position;
    private Date dateOfBirth;
    private Double height;
    private Double weight;
}
