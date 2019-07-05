package msky.ticTacToe.domain;

import msky.ticTacToe.dto.GameDTO;

class Game {

    private Board board = new Board();

    GameDTO dto() {
        return GameDTO.builder()
                .board(board.dto())
                .build();
    }
}
