package msky.ticTacToe.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BoardDTO {

    private List<FieldDTO> fields;

    public boolean isEmpty() {
        return fields == null || fields.isEmpty();
    }

}
