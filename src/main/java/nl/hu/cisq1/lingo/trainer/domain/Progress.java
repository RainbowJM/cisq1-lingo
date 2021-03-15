package nl.hu.cisq1.lingo.trainer.domain;

public class Progress {
    private Long id;
    private final GameStatus status;
    private final Long score;
    private final String hints;
    private final int roundNumber;

    public Progress(GameStatus status, Long score, String hints, int roundNumber) {
        this.status = status;
        this.score = score;
        this.hints = hints;
        this.roundNumber = roundNumber;
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

    public Long getScore() {
        return score;
    }

    public String getHints() {
        return hints;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
