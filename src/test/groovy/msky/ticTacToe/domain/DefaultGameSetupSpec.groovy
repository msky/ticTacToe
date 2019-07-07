package msky.ticTacToe.domain

import msky.ticTacToe.dto.GameDTO
import spock.lang.Specification

class DefaultGameSetupSpec extends Specification {

    GameFacade facade = new GameConfiguration().gameFacade()

    def "default game is played on 3 x 3 board"() {
        when: "we create a new game with default setup"
            GameDTO game = facade.createNewGame()

        then: "created board got 3 rows and 3 columns"
            game.board.columns == 3
            game.board.rows == 3
    }

}
