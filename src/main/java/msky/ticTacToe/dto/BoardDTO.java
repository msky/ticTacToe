package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class BoardDTO {

    private List<FieldDTO> fields;

    private int columns;

    private int rows;

    public boolean isEmpty() {
        return fields == null || fields.isEmpty();
    }

}
