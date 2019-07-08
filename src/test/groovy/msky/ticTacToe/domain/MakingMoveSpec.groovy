package msky.ticTacToe.domain

import msky.ticTacToe.dto.FieldDTO
import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.MarkDTO
import msky.ticTacToe.dto.MoveDTO
import msky.ticTacToe.dto.PlayerDTO
import spock.lang.Specification

class MakingMoveSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    def "first mark can be placed anywhere on the board"() {
        given: "move on any field of new game with empty board"
            GameDTO game = createSample3x3Game()
            PlayerDTO nextPlayer = game.nextPlayer
            FieldDTO field = new FieldDTO(column, row)
            MoveDTO move = MoveDTO.builder()
                            .gameId(game.id)
                            .madeBy(nextPlayer)
                            .markedField(field)
                            .build()
        expect: "mark can be made on any field of the board"
            facade.make(move)
            Collection<MarkDTO> markedFields = facade.load(game.id).board.marks
            markedFields.contains(MarkDTO.builder()
                                    .on(field)
                                    .with(nextPlayer.playingWith)
                                    .build())

        where: "every field of 3x3 board can be marked"
            row | column
            0   | 0
            0   | 1
            0   | 2
            1   | 0
            1   | 1
            1   | 2
            2   | 0
            2   | 1
            2   | 2
    }

    private GameDTO createSample3x3Game() {
        List<PlayerDTO> players = testData.samplePlayers()
        facade.createNewGame(players)
    }
}
