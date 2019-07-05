package msky.ticTacToe.domain;

class GameConfiguration {

    GameFacade gameFacade() {
        return new GameFacade(new GameFactory());
    }
}
