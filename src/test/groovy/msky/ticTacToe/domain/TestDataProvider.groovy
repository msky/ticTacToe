package msky.ticTacToe.domain

import msky.ticTacToe.dto.PlayerDTO
import msky.ticTacToe.dto.SymbolDTO

class TestDataProvider {

    List<PlayerDTO> samplePlayers() {
        [player("player1", SymbolDTO.X), player("player2", SymbolDTO.O)]
    }

    private PlayerDTO player(String id, SymbolDTO symbol) {
        new PlayerDTO(id, symbol);
    }
}
