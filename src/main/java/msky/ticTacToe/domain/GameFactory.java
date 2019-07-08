package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class GameFactory {

    private static int DEFAULT_COLUMNS = 3;

    private static int DEFAULT_ROWS = 3;

    private IdGenerator idGenerator;

    Game createStandardGame(List<PlayerDTO> players) {
        return new Game(idGenerator.generate(),
                DEFAULT_COLUMNS,
                DEFAULT_ROWS,
                players.stream().map(Player::fromDto)
                        .collect(Collectors.toList())
        );
    }

}

interface IdGenerator {

    String generate();

}
