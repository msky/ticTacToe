package msky.ticTacToe.domain

import msky.ticTacToe.dto.GameDTO
import msky.ticTacToe.dto.PlayerDTO
import msky.ticTacToe.dto.SymbolDTO
import spock.lang.Specification

class CreateGameSpec extends Specification {

    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    def "board is empty in newly created game"() {
        when: "we create a new game"
            GameDTO game = createSampleGame()
        then: "no field is marked"
            game.getBoard().isEmpty()
    }

    def "created game is available to read"() {
        when: "we create a new game"
            GameDTO game = createSampleGame()
        then: "game is available to read"
            game == facade.load(game.getId())
    }

    def "game holds given order of players"() {
        given: "with defined list of players"
            List<PlayerDTO> players = players()
            PlayerDTO firstPlayer = players.get(0)
        when: "we create a new game"
            GameDTO game = facade.createNewGame(players)
        then: "first player on a given list moves first"
            game.getNextPlayer() == firstPlayer
    }

    def "multiple players cannot play with same symbol"() {
        given: "with two players playing with same symbol"
            PlayerDTO firstPlayerWithX = new PlayerDTO("player1", SymbolDTO.X)
            PlayerDTO secondPlayerWithX = new PlayerDTO("player2", SymbolDTO.X)
        when: "we create a new game"
            facade.createNewGame([firstPlayerWithX, secondPlayerWithX])
        then: "an exception is thrown"
            thrown IllegalSetupException
    }

    private List<PlayerDTO> players() {
        testData.samplePlayers()
    }

    private GameDTO createSampleGame() {
        List<PlayerDTO> players = testData.samplePlayers()
        facade.createNewGame(players)
    }
}
