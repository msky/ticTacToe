package msky.ticTacToe.domain

import msky.ticTacToe.dto.FieldDTO
import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.MoveDTO
import msky.ticTacToe.dto.GameStateDTO
import msky.ticTacToe.dto.PlayerDTO
import spock.lang.Specification

class WinDyDiagonalLineSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    PlayerDTO playerX = testData.playerX

    PlayerDTO playerO = testData.playerO

    GameDTO game

    def "marking field in the middle of right-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2  O |   |   " +
                "1  X |   | X " +
                "0    |   | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(0,2)
            markXAt(0,1)
            markOAt(2,0)
            markXAt(2,1)

        when: "player O makes move at 2,0 "
            GameStateDTO result = markOAt(1, 1)
        then: "player O won"
            result == GameStateDTO.WIN
    }

    def "marking 3rd field at the right bottom of right-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2  O |   |   " +
                "1  X | O | X " +
                "0    |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(0,2)
            markXAt(0,1)
            markOAt(1,1)
            markXAt(2,1)

        when: "player O makes move at 2,0 "
            GameStateDTO result = markOAt(2, 0)
        then: "player O won"
            result == GameStateDTO.WIN
    }

    def "marking 3rd field at the left-top corner of right-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2    |   |   " +
                "1  X | O | X " +
                "0    |   | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(2,0)
            markXAt(0,1)
            markOAt(1,1)
            markXAt(2,1)

        when: "player O makes move at 0,2 "
            GameStateDTO result = markOAt(0, 2)
        then: "player O won"
            result == GameStateDTO.WIN
    }

    def "marking field in the middle of left-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2  O |   | X " +
                "1    |   |   " +
                "0  X |   | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(2,2)
            markOAt(0,2)
            markXAt(0,0)
            markOAt(2,0)

        when: "player X makes move at 1,1"
            GameStateDTO result = markXAt(1, 1)
        then: "player X won"
            result == GameStateDTO.WIN
    }

    def "marking field in the at the top of left-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2  O |   |   " +
                "1    | X |   " +
                "0  X |   | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(1,1)
            markOAt(0,2)
            markXAt(0,0)
            markOAt(2,0)

        when: "player X makes move at 2,2"
            GameStateDTO result = markXAt(2,2)
        then: "player X won"
            result == GameStateDTO.WIN
    }

    def "marking field in the at the bottom of left-side-down directed diagonal line ends the game" () {
        given: "game with board like" +
                "2  O |   | X " +
                "1    | X |   " +
                "0    |   | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(1,1)
            markOAt(0,2)
            markXAt(2,2)
            markOAt(2,0)

        when: "player X makes move at 0,0"
            GameStateDTO result = markXAt(0, 0)
        then: "player X won"
            result == GameStateDTO.WIN
    }

    private GameStateDTO markOAt(int column, int row) {
        return facade.make(MoveDTO.builder().madeBy(playerO).markedField(new FieldDTO(column, row)).build(), game.id)
    }

    private GameStateDTO markXAt(int column, int row) {
        return facade.make(MoveDTO.builder().madeBy(playerX).markedField(new FieldDTO(column, row)).build(), game.id)
    }

    private GameDTO createSample3x3Game(List<PlayerDTO> players = testData.samplePlayers()) {
        facade.startNewGame(players)
    }
}
