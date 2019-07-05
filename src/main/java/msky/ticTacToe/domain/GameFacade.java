package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

public class GameFacade {

    private GameFactory factory;

    GameFacade(GameFactory factory) {
        this.factory = factory;
    }

    public GameDTO createNewGame() {
        return this.factory.createStandardGame().dto();
    }
}
