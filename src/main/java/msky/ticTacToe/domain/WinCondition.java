package msky.ticTacToe.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface WinCondition {
    Collection<Collection<Field>> getWinningCombinationsFor(Field field);
}

/**
 * At the moment we need to check only the line down from given field
 * because if we check all the marked fields we eventually hit
 * the field at the top of winning sequence if there is any.
 **/
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
