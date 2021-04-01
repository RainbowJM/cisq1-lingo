package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidAction;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int score = 0;

    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private final List<Round> rounds = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.START;

    public Game() {
    }

    public void startNewRound(String wordToGuess) {
        status = GameStatus.PLAYING;

        if (!rounds.isEmpty()) {
            this.score += this.getLastRound().calculateScore();
        }

        Round newRound = new Round(wordToGuess);
        rounds.add(newRound);
    }

    public void guess(String word) {
        if (status != GameStatus.PLAYING) {
            throw new InvalidAction("Player is not playing: " + status.toString());
        }

        Round round = this.getLastRound();
        System.out.println(round);
        System.out.println(word);
        round.guess(word);
        updateScore();

        if (round.getAttempt() > 5) {
            status = GameStatus.ELIMINATED;
        }
    }

    public boolean isWordGuessed() {
        // Use for automatically starting new round
        return false;
    }

    public Progress showProgress() {
        Round lastRound = this.getLastRound();

        return new Progress(
                this.id,
                this.status,
                lastRound.getFeedbackHistory(),
                score, // + lastRound.calculateScore(),
                lastRound.getHint()
        );
    }

    private int updateScore() {
        Round lastRound = this.getLastRound();

        score += lastRound.calculateScore();
        return score;
    }

    private Round getLastRound() {
        return rounds.get(rounds.size() - 1);
    }

    public boolean isPlayerEliminated() {
        return status == GameStatus.ELIMINATED;
    }

    public boolean isPlaying() {
        return status == GameStatus.PLAYING;
    }

    public int provideNewWordLength() {
        int currentLength = rounds.get(rounds.size() - 1).currentWordLength();

        if (currentLength > 6) {
            return 5;
        }

        return currentLength + 1;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
