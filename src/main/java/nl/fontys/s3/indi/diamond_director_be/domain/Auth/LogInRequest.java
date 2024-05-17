package nl.fontys.s3.indi.diamond_director_be.domain.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
