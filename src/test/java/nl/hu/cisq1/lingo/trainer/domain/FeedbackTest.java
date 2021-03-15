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
                "APPLE");
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
                "ADOPT");
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
                "BPPLE");
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
                "BPPLE");
        //then
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("get marks from a feedback")
    void getMark(){
        List<Mark> marks = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback feedback = new Feedback(marks, "APPLE");

        assertEquals(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), feedback.getMark());
    }

    @Test
    @DisplayName("get attempt from feedback")
    void getAttempt(){
        List<Mark> marks = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback feedback = new Feedback(marks, "APPLE");

        assertEquals("APPLE",feedback.getAttempt());
    }

    @Test
    @DisplayName("feedback different, when value is different")
    void feedbackDifferent() {
        Feedback feedback1 = new Feedback(
                List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.CORRECT),
                "BPPLE");
        Feedback feedback2 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                "APPLE");

        assertNotEquals(feedback1, feedback2);
    }

    @Test
    @DisplayName("feedback the same, when value are the same")
    void feedbackSame() {
        Feedback feedback1 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");
        Feedback feedback2 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");

        assertEquals(feedback1, feedback2);
    }

    @Test
    @DisplayName("hashcode values the same")
    void hashCodeGeneratorSame() {
        Feedback feedback1 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");
        Feedback feedback2 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");

        assertEquals(feedback1.hashCode(), feedback2.hashCode());
    }

    @Test
    @DisplayName("hashcode values not same")
    void hashCodeGeneratorNotTheSame() {
        Feedback feedback1 = new Feedback(
                List.of(Mark.CORRECT, Mark.PRESENT),
                "APPLE");
        Feedback feedback2 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");

        assertNotEquals(feedback1.hashCode(), feedback2.hashCode());
    }

    @Test
    @DisplayName("contains class name")
    void convertedToString() {
        Feedback feedback1 = new Feedback(
                List.of(Mark.CORRECT, Mark.CORRECT),
                "APPLE");

        assertTrue(feedback1.toString().contains("Feedback"));
    }

    @ParameterizedTest
    @DisplayName("give hint based on attempt")
    @MethodSource("provideHintExamples")
    void getHint(String attempt, String previousHint, String expectedHint) {
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
