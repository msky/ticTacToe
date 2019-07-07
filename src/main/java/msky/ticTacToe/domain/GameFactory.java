package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class GameFactory {

    private static int DEFAULT_COLUMNS = 3;

    private static int DEFAULT_ROWS = 3;

    private IdGenerator idGenerator;

    Game createStandardGame() {
        return new Game(idGenerator.generate(),
                DEFAULT_COLUMNS,
                DEFAULT_ROWS
                );
    }

}

interface IdGenerator {

    String generate();

}
