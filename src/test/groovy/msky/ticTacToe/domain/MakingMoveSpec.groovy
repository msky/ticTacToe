package msky.ticTacToe.domain

import msky.ticTacToe.dto.*
import spock.lang.Specification

class MakingMoveSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    def "first mark can be placed anywhere on the board"() {
        given: "move on any field of new game with empty board"
            GameDTO game = createSample3x3Game()
            PlayerDTO nextPlayer = game.turns[0]
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

    def "players switch turns after move"() {
        given: "a new game"
            PlayerDTO firstPlayer = testData.playerX
            PlayerDTO secondPlayer = testData.playerO
            GameDTO game = createSample3x3Game([firstPlayer, secondPlayer])
        when: "move is made by first player "
            facade.make(MoveDTO.builder()
                        .gameId(game.id)
                        .madeBy(firstPlayer)
                        .markedField(new FieldDTO(0,0))
                        .build())
        then: "second player moves next"
            List<PlayerDTO> turns = facade.load(game.id).turns
            PlayerDTO nextPlayer = turns[0]
            secondPlayer == nextPlayer
    }

    def "player cannot make 2 moves in a row"() {
        given: "a new game"
            PlayerDTO firstPlayer = testData.playerX
            PlayerDTO secondPlayer = testData.playerO
            GameDTO game = createSample3x3Game([firstPlayer, secondPlayer])
        when: "move is made by first player twice "
            MoveDTO firstMove = MoveDTO.builder()
                .gameId(game.id)
                .madeBy(firstPlayer)
                .markedField(new FieldDTO(0, 0))
                .build()
            facade.make(firstMove)

            MoveDTO secondMove = MoveDTO.builder()
                .gameId(game.id)
                .madeBy(firstPlayer)
                .markedField(new FieldDTO(1, 0))
                .build()
            facade.make(secondMove)
        then: "an exception is thrown"
            thrown IllegalMoveException
    }

    def "move cannot be made on already marked field"() {
        given: "game with one marked field"
            PlayerDTO firstPlayer = testData.playerX
            PlayerDTO secondPlayer = testData.playerO
            GameDTO game = createSample3x3Game([firstPlayer, secondPlayer])

            FieldDTO field = new FieldDTO(0, 0)
            MoveDTO firstMove = MoveDTO.builder()
                .gameId(game.id)
                .madeBy(firstPlayer)
                .markedField(field)
                .build()
            facade.make(firstMove)
        when: "second player is trying to mark already marked field"
            MoveDTO secondMove = MoveDTO.builder()
                    .gameId(game.id)
                    .madeBy(secondPlayer)
                    .markedField(field)
                    .build()
            facade.make(secondMove)
        then: "an exception is thrown"
            thrown IllegalMoveException
    }

    def "move cannot be made in a game that is already won by someone"() {
        given: "game won by player X with some free field and board like " +
                "2  X | O |   " +
                "1  X |   | O " +
                "0  X |   |   " +
                "   0 | 1 | 2 "
            GameDTO game = createSample3x3Game()
            markXAt(game, 0, 0)
            markOAt(game, 1, 2)
            markXAt(game, 0, 1)
            markOAt(game, 2, 1)
            markXAt(game, 0, 2)
        when: "player O is trying to mark free field"
            markOAt(game, 1, 0)
        then: "an exception is thrown"
            Exception e = thrown(IllegalMoveException)
            e.message == "The game is over, you can no longer make any moves"
    }

    private GameStateDTO markXAt(GameDTO game, int column, int row) {
        return facade.make(MoveDTO.builder()
                .gameId(game.id)
                .markedField(new FieldDTO(column, row))
                .madeBy(testData.playerX).build())
    }

    private GameStateDTO markOAt(GameDTO game, int column, int row) {
        return facade.make(MoveDTO.builder()
                .gameId(game.id)
                .markedField(new FieldDTO(column, row))
                .madeBy(testData.playerO).build())
    }

    private GameDTO createSample3x3Game(List<PlayerDTO> players = testData.samplePlayers()) {
        facade.createNewGame(players)
    }
}
