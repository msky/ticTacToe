package msky.ticTacToe.domain;

interface SetupRepository {

    GameSetup findById(String gameId);

    void save(GameSetup game);
}


