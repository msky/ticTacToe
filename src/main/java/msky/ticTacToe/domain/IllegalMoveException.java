package msky.ticTacToe.domain;

public class IllegalMoveException extends RuntimeException {

    IllegalMoveException(String message) {
        super(message);
    }
}
