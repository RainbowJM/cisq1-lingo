package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        //given
        //when
        Feedback feedback = new Feedback(List.of
                (Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        //then
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed, not all letters are correct")
    void wordIsNotGuessed() {
        //given
        //when
        Feedback feedback = new Feedback(List.of
                (Mark.CORRECT,Mark.CORRECT,Mark.ABSENT,Mark.CORRECT,Mark.CORRECT));
        //then
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word guessed is invalid, because all letters are invalid")
    void guessIsInvalid() {
        //given
        //when
        Feedback feedback = new Feedback(List.of
                (Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID));
        //then
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("word is valid, only if all letters isnt marked invalid")
    void guessIsValid() {
        //given
        //when
        Feedback feedback = new Feedback(List.of
                (Mark.INVALID,Mark.CORRECT,Mark.ABSENT,Mark.CORRECT,Mark.PRESENT));
        //then
        assertFalse(feedback.isWordInvalid());
    }


}