package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class MoveDTO {

    private String gameId;

    private PlayerDTO madeBy;

    private FieldDTO markedField;
}
