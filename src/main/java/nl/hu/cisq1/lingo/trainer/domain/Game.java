package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private Long score;
    private final List<Round> rounds = new ArrayList<>();
    private final int wordLength;
    private GameStatus status;

    public Game() {
        this.score = 0L;
        this.wordLength = 5;
    }

    public void startNewRound(String wordToGuess) {
        status = GameStatus.START;

        Round newRound = new Round(wordToGuess);
        newRound.giveHint();
        rounds.add(newRound);
        provideNewWordLength();
    }

    public void guess(String word) {
        status = GameStatus.PLAYING;

        Round round = rounds.get(rounds.size() - 1);
        round.guess(word);
        calculateScore();
        showProgress();
    }

    public Progress showProgress() {
        Round round = rounds.get(rounds.size() - 1);
        Feedback feedback = round.getFeedbackHistory().get(round.getFeedbackHistory().size() - 1);

        List<String> hintList = feedback.getHints();

        String hint = feedback.giveHint(hintList.get(hintList.size() - 1));


        return new Progress(getStatus(), feedback, score, hint);
    }

    public boolean isPlayerEliminated() {
        return status == GameStatus.ELIMINATED;
    }

    public boolean isPlaying() {
        return status == GameStatus.PLAYING;
    }

    public int provideNewWordLength() {
        int nextWordLength = rounds.get(rounds.size() - 1).currentWordLength();

        if (wordLength == 5) {
            nextWordLength = 6;
        } else if (wordLength == 6) {
            nextWordLength = 7;
        } else if (wordLength == 7) {
            nextWordLength = 5;
        }
        return nextWordLength;
    }

    public Long calculateScore() {
        Round round = rounds.get(rounds.size() - 1);
        int attempt = round.getAttempt();
        score = 5 * (5 - (long) attempt) + 5;
        return score;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Round> getRounds() {
        return rounds;
    }
}
