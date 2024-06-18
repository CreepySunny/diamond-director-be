package nl.fontys.s3.indi.diamond_director_be.Domain.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponse {
    @NotBlank
    private String accessToken;
}
