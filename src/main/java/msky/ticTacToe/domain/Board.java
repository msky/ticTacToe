package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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

    Board(BoardSize setup) {
        this.columns = setup.getColumns();
        this.rows = setup.getRows();
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

    boolean isMarked(Field field) {
        return marks.contains(field);
    }

    boolean meetsAny(Collection<WinCondition> winConditions) {
        return marks.meetAny(winConditions);
    }

    boolean allFieldsAreMarked() {
        int numberOfFields = columns * rows;
        return numberOfFields == marks.count();
    }
}

@Getter
@AllArgsConstructor
class BoardSize {
    private int columns;

    private int rows;
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

    boolean contains(Field field) {
        return marks.containsKey(field);
    }

    boolean meetAny(Collection<WinCondition> winConditions) {
        return winConditions.stream()
                .anyMatch(winCondition -> isAnyCombinationForConditionAvailable(winCondition));
    }

    int count() {
        return marks.size();
    }

    private boolean isAnyCombinationForConditionAvailable(WinCondition winCondition) {
        return marks.entrySet().stream()
                .anyMatch(mark ->
                        isAnyCombinationAvailable(winCondition.getWinningCombinationsFor(mark.getKey()), mark.getValue()));
    }

    private boolean isAnyCombinationAvailable(Collection<Collection<Field>> winCombinations, Symbol symbol) {
        Collection<Field> markedFields = marks.keySet();
        return winCombinations.stream().anyMatch(combination -> markedFields.containsAll(combination) &&
                allFieldsAreMarkedBy(combination, symbol));
    }

    private boolean allFieldsAreMarkedBy(Collection<Field> fields, Symbol symbol) {
        return fields.stream().allMatch(field -> marks.get(field) == symbol);
    }
}

@AllArgsConstructor
@EqualsAndHashCode
@Getter
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
