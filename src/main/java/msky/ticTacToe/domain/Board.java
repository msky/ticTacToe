package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import msky.ticTacToe.dto.BoardDTO;
import msky.ticTacToe.dto.FieldDTO;
import msky.ticTacToe.dto.MarkDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Board {

    private int columns;

    private int rows;

    private Marks marks = new Marks();

    Board(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    BoardDTO dto() {
        return BoardDTO.builder()
                .columns(columns)
                .rows(rows)
                .marks(marks.dto()).build();
    }

    void mark(Field field, Symbol symbol) {
        marks.addOn(field, symbol);
    }
}

class Marks {
    private Map<Field, Symbol> marks = new HashMap<>();

    Collection<MarkDTO> dto() {
        return marks.entrySet().stream()
                .map(
                        markedField -> MarkDTO.builder()
                                .on(markedField.getKey().dto())
                                .with(markedField.getValue().dto())
                                .build()
                )
                .collect(Collectors.toSet());
    }

    void addOn(Field field, Symbol symbol) {
        marks.put(field, symbol);
    }
}

@AllArgsConstructor
@EqualsAndHashCode
class Field {

    private int column;

    private int row;

    FieldDTO dto() {
        return new FieldDTO(column, row);
    }

    static Field fromDto(FieldDTO dto) {
        return new Field(dto.getColumn(), dto.getRow());
    }

}
