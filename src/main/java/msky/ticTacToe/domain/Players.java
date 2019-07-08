package msky.ticTacToe.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import msky.ticTacToe.dto.PlayerDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Players {

    private Queue<Player> queue;

    Players(List<Player> playersList) {
        queue = new LinkedList<>(playersList);
    }

    Player checkNext() {
        return queue.peek();
    }
}

@AllArgsConstructor
@EqualsAndHashCode
class Player {

    private String id;

    PlayerDTO dto() {
        return new PlayerDTO(id);
    }
}
