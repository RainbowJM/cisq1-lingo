package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.application.exception.GameNotFound;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainerService {
    private final WordService wordService;
    private final SpringGameRepository gameRepository;

    public TrainerService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Progress startNewGame() {
        String wordToGuess = this.wordService.provideRandomWord(5);

        Game game = new Game();
        game.startNewRound(wordToGuess);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    public Progress startNewRound(Long gameId) {
        Game game = getGameById(gameId);

        Integer nextLength = game.provideNewWordLength();
        String wordToGuess = this.wordService.provideRandomWord(nextLength);
        game.startNewRound(wordToGuess);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    public Progress guess(Long gameId, String attempt) {
        Game game = getGameById(gameId);

        game.guess(attempt);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    private Game getGameById(Long gameId) {
        return this.gameRepository.findById(gameId)
                .orElseThrow(GameNotFound::new);
    }
}
