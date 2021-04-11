package nl.hu.cisq1.lingo.trainer.domain;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@State(Scope.Benchmark)
@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<Mark> mark;

    private String attempt;

    public Feedback() {
    }

    public Feedback(List<Mark> feedback, String attempt) {
        this.mark = feedback;
        this.attempt = attempt;
    }

    @Benchmark
    public boolean isWordGuessed() {
        //.stream() maakt van een collectie (een verzameling elementen) een stream (een reeks elementen waar je over tijd bewerkingen op kunt doen)
        // .allMatch geeft een boolean terug op basis van de meegegeven functie
        //Mark.CORRECT::equals kan je herschrijven als: mark -> Mark.CORRECT.equals(mark)
        //::equals is een verwijzing naar de methodenaam, .equals is een aanroep van de methode
        return this.mark.stream()
                .allMatch(Mark.CORRECT::equals);
    }

    public boolean isWordInvalid() {
        return this.mark.stream()
                .allMatch(Mark.INVALID::equals);
    }

    public String giveHint(String previousHint) {
        String[] letters = this.attempt.split("");
        List<String> hint = new ArrayList<>();

        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            char prevHintLetter = previousHint.charAt(i);

            if (mark.get(i) == Mark.CORRECT) {
                hint.add(letter);
            } else {
                hint.add(String.valueOf(prevHintLetter));
            }
        }

        return String.join("", hint);
    }

    public List<Mark> getMark() {
        return mark;
    }

    public String getAttempt() {
        return attempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(mark, feedback.mark) && Objects.equals(attempt, feedback.attempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, attempt);
    }
}
