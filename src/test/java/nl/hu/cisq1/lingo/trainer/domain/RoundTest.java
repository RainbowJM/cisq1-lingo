package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RoundTest {
    static Stream<Arguments> provideGuessingExamples() {
        return Stream.of(
                Arguments.of("BAARD", "BAARD", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "DRAAD", List.of(ABSENT, PRESENT, CORRECT, PRESENT, CORRECT)),
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("BAARD", "BARAA", List.of(CORRECT, CORRECT, PRESENT, PRESENT, ABSENT))
        );
    }

    @ParameterizedTest
    @MethodSource("provideGuessingExamples")
    @DisplayName("guessing a word")
    void guessing(String wordToGuess, String attempt, List<Mark> expectedMarks) {
        Round round = new Round(wordToGuess);
        round.guess(attempt);

        Feedback expected = new Feedback(expectedMarks, attempt);
        assertEquals(expected, round.getLastFeedback());
    }


    @Test
    @DisplayName("give first hint based on word to guess")
    void giveFirstHint() {
        Round round = new Round("APPLE");

        assertEquals("A....", round.firstHint());
    }

    @Test
    @DisplayName("check attempt of round")
    void checkAttempt(){
        String wordToGuess = "APPLE";
        String wordAttempt = "ADOPT";
        Round round = new Round(wordToGuess);
        round.guess(wordAttempt);

        assertEquals(round.getAttempt(), 1);
    }

    @Test
    @DisplayName("check the length of the word is 5")
    void checkLength() {
        String word = "APPLE";
        Round round = new Round(word);

        assertEquals(5, round.currentWordLength());
    }

    @Test
    @DisplayName("round different, when value is different")
    void roundDifferent() {
        // round 1
        Round round1 = new Round("SPORT");
        round1.guess("SPORT");

        // round2
        Round round2 = new Round("SPORT");
        round2.guess("SOORT");


        assertNotEquals(round1, round2);
    }

    @Test
    @DisplayName("feedback same, when value is same")
    void roundSame() {
        // round 1
        String word1 = "SOORT";
        List<Mark> markList1 = List.of(CORRECT, Mark.ABSENT, CORRECT, CORRECT, CORRECT);

        Feedback feedback1 = new Feedback(markList1, word1);
        List<Feedback> feedbacks1 = new ArrayList<>();
        feedbacks1.add(feedback1);

        Round round1 = new Round("SPORT");

        // round2
        String word2 = "SOORT";
        List<Mark> markList2 = List.of(CORRECT, Mark.ABSENT, CORRECT, CORRECT, CORRECT);

        Feedback feedback2 = new Feedback(markList2, word2);
        List<Feedback> feedbacks2 = new ArrayList<>();
        feedbacks2.add(feedback2);

        Round round2 = new Round("SPORT");


        assertEquals(round1, round2);
    }

    @Test
    @DisplayName("hashcode from round class's values not same")
    void hashCodeGeneratorNotTheSameRound() {
        // round 1
        Round round1 = new Round("SPORT");
        round1.guess("SPORT");

        // round2
        Round round2 = new Round("SPORT");
        round2.guess("SOORT");

        assertNotEquals(round1.hashCode(), round2.hashCode());
    }

    @Test
    @DisplayName("hashcode values of round class the same")
    void hashCodeGeneratorSameRound() {
        // round 1
        String word1 = "SOORT";
        List<Mark> markList1 = List.of(CORRECT, Mark.ABSENT, CORRECT, CORRECT, CORRECT);

        Feedback feedback1 = new Feedback(markList1, word1);
        List<Feedback> feedbacks1 = new ArrayList<>();
        feedbacks1.add(feedback1);

        Round round1 = new Round("SPORT");

        // round2
        String word2 = "SOORT";
        List<Mark> markList2 = List.of(CORRECT, Mark.ABSENT, CORRECT, CORRECT, CORRECT);

        Feedback feedback2 = new Feedback(markList2, word2);
        List<Feedback> feedbacks2 = new ArrayList<>();
        feedbacks2.add(feedback2);

        Round round2 = new Round("SPORT");


        assertEquals(round1.hashCode(), round2.hashCode());
    }

//    @ParameterizedTest
//    @DisplayName("start a game")
//    @MethodSource("provideAttemptExamples")
//    void start(String attemptWord){
//        Round round = new Round("BAARD");
//        round.guess(attemptWord);
//
//        assertEquals(, );
//    }
//
//    static Stream<Arguments> provideAttemptExamples(){
//        return Stream.of(
//                Arguments.of("BERGEN"),
//                Arguments.of("BONJE"),
//                Arguments.of("BARST"),
//                Arguments.of("DRAAD"),
//                Arguments.of("BAARD")
//        );
//    }
}