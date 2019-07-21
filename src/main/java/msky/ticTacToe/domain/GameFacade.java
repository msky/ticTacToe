package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.GameDTO;
import msky.ticTacToe.dto.MoveDTO;
import msky.ticTacToe.dto.GameStateDTO;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.List;

@AllArgsConstructor
public class GameFacade {

    private GameFactory factory;

    private SetupRepository setupRepository;

    private MoveRepository moveRepository;

    public GameDTO startNewGame(List<PlayerDTO> players) {
        GameDTO game = factory.createStandardGame(players).dto();
        setupRepository.save(new GameSetup(game.getId(), players));

        return game;
    }

    public GameDTO load(String gameId) {
        // TODO: findOrThrow?
        Game game = rebuild(gameId);

        return game.dto();

    }

    private Game rebuild(String gameId) {
        GameSetup setup = setupRepository.findById(gameId);
        Game game = factory.createStandardGame(gameId, setup.getPlayers());
        moveRepository.getAllFor(gameId).stream().forEach(move -> game.make(move));

        return game;
    }

    public GameStateDTO make(MoveDTO moveDTO, String gameId) {
        Game game = rebuild(gameId);
        Move move = Move.fromDto(moveDTO);
        State result = game.make(move);

        moveRepository.save(move, gameId);

        return result.dto();
    }
}
