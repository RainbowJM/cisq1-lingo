package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidAction;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidWordLength;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Round {
    public static final int MAX_ATTEMPT = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wordToGuess;
    private int attempt;
    private String hint;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private final List<Feedback> feedbacks = new ArrayList<>();

    public Round() {
    }

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.hint = firstHint();
    }

    public void guess(String attemptWord) {
        if (attemptWord.length() != wordToGuess.length()) {
            throw new InvalidWordLength();
        }

        if (attempt > MAX_ATTEMPT) {
            throw new InvalidAction("Player is eliminated");
        }

        List<Mark> marks = giveListMark(attemptWord);
        Feedback feedback = new Feedback(marks, attemptWord);

        this.feedbacks.add(feedback);
        this.hint = feedback.giveHint(this.hint);

        this.attempt++;
//        calculateScore();
    }

    private List<Mark> giveListMark(String attemptWord) {
        String[] lettersWordToGuess = this.wordToGuess.split("");
        String[] lettersAttemptWord = attemptWord.split("");

        List<Mark> marks = new ArrayList<>();
        List<String> available = new ArrayList<>();

        for (int i = 0; i < lettersWordToGuess.length; i++) {
            String letterWordToGuess = lettersWordToGuess[i];
            String letterAttemptWord = lettersAttemptWord[i];

            if (letterAttemptWord.equals(letterWordToGuess)) {
                marks.add(Mark.CORRECT);
            } else {
                marks.add(null);
                available.add(letterWordToGuess);
            }
        }

        for (int j = 0; j < marks.size(); j++) {
            if (marks.get(j) != null) {
                continue;
            }

            String attemptLetter = lettersAttemptWord[j];
            if (available.contains(attemptLetter)) {
                marks.set(j, Mark.PRESENT);
                available.remove(attemptLetter);
            } else {
                marks.set(j, Mark.ABSENT);
            }
        }

        return marks;
    }

    public Feedback getLastFeedback() {
        return this.feedbacks.get(feedbacks.size() - 1);
    }

    public String firstHint() {
        String[] letters = wordToGuess.split("");
        return letters[0] + ".".repeat(currentWordLength() - 1);
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

    public String getHint() {
        return this.hint;
    }

    public int calculateScore() {
        return 5 * (5 - attempt) + 5;
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
