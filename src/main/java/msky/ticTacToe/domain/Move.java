package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.MoveDTO;

@AllArgsConstructor
class Move {

    private Player madeBy;

    private Field madeOn;

    Field getMarkedField() {
        return madeOn;
    }

    Symbol madeWith() {
        return madeBy.playingWith();
    }

    static Move fromDto(MoveDTO dto) {
        return new Move(Player.fromDto(dto.getMadeBy()), Field.fromDto(dto.getMarkedField()));
    }

}
