package msky.ticTacToe.domain

import msky.ticTacToe.dto.GameDTO
import spock.lang.Shared
import spock.lang.Specification

class DefaultGameSetupSpec extends Specification {

    @Shared
    TestDataProvider testData = new TestDataProvider()

    GameFacade facade = new GameConfiguration().gameFacade()

    def "default game is played on 3 x 3 board"() {
        when: "we create a new game with default setup"
            GameDTO game = createSampleGame()

        then: "created board got 3 rows and 3 columns"
            game.board.columns == 3
            game.board.rows == 3
    }

    def "default game cannot be created for players number different than 2"() {
        when: "creating game with number of players != 2"
            facade.startNewGame(players)
        then: "an exception is thrown"
            thrown IllegalSetupException
        where:
            players << [
            [testData.playerO],
            [],
            [testData.playerO, testData.playerX, testData.playerTriangle]]
    }

    private GameDTO createSampleGame() {
        facade.startNewGame(testData.samplePlayers())
    }

}
