package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    @DisplayName("Give first hint based on word to guess")
    void giveFirstHint(){
        Round round = new Round("APPLE");

        assertEquals("A....",round.firstHint());
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
    @DisplayName("get list of feedback")
    void getFeedback(){
        String wordToGuess = "APPLE";
        String wordAttempt = "ADOPT";
        Round round = new Round(wordToGuess);
        round.guess(wordAttempt);

        assertEquals(new Feedback(round.giveListMark(wordAttempt),wordAttempt), round.getFeedbackHistory().get(0));
    }

    @Test
    @DisplayName("check the length of the word is 5")
    void checkLength() {
        String word = "APPLE";
        List<Mark> markList = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.CORRECT);
        Feedback feedback = new Feedback(markList, word);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);
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
        List<Mark> markList1 = List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);

        Feedback feedback1 = new Feedback(markList1, word1);
        List<Feedback> feedbacks1 = new ArrayList<>();
        feedbacks1.add(feedback1);

        Round round1 = new Round("SPORT");

        // round2
        String word2 = "SOORT";
        List<Mark> markList2 = List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);

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
        List<Mark> markList1 = List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);

        Feedback feedback1 = new Feedback(markList1, word1);
        List<Feedback> feedbacks1 = new ArrayList<>();
        feedbacks1.add(feedback1);

        Round round1 = new Round("SPORT");

        // round2
        String word2 = "SOORT";
        List<Mark> markList2 = List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);

        Feedback feedback2 = new Feedback(markList2, word2);
        List<Feedback> feedbacks2 = new ArrayList<>();
        feedbacks2.add(feedback2);

        Round round2 = new Round("SPORT");


        assertEquals(round1.hashCode(), round2.hashCode());
    }
}