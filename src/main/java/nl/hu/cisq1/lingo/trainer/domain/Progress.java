package nl.hu.cisq1.lingo.trainer.domain;

public class Progress {
    private Long id;
    private final GameStatus status;
    private final Feedback feedback;
    private final int score;
    private final String hint;

    public Progress(GameStatus status, Feedback feedback, int score, String hint) {
        this.status = status;
        this.feedback = feedback;
        this.score = score;
        this.hint = hint;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public int getScore() {
        return score;
    }

    public String getHint() {
        return hint;
    }

}
