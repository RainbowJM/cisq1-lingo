package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidAction;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidWordLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoundTest {

    @Test
    @DisplayName("throws exception when length of attempt word not equal to length wordToGuess")
    void lengthNotEqual() {
        Round round = new Round("BAARD");

        assertThrows(InvalidWordLength.class, () -> round.guess("BERGEN"));
    }

    @Test
    @DisplayName("throws exception when max attempt is reached")
    void maxAttempt() {
        Round round = new Round("BAARD");
        round.guess("BARST");
        round.guess("DRAAD");
        round.guess("BONJE");
        round.guess("BARRA");
        round.guess("BARAA");
        round.guess("BARAA");

        assertThrows(InvalidAction.class, () -> round.guess("BARAA"));
    }

    @ParameterizedTest
    @DisplayName("get next word length based on current word length")
    @MethodSource("provideWordLengthExamples")
    void nextWordLength(String wordToGuess, int nextLength) {
        Game game = new Game();
        game.startNewRound(wordToGuess);

        assertEquals(nextLength, game.provideNewWordLength());
    }

    static Stream<Arguments> provideWordLengthExamples() {
        return Stream.of(
                Arguments.of("baard", 6),
                Arguments.of("bergen", 7),
                Arguments.of("baarden", 5),
                Arguments.of("bord", 5)
        );
    }

    @Test
    @DisplayName("give first hint based on word to guess")
    void giveFirstHint() {
        Round round = new Round("APPLE");

        assertEquals("A....", round.firstHint());
    }

    @Test
    @DisplayName("check attempt of round")
    void checkAttempt() {
        String wordToGuess = "APPLE";
        String wordAttempt = "ADOPT";
        Round round = new Round(wordToGuess);
        round.guess(wordAttempt);

        assertEquals(1, round.getAttempt());
    }

    @Test
    @DisplayName("check the length of the word is 5")
    void checkLength() {
        String word = "APPLE";
        Round round = new Round(word);

        assertEquals(5, round.currentWordLength());
    }
}