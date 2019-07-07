package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class GameDTO {

    private String id;

    private BoardDTO board;

}
