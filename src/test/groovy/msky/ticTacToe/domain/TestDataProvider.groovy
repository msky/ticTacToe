package msky.ticTacToe.domain

import msky.ticTacToe.dto.PlayerDTO

class TestDataProvider {

    List<PlayerDTO> samplePlayers() {
        List<PlayerDTO> players = of(player("player1"), player("player2"))
        players
    }

    private player(String id) {
        new PlayerDTO(id)
    }

    private of(PlayerDTO... player) {
        Arrays.asList(player)
    }
}
