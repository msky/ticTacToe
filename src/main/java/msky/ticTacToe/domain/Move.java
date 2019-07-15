package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import msky.ticTacToe.dto.MoveDTO;
import msky.ticTacToe.dto.GameStateDTO;

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

    Player isMadeBy() { return madeBy;}

    static Move fromDto(MoveDTO dto) {
        return new Move(Player.fromDto(dto.getMadeBy()), Field.fromDto(dto.getMarkedField()));
    }

}
