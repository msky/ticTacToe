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

    void switchTurn() {
        queue.add(queue.poll());
    }

    public boolean isNext(Player player) {
        return checkNext().equals(player);
    }
}

@AllArgsConstructor
@EqualsAndHashCode
class Player {

    private String id;

    private Symbol playingWith;

    PlayerDTO dto() {
        return new PlayerDTO(id, playingWith.dto());
    }

    Symbol playingWith() {
        return playingWith;
    }

    static Player fromDto(PlayerDTO dto) {
        return new Player(dto.getId(), Symbol.fromDTO(dto.getPlayingWith()));
    }
}
