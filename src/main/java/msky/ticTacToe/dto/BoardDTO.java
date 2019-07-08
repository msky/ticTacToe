package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;

@Builder
@Getter
@EqualsAndHashCode
public class BoardDTO {

    private Collection<MarkDTO> marks;

    private int columns;

    private int rows;

    public boolean isEmpty() {
        return marks == null || marks.isEmpty();
    }

}
