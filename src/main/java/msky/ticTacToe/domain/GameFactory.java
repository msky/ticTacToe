package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class GameFactory {

    private IdGenerator idGenerator;

    Game createStandardGame() {
        return new Game(idGenerator.generate());
    }

}

interface IdGenerator {

    String generate();

}
