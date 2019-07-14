package msky.ticTacToe.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * At the moment in all implementation of win condition we can assume that
 * given field is on the edge of winning sequence
 * because if we check all the marked fields it will eventually become true
 * (if there is any winning sequence marked).
 **/
interface WinCondition {
    Collection<Collection<Field>> getWinningCombinationsFor(Field field);
}

class VerticalLineDownsideDirection implements  WinCondition {

    private int length;

    VerticalLineDownsideDirection(int length) {
        this.length = length;
    }

    public Collection<Collection<Field>> getWinningCombinationsFor(Field field) {
        return Arrays.asList(getRequiredCombinationDownside(field));
    }

    private Collection<Field> getRequiredCombinationDownside(Field startField) {
        return IntStream.range(0, length)
                .mapToObj(i -> new Field(startField.getColumn(), startField.getRow() - i))
                .collect(Collectors.toList());
    }
}

class HorizontalLineRightsideDirection implements  WinCondition {

    private int length;

    HorizontalLineRightsideDirection(int length) {
        this.length = length;
    }

    public Collection<Collection<Field>> getWinningCombinationsFor(Field field) {
        return Arrays.asList(getRequiredCombinationDownside(field));
    }

    private Collection<Field> getRequiredCombinationDownside(Field startField) {
        return IntStream.range(0, length)
                .mapToObj(i -> new Field(startField.getColumn() + i, startField.getRow()))
                .collect(Collectors.toList());
    }
}

class DiagonalLineDownsideDirection implements  WinCondition {

    private int length;

    DiagonalLineDownsideDirection(int length) {
        this.length = length;
    }

    public Collection<Collection<Field>> getWinningCombinationsFor(Field field) {
        return Arrays.asList(getRequiredCombinationDownsideRight(field), getRequiredCombinationDownsideLeft(field));
    }

    private Collection<Field> getRequiredCombinationDownsideRight(Field startField) {
        return IntStream.range(0, length)
                .mapToObj(i -> new Field(startField.getColumn() + i, startField.getRow() - i))
                .collect(Collectors.toList());
    }

    private Collection<Field> getRequiredCombinationDownsideLeft(Field startField) {
        return IntStream.range(0, length)
                .mapToObj(i -> new Field(startField.getColumn() - i, startField.getRow() - i))
                .collect(Collectors.toList());
    }
}
