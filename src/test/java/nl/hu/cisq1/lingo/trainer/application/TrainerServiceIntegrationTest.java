package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
@Transactional
class TrainerServiceIntegrationTest {
    @Autowired
    private TrainerService service;

    @Test
    @DisplayName("start a new game with a new round")
    void startNewGame() {
        Progress progress = service.startNewGame();

        assertEquals(GameStatus.PLAYING, progress.getStatus());
        assertEquals(0, progress.getScore());
        assertEquals(5, progress.getHint().length());
        assertEquals(0, progress.getFeedback().size());
    }

}