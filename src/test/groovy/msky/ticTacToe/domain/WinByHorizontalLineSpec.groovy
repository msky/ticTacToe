package msky.ticTacToe.domain

import msky.ticTacToe.dto.FieldDTO
import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.MoveDTO
import msky.ticTacToe.dto.MoveResultDTO
import msky.ticTacToe.dto.PlayerDTO
import spock.lang.Specification

class WinByHorizontalLineSpec extends  Specification {
    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    PlayerDTO playerX = testData.playerX

    PlayerDTO playerO = testData.playerO

    GameDTO game

    def "marking 3rd field at the right end of horizontal line ends the game" () {
        given: "game with board like" +
                "2  O | O |   " +
                "1  X |   | X " +
                "0    |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(0,2)
            markXAt(0,1)
            markOAt(1,2)
            markXAt(2,1)

        when: "player O make move at 2,2"
            MoveResultDTO result = markOAt(2,2)
        then: "player O won"
            result == MoveResultDTO.WIN
    }

    def "marking 3rd field at the left end of horizontal line ends the game" () {
        given: "game with board like" +
                "2    | O | O " +
                "1  X |   | X " +
                "0    |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(2,2)
            markXAt(0,1)
            markOAt(1,2)
            markXAt(2,1)

        when: "player O make move at 0,2"
            MoveResultDTO result = markOAt(0,2)
        then: "player O won"
            result == MoveResultDTO.WIN
    }

    def "marking 3rd field in the middle of horizontal line ends the game" () {
        given: "game with board like" +
                "2  O |   | O " +
                "1  X |   | X " +
                "0    |   |   " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerO, playerX])
            markOAt(0,2)
            markXAt(0,1)
            markOAt(2,2)
            markXAt(2,1)

        when: "player O make move at 1,2"
            MoveResultDTO result = markOAt(1,2)
        then: "player X won"
            result == MoveResultDTO.WIN
    }


    private MoveResultDTO markOAt(int column, int row) {
        return facade.make(MoveDTO.builder().gameId(game.id).madeBy(playerO).markedField(new FieldDTO(column, row)).build())
    }

    private MoveResultDTO markXAt(int column, int row) {
        return facade.make(MoveDTO.builder().gameId(game.id).madeBy(playerX).markedField(new FieldDTO(column, row)).build())
    }

    private GameDTO createSample3x3Game(List<PlayerDTO> players = testData.samplePlayers()) {
        facade.createNewGame(players)
    }
}
