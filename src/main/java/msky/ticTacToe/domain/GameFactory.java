package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class GameFactory {

    private static int DEFAULT_COLUMNS = 3;

    private static int DEFAULT_ROWS = 3;

    private static int DEFAULT_PLAYERS = 2;

    private IdGenerator idGenerator;

    Game createStandardGame(List<PlayerDTO> players) {

        // TODO extract default config with validation to separate class
        if (isOnePlayerPerSymbol(players) == false) {
            throw new IllegalSetupException("Players should pick unique symbols!");
        }

        if (DEFAULT_PLAYERS != players.size()) {
            throw new IllegalSetupException("Standard game can only be played by 2 players!");
        }

        return new Game(idGenerator.generate(),
                new BoardSize(DEFAULT_COLUMNS, DEFAULT_ROWS),
                players.stream().map(Player::fromDto)
                        .collect(Collectors.toList()),
                Arrays.asList(new VerticalLineDownsideDirection(3),
                        new HorizontalLineRightsideDirection(3),
                        new DiagonalLineDownsideDirection(3)));
    }

    private boolean isOnePlayerPerSymbol(List<PlayerDTO> players) {
        return players.stream()
                .map(PlayerDTO::getPlayingWith)
                .distinct()
                .count() == players.size();
    }

}

interface IdGenerator {

    String generate();

}
