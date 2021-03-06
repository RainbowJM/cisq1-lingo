package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> mark;
    private String attempt;

    public Feedback(List<Mark> feedback, String attempt) {
        this.mark = feedback;
        this.attempt = attempt;
    }

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

            // attempt: S O O R T
            // marks: C A P A C
            // prev: S P . . .
            // next: S P . . T

            if (mark.get(i) == Mark.CORRECT) {
                hint.add(letter);
            } else {
                hint.add(String.valueOf(prevHintLetter));
            }
        }
        return String.join("", hint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(mark, feedback.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "mark=" + mark +
                '}';
    }
}
