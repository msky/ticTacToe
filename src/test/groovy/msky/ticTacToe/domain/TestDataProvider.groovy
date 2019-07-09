package msky.ticTacToe.domain

import msky.ticTacToe.dto.PlayerDTO
import msky.ticTacToe.dto.SymbolDTO

class TestDataProvider {

    PlayerDTO playerX = new PlayerDTO("player1", SymbolDTO.X)

    PlayerDTO playerO = new PlayerDTO("player2", SymbolDTO.O)

    List<PlayerDTO> samplePlayers() {
        [playerX, playerO]
    }
}
