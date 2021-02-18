package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Mark;

import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> mark;

    public Feedback(List<Mark> feedback) {
        this.mark = feedback;
    }

    public static boolean correct(String woord) {
        return true;
    }

    public static boolean invalid(String woord) {
        return false;
    }

    public boolean isWordGuessed(){
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
