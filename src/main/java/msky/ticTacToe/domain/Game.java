package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Game {

    private String id;

    private Board board;

    private Players players;

    private Collection<WinCondition> winConditions;

    Game(String id, int columns, int rows, List<Player> players, Collection<WinCondition> winConditions) {
        this.id = id;
        this.board = new Board(columns, rows);
        this.players = new Players(players);
        this.winConditions = new ArrayList<>(winConditions);
    }

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .id(id)
                .turns(players.currentTurnsOrder())
                .build();
    }

    MoveResult make(Move move) {
        if (players.isNext(move.isMadeBy()) == false) {
            throw new IllegalMoveException("It's not your turn!");
        }
        if (board.isMarked(move.getMarkedField())) {
            throw new IllegalMoveException("Field already marked!");
        }
        board.mark(move.getMarkedField(), move.madeWith());

        // TODO: we can check the win conditions only for last marked field
        if (board.meetAny(winConditions)) {
            return MoveResult.WIN;
        } else {
            players.switchTurn();
            return MoveResult.NEXT_TURN;
        }

    }
}
