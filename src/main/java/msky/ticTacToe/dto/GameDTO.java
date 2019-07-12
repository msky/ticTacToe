package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class GameDTO {

    private String id;

    private BoardDTO board;

    private List<PlayerDTO> turns;
}
