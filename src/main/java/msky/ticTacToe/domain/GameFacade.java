package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.GameDTO;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.List;

@AllArgsConstructor
public class GameFacade {

    private GameFactory factory;

    private GameRepository gameRepository;

    public GameDTO createNewGame(List<PlayerDTO> players) {
        Game newGame = this.factory.createStandardGame(players);
        return gameRepository.save(newGame).dto();
    }

    public GameDTO load(String gameId) {
        // TODO: findOrThrow?
        return gameRepository.findById(gameId).dto();
    }
}
