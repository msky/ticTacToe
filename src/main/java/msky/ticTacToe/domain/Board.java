package msky.ticTacToe.domain;

import lombok.EqualsAndHashCode;
import msky.ticTacToe.dto.BoardDTO;
import msky.ticTacToe.dto.FieldDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Board {

    private Fields fields = new Fields();

    BoardDTO dto() {
        return BoardDTO.builder().fields(fields.dto()).build();
    }

}

class Fields {
    private Map<Field, Symbol> markedFields = new HashMap<>();

    List<FieldDTO> dto() {
        return markedFields.entrySet().stream()
                .map(
                        markedField -> FieldDTO.builder().build()
                )
                .collect(Collectors.toList());
    }
}

@EqualsAndHashCode
class Field {

}
