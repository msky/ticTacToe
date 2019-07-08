package msky.ticTacToe.domain;

import msky.ticTacToe.dto.SymbolDTO;

enum Symbol {
    X, O;

    SymbolDTO dto() {
        return SymbolDTO.valueOf(name());
    }

    static Symbol fromDTO(SymbolDTO dto) {
        return Symbol.valueOf(dto.name());
    }
}
