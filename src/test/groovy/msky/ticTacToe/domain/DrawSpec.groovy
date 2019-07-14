package msky.ticTacToe.domain

import msky.ticTacToe.dto.FieldDTO
import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.MoveDTO
import msky.ticTacToe.dto.MoveResultDTO
import msky.ticTacToe.dto.PlayerDTO
import spock.lang.Specification

class DrawSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    PlayerDTO playerX = testData.playerX

    PlayerDTO playerO = testData.playerO

    GameDTO game

    def "marking last free field on board without fulfilling any of win conditions ends game with a draw" () {
        given: "game with board like" +
                "2  X | O |   " +
                "1  X | X | O " +
                "0  O | X | O " +
                "   0 | 1 | 2 "

            game = createSample3x3Game([playerX, playerO])
            markXAt(0,2)
            markOAt(0,0)
            markXAt(0,1)
            markOAt(1,2)
            markXAt(1,1)
            markOAt(2,0)
            markXAt(1,0)
            markOAt(2,1)

        when: "player X makes move at 2,2"
            MoveResultDTO result = markXAt(2,2)
        then: "game ands with a draw"
            result == MoveResultDTO.DRAW
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
