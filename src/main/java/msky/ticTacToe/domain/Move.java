package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import msky.ticTacToe.dto.MoveDTO;
import msky.ticTacToe.dto.MoveResultDTO;

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

@Builder
@Getter
class MoveResult {

    private Player nextPlayer;

    MoveResultDTO dto() {
        return MoveResultDTO.builder().nextPlayer(nextPlayer.dto()).build();
    }
}
