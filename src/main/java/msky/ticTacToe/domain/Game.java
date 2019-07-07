package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

class Game {

    private String id;

    private Board board = new Board();

    Game(String id) {
        this.id = id;
    }

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .id(id)
                .build();
    }
}
