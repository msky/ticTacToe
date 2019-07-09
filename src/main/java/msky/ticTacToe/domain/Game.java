package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

import java.util.List;

class Game {

    private String id;

    private Board board;

    private Players players;

    Game(String id, int columns, int rows, List<Player> players) {
        this.id = id;
        this.board = new Board(columns, rows);
        this.players = new Players(players);
    }

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .id(id)
                .nextPlayer(players.checkNext().dto())
                .build();
    }

    MoveResult make(Move move) {
        board.mark(move.getMarkedField(), move.madeWith());
        players.switchTurn();
        return MoveResult.builder().nextPlayer(players.checkNext()).build();
    }
}
