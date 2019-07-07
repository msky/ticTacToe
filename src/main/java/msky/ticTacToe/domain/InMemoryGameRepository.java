package msky.ticTacToe.domain;

import java.util.HashMap;
import java.util.Map;

class InMemoryGameRepository implements GameRepository {

    private Map<String, Game> games = new HashMap<>();

    public Game findById(String gameId) {
        return games.get(gameId);
    }

    public Game save(Game game) {
        final String gameId = game.dto().getId();
        games.put(gameId, game);
        return game;
    }
}
