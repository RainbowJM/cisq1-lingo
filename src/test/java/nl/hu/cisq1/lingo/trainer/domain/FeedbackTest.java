package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        //given
        //when
        Feedback feedback = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                "apple");
        //then
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed, not all letters are correct")
    void wordIsNotGuessed() {
        //given
        //when
        Feedback feedback = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT),
                "adopt");
        //then
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word guessed is invalid, because all letters are invalid")
    void guessIsInvalid() {
        //given
        //when
        Feedback feedback = new Feedback(
                List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID),
                "appsa");
        //then
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("word is valid, only if all letters isnt marked invalid")
    void guessIsValid() {
        //given
        //when
        Feedback feedback = new Feedback(
                List.of(Mark.INVALID, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.PRESENT),
                "appls");
        //then
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("feedback different, when value is different")
    void feedbackDifferent() {
        Feedback feedback1 = new Feedback(List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT), "dpple");
        Feedback feedback2 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "apple");

        assertNotEquals(feedback1, feedback2);
    }

    @Test
    @DisplayName("feedback the same, when value are the same")
    void feedbackSame() {
        Feedback feedback1 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT), "apple");
        Feedback feedback2 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT), "apple");

        assertEquals(feedback1, feedback2);
    }

    @Test
    @DisplayName("hashcode values the same")
    void hashCodeGenerator() {
        Feedback feedback1 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT), "apple");
        Feedback feedback2 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT), "apple");

        assertEquals(feedback1, feedback2);
    }

    @Test
    @DisplayName("contains class name")
    void convertedToString() {
        Feedback feedback1 = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT), "apple");

        assertTrue(feedback1.toString().contains("Feedback"));
    }
//
//    @Test
//    @DisplayName("give hint based on attempt")
//    @MethodSource("provideHintExamples")
//    void getHint() {
//        // given
//        String attempt = "SOORT";
//        List<Mark> markList = List.of(Mark.CORRECT, Mark.ABSENT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT);
//        Feedback feedback = new Feedback(markList, attempt);
//        String previousHint = "SP...";
//
//        // when
//        String hint = feedback.giveHint(previousHint);
//
//        // then
//        String expectedHint = "SP..T";
//        assertEquals(expectedHint, hint);
//    }

    @ParameterizedTest
    @DisplayName("give hint based on attempt")
    @MethodSource("provideHintExamples")
    void getHint(String attempt,String previousHint, String expectedHint) {
        // given
//        String attempt = "SOORT";
        List<Mark> markList = List.of(Mark.CORRECT, Mark.ABSENT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT);
        Feedback feedback = new Feedback(markList, attempt);
//        String previousHint = "SP...";

        // when
        String hint = feedback.giveHint(previousHint);

        // then
//        String expectedHint = "SP..T";
        assertEquals(expectedHint, hint);
    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("SOORT", "SP...", "SP..T"),
                Arguments.of("APPLE", "A....", "A...E")
        );
    }

}
