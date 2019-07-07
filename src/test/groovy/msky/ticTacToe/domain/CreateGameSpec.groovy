package msky.ticTacToe.domain

import msky.ticTacToe.dto.GameDTO
import spock.lang.Specification

class CreateGameSpec extends Specification {

    GameFacade facade = new GameConfiguration().gameFacade()

    def "board is empty in newly created game"() {
        when: "we create a new game"
            GameDTO game = facade.createNewGame()
        then: "no field is marked"
            game.getBoard().isEmpty()
    }

    def "created game is available to read"() {
        when: "we create a new game"
            GameDTO game = facade.createNewGame()
        then: "game is available to read"
            game == facade.load(game.getId())
    }
}
