package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.GameDTO;

@AllArgsConstructor
public class GameFacade {

    private GameFactory factory;

    private GameRepository gameRepository;

    public GameDTO createNewGame() {
        Game newGame = this.factory.createStandardGame();
        return gameRepository.save(newGame).dto();
    }

    public GameDTO load(String gameId) {
        // TODO: findOrThrow?
        return gameRepository.findById(gameId).dto();
    }
}
