package msky.ticTacToe.domain;

public class IllegalSetupException extends RuntimeException {

    IllegalSetupException(String message) {
        super(message);
    }
}
