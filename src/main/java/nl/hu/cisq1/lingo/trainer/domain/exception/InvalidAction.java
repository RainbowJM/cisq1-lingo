package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidAction extends RuntimeException {
    public InvalidAction() {
    }

    public InvalidAction(String message) {
        super(message);
    }
}
