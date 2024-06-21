package nl.fontys.s3.indi.diamond_director_be.Controllers;

import nl.fontys.s3.indi.diamond_director_be.Business.Coach.CreateCoachUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesFromTeamNameUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.FindCoachesNoTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.UserRoles;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CoachPosition;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.Coaches;
import nl.fontys.s3.indi.diamond_director_be.Domain.Coach.CreateCoachRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoachControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCoachUseCase createCoachUseCase;

    @MockBean
    private FindCoachesFromTeamNameUseCase findCoachesFromTeamNameUseCase;

    @MockBean
    private FindCoachesNoTeamUseCase findCoachesNoTeamUseCase;

    @BeforeEach
    public void setUp() {
        when(createCoachUseCase.createCoach(any(CreateCoachRequest.class)))
                .thenReturn(CreateUserResponse.builder().userId(1L).build());
        when(findCoachesFromTeamNameUseCase.findCoachFromTeamName(anyString()))
                .thenReturn(List.of(new Coaches()));
        when(findCoachesNoTeamUseCase.findCoachesWithNoTeam())
                .thenReturn(List.of(new Coaches()));
    }

    @Test
    public void testCreateCoach() throws Exception {
        String jsonRequest = "{\"firstName\":\"Coach\",\"lastName\":\"123\",\"email\":\"123@asd.com\",\"password\":\"123\",\"dateOfBirth\":\"2023-05-03\",\"canScoreKeep\":false,\"position\":\"DEFENSE\"}";

        mockMvc.perform(post("/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(1L));

        verify(createCoachUseCase, times(1)).createCoach(any(CreateCoachRequest.class));
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testFindCoachesFromTeamName() throws Exception {
        mockMvc.perform(get("/coach/team/TestTeam"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(findCoachesFromTeamNameUseCase, times(1)).findCoachFromTeamName("TestTeam");
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testFindCoachesNoTeam() throws Exception {
        mockMvc.perform(get("/coach/no-team"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(findCoachesNoTeamUseCase, times(1)).findCoachesWithNoTeam();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}