package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("throws exception when status is playing already")
    void statusPlaying() {
        Game game = new Game();
        game.startNewRound("wordToGuess");

        assertThrows(InvalidAction.class, () -> game.startNewRound("wordToGuess"));
    }

    @Test
    @DisplayName("throws exception when status is not playing when attempt a guess")
    void statusNotPlaying() {
        Game game = new Game();

        assertThrows(InvalidAction.class, () -> game.guess("attempt"));
    }

    @ParameterizedTest
    @MethodSource("provideGuessingExamples")
    @DisplayName("guessing a word")
    void guessing(String wordToGuess, String attempt, List<Mark> expectedMarks) {
        Game game = new Game();
        game.startNewRound(wordToGuess);
        game.guess(attempt);

        Feedback expected = new Feedback(expectedMarks, attempt);

        assertEquals(expected, game.showProgress().getFeedback().get(game.showProgress().getFeedback().size() - 1));
    }

    static Stream<Arguments> provideGuessingExamples() {
        return Stream.of(
                Arguments.of("BAARD", "BAARD", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "DRAAD", List.of(INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BARAA", List.of(CORRECT, CORRECT, PRESENT, PRESENT, ABSENT))
        );
    }

    @Test
    @DisplayName("game status is eliminated")
    void Eliminated() {
        Game game = new Game();
        game.startNewRound("BAARD");
        game.guess("BARST");
        game.guess("DRAAD");
        game.guess("BONJE");
        game.guess("BARRA");
        game.guess("BARAA");
        game.guess("BARAA");

        assertTrue(game.isPlayerEliminated());
        assertEquals(GameStatus.ELIMINATED, game.getStatus());

    }

    @Test
    @DisplayName("start a new round with length 5")
    void startNewRoundWithLength5() {
        Game game = new Game();
        game.startNewRound("SPORT");

        assertEquals(1, game.getRounds().size());
    }

    @Test
    @DisplayName("Player is playing")
    void playing() {
        Game game = new Game();
        game.startNewRound("SPORT");
        game.guess("SOORT");

        assertTrue(game.isPlaying());
    }

    @Test
    @DisplayName("Player is not playing")
    void notPlaying() {
        Game game = new Game();

        assertFalse(game.isPlaying());
    }

    @Test
    @DisplayName("Player not eliminated")
    void playerNotEliminated() {
        Game game = new Game();
        game.startNewRound("SPORT");
        game.guess("SOORT");

        assertFalse(game.isPlayerEliminated());
    }
}