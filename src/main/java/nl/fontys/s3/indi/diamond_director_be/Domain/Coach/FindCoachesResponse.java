package nl.fontys.s3.indi.diamond_director_be.Domain.Coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindCoachesResponse {
    private HttpStatusCodeException exception;
    private List<Coaches> coaches;
}
