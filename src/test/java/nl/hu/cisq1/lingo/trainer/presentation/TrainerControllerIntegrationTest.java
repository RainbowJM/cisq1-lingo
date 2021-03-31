package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainerControllerIntegrationTest {

    @MockBean
    private SpringWordRepository wordRepository;

    @MockBean
    private SpringGameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("start a new game")
    void startNewGame() throws Exception {
        when(wordRepository.findRandomWordByLength(5))
                .thenReturn(Optional.of(new Word("BAARD")));

        RequestBuilder request = MockMvcRequestBuilders.post("/trainer/games");

        String hint = "B....";

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("PLAYING")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.hint", hasLength(5)))
                .andExpect(jsonPath("$.hint", equalTo(hint)))
                .andExpect(jsonPath("$.feedback", hasSize(0)));
    }

//    @Test
//    @DisplayName("start new round")
//    void startNewRound() throws Exception {
//        Game game = new Game();
//        game.startNewRound("BAARD");
//        game.guess("BAARD");
//
//        when(gameRepository.findById(0L))
//                .thenReturn(Optional.of(game));
//
//        when(wordRepository.findRandomWordByLength(6))
//                .thenReturn(Optional.of(new Word("HOEDEN")));
//
//        RequestBuilder request = MockMvcRequestBuilders.post("/trainer/games/0/round");
//
//        String hint = "H.....";
//
//        mockMvc.perform(request).
//                andExpect(status().isOk()).
//                andExpect(jsonPath("$.status", is("PLAYING")))
//                .andExpect(jsonPath("$.score", is(25)))
//                .andExpect(jsonPath("$.hint", hasSize(6)))
//                .andExpect(jsonPath("$.hint", equalTo(hint)))
//                .andExpect(jsonPath("$.feedback", hasSize(0)));
//    }

}
