package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private int attempt;
    private List<Feedback> feedbacks = new ArrayList<>();

    public Round(String wordToGuess, int attempt, List<Feedback> feedbacks) {
        this.wordToGuess = wordToGuess;
        this.attempt = attempt;
        this.feedbacks = feedbacks;
    }

    public void guess(String attempt){
        if (getAttempt() <= 5){

        }
    }

    public int getAttempt() {
        return attempt;
    }

    public List<Feedback> getFeedbackHistory() {
        return feedbacks;
    }
}
