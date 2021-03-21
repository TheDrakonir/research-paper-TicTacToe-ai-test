package de.opicht.ft.facharbeit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import de.opicht.ft.facharbeit.agents.Agent;
import de.opicht.ft.facharbeit.game.TicTacToe;

public class GameRunner implements Callable<GameResult> {

    private final int repetitions;
    private final Agent[] agents;

    public GameRunner(int repetitions, Agent[] agents) {
        this.repetitions = repetitions;
        this.agents = agents;
    }

    @Override
    public GameResult call() {
        Map<Agent, Integer> wins = new HashMap<>();
        int draws = 0;

        for (int i = 0; i < repetitions; i++) {
            Optional<Agent> result = TicTacToe.start(agents);

            result.ifPresent(winner -> wins.put(winner, wins.getOrDefault(winner, 0) + 1));

            if (result.isEmpty()) {
                draws++;
            }
        }

        return new GameResult(repetitions, draws, agents[0].getAgentIdentifier(), wins.getOrDefault(agents[0], 0),
                agents[1].getAgentIdentifier(), wins.getOrDefault(agents[1], 0));
    }
}
