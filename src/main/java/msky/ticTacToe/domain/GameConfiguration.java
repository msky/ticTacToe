package msky.ticTacToe.domain;

import java.util.UUID;

class GameConfiguration {

    GameFacade gameFacade() {
        return new GameFacade(new GameFactory(() -> UUID.randomUUID().toString()),
                new InMemorySetupRepository(),
                new InMemoryMoveRepository());
    }
}
