package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

class Game {

    private String id;

    private Board board;

    Game(String id, int columns, int rows) {
        this.id = id;
        board = new Board(columns, rows);
    }

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .id(id)
                .build();
    }
}
