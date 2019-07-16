package msky.ticTacToe.domain

import msky.ticTacToe.dto.FieldDTO
import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.MoveDTO
import msky.ticTacToe.dto.GameStateDTO
import msky.ticTacToe.dto.PlayerDTO
import spock.lang.Specification

class WinByVerticalLineSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    PlayerDTO playerX = testData.playerX

    PlayerDTO playerO = testData.playerO

    GameDTO game

    def "marking 3rd field at the bottom of vertical line ends the game" () {
        given: "game with board like" +
            "2  X | O |   " +
            "1  X |   | O " +
            "0    |   |   " +
            "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(0,2)
            markOAt(1,2)
            markXAt(0,1)
            markOAt(2,1)

        when: "player X makes move at 0,0 "
            GameStateDTO result = markXAt(0, 0)
        then: "player X won"
            result == GameStateDTO.WIN
    }

    def "marking 3rd field at the top of vertical line ends the game" () {
        given: "game with board like" +
                "2    | O |   " +
                "1  X |   | O " +
                "0  X |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(0,0)
            markOAt(1,2)
            markXAt(0,1)
            markOAt(2,1)

        when: "player X makes move at 0,2 "
            GameStateDTO result = markXAt(0, 2)
        then: "player X won"
            result == GameStateDTO.WIN
    }

    def "marking 3rd field at the middle of vertical line ends the game" () {
        given: "game with board like" +
                "2  X | O |   " +
                "1    |   | O " +
                "0  X |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(0,0)
            markOAt(1,2)
            markXAt(0,2)
            markOAt(2,1)

        when: "player X makes move at 0,1 "
            GameStateDTO result = markXAt(0, 1)
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
        facade.createNewGame(players)
    }
}
