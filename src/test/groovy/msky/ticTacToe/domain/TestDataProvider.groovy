package msky.ticTacToe.domain

import msky.ticTacToe.dto.PlayerDTO

class TestDataProvider {

    List<PlayerDTO> samplePlayers() {
        [player("player1"), player("player2")]
    }

    private PlayerDTO player(String id) {
        new PlayerDTO(id)
    }
}
