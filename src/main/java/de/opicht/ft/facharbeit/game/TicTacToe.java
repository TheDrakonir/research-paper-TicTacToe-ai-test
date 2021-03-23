package de.opicht.ft.facharbeit.game;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.opicht.ft.facharbeit.GameResult;
import de.opicht.ft.facharbeit.agents.Agent;

public class TicTacToe {

    private TicTacToe() {
    }

    /**
     * Starts and runs the game simulation for the agents given on initialization
     * 
     * @param agents Varargs of agents to play the game; must be exactly two! First
     *               agent is X (beginning player), second agent is O.
     * @return the winning agent or an empty optional if it's a draw
     */
    public static GameResult start(Agent... agents) {
        if (agents.length != 2) {
            throw new IllegalArgumentException("Can only simulate a game with exactly two agents");
        }

        BoardState boardState = new BoardState();
        Map<Agent, Integer> moveCount = new HashMap<>(2);
        Map<Agent, Duration> moveTimes = new HashMap<>(2);
        int nextAgentIndex = 0;

        while (!boardState.isFinished()) {
            Duration moveTime = playNextMove(agents[nextAgentIndex], boardState, nextAgentIndex);

            moveCount.put(agents[nextAgentIndex], moveCount.getOrDefault(agents[nextAgentIndex], 0) + 1);
            moveTimes.put(agents[nextAgentIndex],
                    moveTimes.getOrDefault(agents[nextAgentIndex], Duration.ZERO).plus(moveTime));

            nextAgentIndex = (nextAgentIndex == 0 ? 1 : 0);
        }

        Map<String, Duration> avarageMoveTimes = new HashMap<>();
        for (Map.Entry<Agent, Duration> entry : moveTimes.entrySet()) {
            avarageMoveTimes.put(entry.getKey().getAgentIdentifier(),
                    entry.getValue().dividedBy(moveCount.get(entry.getKey())));
        }

        if (boardState.isWon()) {
            int finishingAgentIndex = (nextAgentIndex == 0 ? 1 : 0);
            return new GameResult(Optional.of(agents[finishingAgentIndex]), avarageMoveTimes);
        }

        return new GameResult(Optional.empty(), avarageMoveTimes);
    }

    private static Duration playNextMove(Agent agent, BoardState boardState, int playerIndex) {
        Instant start = Instant.now();
        while (!boardState.applyMove(Players.values()[playerIndex],
                agent.determineNextMove(boardState.getBoardCopy(), Players.values()[playerIndex]))) {
            System.out.println("Invalid move. Retrying...");
        }
        Instant end = Instant.now();
        return Duration.between(start, end);
    }
}
