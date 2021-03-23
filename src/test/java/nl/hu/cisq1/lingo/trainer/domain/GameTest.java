package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("start a new round with length 5")
    void startNewRoundWithLength5(){
        Game game = new Game();
        game.startNewRound("SPORT");

        assertEquals(game.getRounds().size(), 1);
    }

    @Test
    @DisplayName("start a new round with length 6 and 7")
    void startNewRoundWithLength6_7() {
        Game game = new Game();
        game.startNewRound("WINNERS");
        game.startNewRound("WINNER");

        assertEquals(game.getRounds().size(), 2);
    }

    @Test
    @DisplayName("Player is playing")
    void playing() {
        Game game = new Game();
        game.startNewRound("SPORT");
        game.guess("SOORT");

        assertTrue(game.isPlaying());
    }

    @Test
    @DisplayName("Player is not playing")
    void notPlaying() {
        Game game = new Game();

        assertFalse(game.isPlaying());
    }

//    @Test
//    @DisplayName("Player not eliminated")
//    void playerNotEliminated(){
//        Game game = new Game();
//        game.startNewRound("SPORT");
//        game.guess("SOORT");
//
//        assertFalse(game.isPlayerEliminated());
//    }
}