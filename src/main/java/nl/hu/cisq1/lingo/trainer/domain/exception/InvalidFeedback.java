package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidFeedback extends RuntimeException {
    public InvalidFeedback() {
    }

    public InvalidFeedback(String message) {
        super(message);
    }
}
