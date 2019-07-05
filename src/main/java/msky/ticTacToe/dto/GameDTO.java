package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameDTO {

    private BoardDTO board;

}
