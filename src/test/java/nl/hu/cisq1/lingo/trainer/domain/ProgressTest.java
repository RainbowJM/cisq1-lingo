package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressTest {

    @Test
    @DisplayName("set id and get id of the progress")
    void setAndGetId() {
        Round round = new Round("APPLE");
        round.guess("ADOPT");

        Progress progress = new Progress(GameStatus.PLAYING, round.getFeedbackHistory().get(0), 0, "A....");

        progress.setId(0L);
        assertEquals(progress.getId(), 0L);

    }

    @Test
    @DisplayName("get game status of the progress")
    void getStatus() {
        Round round = new Round("APPLE");
        round.guess("ADOPT");

        Progress progress = new Progress(GameStatus.PLAYING, round.getFeedbackHistory().get(0), 0, "A....");

        progress.setId(0L);
        assertEquals(progress.getStatus(), GameStatus.PLAYING);

    }

    @Test
    @DisplayName("get feedback of the progress")
    void getFeedback() {
        Round round = new Round("APPLE");
        round.guess("ADOPT");

        Progress progress = new Progress(GameStatus.PLAYING, round.getFeedbackHistory().get(0), 0, "A....");

        assertEquals(progress.getFeedback(), round.getFeedbackHistory().get(0));

    }

    @Test
    @DisplayName("get score of the progress")
    void getScore() {
        Round round = new Round("APPLE");
        round.guess("ADOPT");

        Progress progress = new Progress(GameStatus.PLAYING, round.getFeedbackHistory().get(0), 0, "A....");

        assertEquals(progress.getScore(), 0L);

    }

    @Test
    @DisplayName("get hint of the progress")
    void getHint() {
        Round round = new Round("APPLE");
        round.guess("ADOPT");

        Progress progress = new Progress(GameStatus.PLAYING, round.getFeedbackHistory().get(0), 0, "A....");

        assertEquals(progress.getHint(), "A....");

    }
}