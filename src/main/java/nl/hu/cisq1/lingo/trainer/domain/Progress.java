package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Progress {
    private final Long id;
    private final GameStatus status;
    private final List<Feedback> feedback;
    private final int score;
    private final String hint;

    public Progress(Long id, GameStatus status, List<Feedback> feedback, int score, String hint) {
        this.id = id;
        this.status = status;
        this.feedback = feedback;
        this.score = score;
        this.hint = hint;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public int getScore() {
        return score;
    }

    public String getHint() {
        return hint;
    }

}
