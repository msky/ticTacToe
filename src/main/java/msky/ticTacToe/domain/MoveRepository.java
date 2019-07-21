package msky.ticTacToe.domain;

import java.util.List;

interface MoveRepository {

    void save(Move move, String gameId);

    List<Move> getAllFor(String gameId);

}
