package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidWordLength extends RuntimeException {
    public InvalidWordLength() {
    }

    public InvalidWordLength(String message) {
        super(message);
    }
}