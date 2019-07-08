package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class MarkDTO {

    private FieldDTO on;

    private SymbolDTO with;

}
