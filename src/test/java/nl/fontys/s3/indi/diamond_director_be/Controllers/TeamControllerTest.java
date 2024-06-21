package nl.fontys.s3.indi.diamond_director_be.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.s3.indi.diamond_director_be.Business.Coach.AssignCoachToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Player.AssignPlayerToTeamUseCase;
import nl.fontys.s3.indi.diamond_director_be.Business.Team.*;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignCoachTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.AssignPlayerTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.CreateTeamRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Team.Team;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateTeamUseCase createTeamUseCase;

    @MockBean
    private FindTeamFromUserEmailUseCase findTeamFromUserEmailUseCase;

    @MockBean
    private AssignCoachToTeamUseCase assignCoachToTeamUseCase;

    @MockBean
    private AssignPlayerToTeamUseCase assignPlayerToTeamUseCase;

    @MockBean
    private FindAllTeamsUseCase findAllTeamsUseCase;

    @MockBean
    private DeleteTeamUsingTeamNameUseCase deleteTeamUsingTeamNameUseCase;

    @MockBean
    private SearchForTeamUsingTeamNameUseCase searchForTeamUsingTeamNameUseCase;

    @BeforeEach
    public void setUp() {
        when(createTeamUseCase.createTeam(any(CreateTeamRequest.class))).thenReturn(1L);
        when(findTeamFromUserEmailUseCase.findTeamFromUserEmail(anyString())).thenReturn(new Team());
        when(findAllTeamsUseCase.findAllTeams()).thenReturn(List.of(new Team()));
        when(searchForTeamUsingTeamNameUseCase.searchForTeam(anyString())).thenReturn(List.of(new Team()));
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testCreateNewTeam() throws Exception {
        CreateTeamRequest request = CreateTeamRequest.builder()
                .createCoachUserEmail("email@email.com")
                .teamName("TestTeam")
                .build();

        mockMvc.perform(post("/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(1L));

        verify(createTeamUseCase, times(1)).createTeam(any(CreateTeamRequest.class));
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testFindTeamByUserEmail() throws Exception {
        mockMvc.perform(get("/team/email@email.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());

        verify(findTeamFromUserEmailUseCase, times(1)).findTeamFromUserEmail("email@email.com");
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testAssignCoachTeam() throws Exception {
        AssignCoachTeamRequest request = AssignCoachTeamRequest.builder()
                .coachId(1L)
                .teamName("TestTeam")
                .build();

        mockMvc.perform(put("/team/assign/coach")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(assignCoachToTeamUseCase, times(1)).assignCoachToTeam(any(AssignCoachTeamRequest.class));
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testAssignPlayerTeam() throws Exception {
        AssignPlayerTeamRequest request = AssignPlayerTeamRequest.builder()
                .teamName("TestTeam")
                .playerId(1L)
                .build();

        mockMvc.perform(put("/team/assign/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(assignPlayerToTeamUseCase, times(1)).assignPlayerToTeam(any(AssignPlayerTeamRequest.class));
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testFindAllTeams() throws Exception {
        mockMvc.perform(get("/team"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(findAllTeamsUseCase, times(1)).findAllTeams();
    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "COACH")
    public void testDeleteTeamByName() throws Exception {
        mockMvc.perform(delete("/team/TestTeam"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(deleteTeamUsingTeamNameUseCase, times(1)).deleteTeamUsingTeamName("TestTeam");
    }

    @Test
    public void testSearchForTeamUsingTeamName() throws Exception {
        mockMvc.perform(get("/team/search/TestTeam"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(searchForTeamUsingTeamNameUseCase, times(1)).searchForTeam("TestTeam");
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
