package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;
import msky.ticTacToe.dto.GameStateDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static msky.ticTacToe.domain.State.*;

class Game {

    private String id;

    private Board board;

    private Players players;

    private Collection<WinCondition> winConditions;

    private State state;

    private Player winner;

    Game(String id, BoardSize size, List<Player> players, Collection<WinCondition> winConditions) {
        this.id = id;
        this.board = new Board(size);
        this.players = new Players(players);
        this.winConditions = new ArrayList<>(winConditions);
        this.state = WAITING_FOR_NEXT_TURN;
    }

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .id(id)
                .turns(players.currentTurnsOrder())
                .winner(winner != null ? winner.dto() : null)
                .state(state.dto())
                .build();
    }

    State make(Move move) {
        validate(move);
        board.mark(move.getMarkedField(), move.madeWith());

        // TODO: we can check the win conditions only for last marked field
        if (board.meetsAny(winConditions)) {
            state = WIN;
            winner = move.getPlayer();
        } else if (board.allFieldsAreMarked()) {
            state = DRAW;
        } else {
            players.switchTurn();
        }

        return state;
    }

    private void validate(Move move) {
        if (isOver()) {
            throw new IllegalMoveException("The game is over, you can no longer make any moves");
        }
        if (move.isMadeBy(players.checkNext()) == false) {
            throw new IllegalMoveException("It's not your turn!");
        }
        if (board.isMarked(move.getMarkedField())) {
            throw new IllegalMoveException("Field already marked!");
        }
    }

    private boolean isOver() {
        return state != WAITING_FOR_NEXT_TURN;
    }

}

enum State {
    WAITING_FOR_NEXT_TURN, WIN, DRAW;

    public GameStateDTO dto() {
        return GameStateDTO.valueOf(name());
    }
}
