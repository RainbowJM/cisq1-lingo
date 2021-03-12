package nl.hu.cisq1.lingo.trainer.domain;

public class Progress {
    private Long id;
    private Long score;
    private String hints;
    private int roundNumber;

    public Progress(Long id, Long score, String hints, int roundNumber) {
        this.id = id;
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
