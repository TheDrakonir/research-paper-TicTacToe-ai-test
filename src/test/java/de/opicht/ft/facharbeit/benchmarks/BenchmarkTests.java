package de.opicht.ft.facharbeit.benchmarks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.opicht.ft.facharbeit.App;
import de.opicht.ft.facharbeit.agents.MinimaxAgent;
import de.opicht.ft.facharbeit.agents.MinimaxWithPruningAgent;
import de.opicht.ft.facharbeit.agents.MonteCarloTreeSearchAgent;
import de.opicht.ft.facharbeit.agents.RandomAgent;

public class BenchmarkTests {

    @Test
    public void compareMinimaxToRandom_RandomStarts() {
        System.out.println("TEST: compareMinimaxToRandom_RandomStarts");

        // Random vs. Minimax
        App.benchmarkGames(10000, new RandomAgent(), new MinimaxAgent());

        // Random vs. AlphaBetaMinimax
        App.benchmarkGames(10000, new RandomAgent(), new MinimaxWithPruningAgent());

        assertTrue(true);
    }

    @Test
    public void compareMinimaxToRandom_MinimaxStarts() {
        System.out.println("TEST: compareMinimaxToRandom_MinimaxStarts");

        // Random vs. Minimax
        App.benchmarkGames(10000, new MinimaxAgent(), new RandomAgent());

        // Random vs. AlphaBetaMinimax
        App.benchmarkGames(10000, new MinimaxWithPruningAgent(), new RandomAgent());

        assertTrue(true);
    }

    @Test
    public void compareMctsToRandom_RandomStarts() {
        System.out.println("TEST: compareMctsToRandom_RandomStarts");

        // Mcts with 1s
        App.benchmarkGames(1000, new RandomAgent(), new MonteCarloTreeSearchAgent());

        // Mcts with .5s
        App.benchmarkGames(1000, new RandomAgent(), new MonteCarloTreeSearchAgent(500));

        // Mcts with .2s
        App.benchmarkGames(1000, new RandomAgent(), new MonteCarloTreeSearchAgent(200));

        // Mcts with .1s
        App.benchmarkGames(1000, new RandomAgent(), new MonteCarloTreeSearchAgent(100));

        // Mcts with .05s
        App.benchmarkGames(1000, new RandomAgent(), new MonteCarloTreeSearchAgent(50));

        assertTrue(true);
    }

    @Test
    public void compareMctsToRandom_MctsStarts() {
        System.out.println("TEST: compareMctsToRandom_MctsStarts");

        // Mcts with 1s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(), new RandomAgent());

        // Mcts with .5s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(500), new RandomAgent());

        // Mcts with .2s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(200), new RandomAgent());

        // Mcts with .1s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(100), new RandomAgent());

        // Mcts with .05s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(50), new RandomAgent());

        assertTrue(true);
    }

    @Test
    public void compareMinimaxToMcts_MctsStarts() {
        System.out.println("TEST: compareMinimaxToMcts_MctsStarts");

        // Mcts vs. AlphaBetaMinimax
        // Mcts with .2s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(200), new MinimaxWithPruningAgent());

        // Mcts with .1s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(100), new MinimaxWithPruningAgent());

        // Mcts with .05s
        App.benchmarkGames(1000, new MonteCarloTreeSearchAgent(50), new MinimaxWithPruningAgent());

        assertTrue(true);
    }

    @Test
    public void compareMinimaxToMcts_MinimaxStarts() {
        System.out.println("TEST: compareMinimaxToMcts_MinimaxStarts");

        // Mcts vs. AlphaBetaMinimax
        // Mcts with .2s
        App.benchmarkGames(1000, new MinimaxWithPruningAgent(), new MonteCarloTreeSearchAgent(200));

        // Mcts with .1s
        App.benchmarkGames(1000, new MinimaxWithPruningAgent(), new MonteCarloTreeSearchAgent(100));

        // Mcts with .05s
        App.benchmarkGames(1000, new MinimaxWithPruningAgent(), new MonteCarloTreeSearchAgent(50));

        assertTrue(true);
    }
}
