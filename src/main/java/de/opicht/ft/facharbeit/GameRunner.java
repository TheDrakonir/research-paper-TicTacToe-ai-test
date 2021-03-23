package de.opicht.ft.facharbeit;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import de.opicht.ft.facharbeit.agents.Agent;
import de.opicht.ft.facharbeit.game.TicTacToe;

public class GameRunner implements Callable<SimulationResult> {

    private final int repetitions;
    private final Agent[] agents;

    public GameRunner(int repetitions, Agent[] agents) {
        this.repetitions = repetitions;
        this.agents = agents;
    }

    @Override
    public SimulationResult call() {
        Map<Agent, Integer> wins = new HashMap<>();
        Map<String, Duration> moveTimes = new HashMap<>();
        int draws = 0;

        for (int i = 0; i < repetitions; i++) {
            GameResult result = TicTacToe.start(agents);

            result.winningAgent.ifPresent(winner -> wins.put(winner, wins.getOrDefault(winner, 0) + 1));

            if (result.winningAgent.isEmpty()) {
                draws++;
            }

            for (Map.Entry<String, Duration> entry : result.agentAvarageMoveTimes.entrySet()) {
                moveTimes.put(entry.getKey(), moveTimes.getOrDefault(entry.getKey(), Duration.ZERO).plus(entry.getValue()));
            }
        }

        return new SimulationResult(repetitions, draws, agents[0].getAgentIdentifier(), wins.getOrDefault(agents[0], 0), moveTimes.get(agents[0].getAgentIdentifier()),
                agents[1].getAgentIdentifier(), wins.getOrDefault(agents[1], 0), moveTimes.get(agents[1].getAgentIdentifier()));
    }
}
