package nl.hu.cisq1.lingo.trainer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.presentation.DTO.Guess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class TrainerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("start a new game")
    void startNewGame() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trainer/games");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.status", is("PLAYING")))
                .andExpect(jsonPath("$.feedback", hasSize(0)))
                .andExpect(jsonPath("$.hint", hasLength(5)));
    }

    @Test
    @DisplayName("guessing a word after starting a game")
    void guess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trainer/games");

        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        int id = JsonPath.read(response.getContentAsString(), "$.id");

        Guess guess = new Guess();
        guess.attempt = "BARST";
        String guessBody = new ObjectMapper().writeValueAsString(guess);

        RequestBuilder guessRequest = MockMvcRequestBuilders
                .post("/trainer/games/" + id + "/round/guess")
                .contentType(MediaType.APPLICATION_JSON)
                .content(guessBody);

        mockMvc.perform(guessRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.feedback", hasSize(1)));
    }
}