//package nl.hu.cisq1.lingo.trainer.presentation;
//
//import nl.hu.cisq1.lingo.CiTestConfiguration;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Import(CiTestConfiguration.class)
//@AutoConfigureMockMvc
//class TrainerControllerWebTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("start a new game")
//    void startNewGame() throws Exception{
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trainer/games");
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.gameId", greaterThanOrEqualTo(0)))
//                .andExpect(jsonPath("$.score", is(0)))
//                .andExpect(jsonPath("$.status", is("PLAYING")))
//                .andExpect(jsonPath("$.feedbackHistory", hasSize(0)))
//                .andExpect(jsonPath("$.currentHint",hasSize(5)))
//                .andExpect(jsonPath("$.attemptsLeft",is(5)));
//    }
//
//}