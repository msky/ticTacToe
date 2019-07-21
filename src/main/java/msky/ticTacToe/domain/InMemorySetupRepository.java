package msky.ticTacToe.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemorySetupRepository implements SetupRepository {

    private Map<String, GameSetup> setups = new HashMap<>();

    public GameSetup findById(String gameId) {
        return setups.get(gameId);
    }

    public void save(GameSetup setup) {
        final String gameId = setup.getGameId();
        setups.put(gameId, setup);
    }
}

class InMemoryMoveRepository implements MoveRepository {

    private Map<String, List<Move>> moves = new HashMap<>();

    @Override
    public void save(Move move, String gameId) {
        moves.computeIfAbsent(gameId, k -> new ArrayList<>()).add(move);
    }

    @Override
    public List<Move> getAllFor(String gameId) {
        return moves.getOrDefault(gameId, new ArrayList<>());
    }
}
