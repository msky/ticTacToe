package msky.ticTacToe.domain;

interface GameRepository {

    Game findById(String gameId);

    Game save(Game game);
}
