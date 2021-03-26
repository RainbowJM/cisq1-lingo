package nl.hu.cisq1.lingo.words.domain.exception;

public class WordLengthNotSupported extends RuntimeException {
    public WordLengthNotSupported(Integer length) {
        super("Could not find word of length " + length);
    }
}
