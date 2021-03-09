package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Round {
    private String wordToGuess;
    private int attempt;
    private List<Feedback> feedbacks = new ArrayList<>();

    public Round(String wordToGuess, int attempt, List<Feedback> feedbacks) {
        this.wordToGuess = wordToGuess;
        this.attempt = attempt;
        this.feedbacks = feedbacks;
    }

    public void guess(String attemptWord) {
        int maxAttempt = 5;

        if (getAttempt() <= maxAttempt) {
            String[] lettersWordToGuess = this.wordToGuess.split("");
            String[] lettersAttemptWord = attemptWord.split("");
            List<Mark> mark = new ArrayList<>();

            for (int i = 0; i < lettersWordToGuess.length; i++) {
                String letterWordToGuess = lettersWordToGuess[i];
                String letterAttemptWord = lettersAttemptWord[i];

                if (letterAttemptWord.equals(letterWordToGuess)) {
                    Mark markOfLetter = Mark.CORRECT;
                    mark.add(markOfLetter);
                }
            }
            Feedback feedbackList = new Feedback(mark, attemptWord);
            feedbacks.add(feedbackList);
        }
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

    @Override
    public String toString() {
        return "Round{" +
                "wordToGuess='" + wordToGuess + '\'' +
                ", attempt=" + attempt +
                ", feedbacks=" + feedbacks +
                '}';
    }
}
