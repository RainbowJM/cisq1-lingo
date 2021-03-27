package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TrainerServiceTest {

    @Test
    @DisplayName("start a new game with a new round")
    void startNewGame() {
        WordService mockWordService = mock(WordService.class);
        when(mockWordService.provideRandomWord(5)).thenReturn("BAARD");

        SpringGameRepository mockRepository = mock(SpringGameRepository.class);

        TrainerService trainerService = new TrainerService(mockWordService, mockRepository);
        Progress progress = trainerService.startNewGame();

        List<String> hint = List.of("B", ".", ".", ".", ".");

        assertEquals(String.join("", hint), progress.getHint());
        assertEquals(GameStatus.PLAYING, progress.getStatus());
    }
}