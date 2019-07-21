package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
class GameSetup {

    private String gameId;

    private List<PlayerDTO> players;

}
