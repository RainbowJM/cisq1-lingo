package nl.hu.cisq1.lingo.trainer.domain;

import java.io.Serializable;

public class Game implements Serializable {
    private Long score;
    private GameStatus status = GameStatus.START;

    public Game(Long score) {
        this.score = score;
    }
}
