package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Round {
    private final String wordToGuess;
    private int attempt;
    private final List<Feedback> feedbacks;
    private List<Mark> mark = new ArrayList<>();


    public Round(String wordToGuess, int attempt, List<Feedback> feedbacks) {
        this.wordToGuess = wordToGuess;
        this.attempt = attempt;
        this.feedbacks = feedbacks;
    }

    public void guess(String attemptWord) {
        int maxAttempt = 5;

        if (getAttempt() <= maxAttempt) {
            mark = giveListMark(attemptWord);
            Feedback feedbackList = new Feedback(mark, attemptWord);
            feedbacks.add(feedbackList);
        }
        attempt++;
    }

    public List<Mark> giveListMark(String attemptWord) {
        String[] lettersWordToGuess = this.wordToGuess.split("");
        String[] lettersAttemptWord = attemptWord.split("");
        Mark markOfLetter;

        for (int i = 0; i < lettersWordToGuess.length; i++) {
            String letterWordToGuess = lettersWordToGuess[i];
            String letterAttemptWord = lettersAttemptWord[i];

            if (letterAttemptWord.equals(letterWordToGuess)) {
                markOfLetter = Mark.CORRECT;
                mark.add(markOfLetter);
            } else {
                markOfLetter = Mark.ABSENT;
            }
        }
        return mark;
    }

    public String giveHint() {

        return null;
    }

    public int getAttempt() {
        return attempt;
    }

    public List<Feedback> getFeedbackHistory() {
        return feedbacks;
    }

    public int currentWordLength() {
        return wordToGuess.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return attempt == round.attempt && Objects.equals(wordToGuess, round.wordToGuess) && Objects.equals(feedbacks, round.feedbacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordToGuess, attempt, feedbacks);
    }
}
