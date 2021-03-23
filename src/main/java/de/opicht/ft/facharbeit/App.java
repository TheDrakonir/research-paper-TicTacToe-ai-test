package de.opicht.ft.facharbeit;

import java.time.Duration;
import java.time.Instant;

import de.opicht.ft.facharbeit.agents.Agent;
import de.opicht.ft.facharbeit.agents.MinimaxAgent;
import de.opicht.ft.facharbeit.agents.MinimaxWithPruningAgent;
import de.opicht.ft.facharbeit.agents.MonteCarloTreeSearchAgent;
import de.opicht.ft.facharbeit.agents.RandomAgent;

public class App {

    public static void main(String[] args) {
        Agent[] agents = new Agent[] { new MinimaxAgent(), new MonteCarloTreeSearchAgent(50) };
        benchmarkGames(200, agents);

        // Agent[] agents2 = new Agent[] { new MinimaxWithPruningAgent(), new MonteCarloTreeSearchAgent() };
        // benchmarkGames(100, agents2);
    }

    public static void benchmarkGames(int gameCount, Agent... agents) {
        Instant start = Instant.now();
        new GameDispatcher(gameCount, agents);
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println("Total runtime: " + duration.toString().substring(2));
    }

}
