package de.opicht.ft.facharbeit;

import java.time.Duration;
import java.time.Instant;

import de.opicht.ft.facharbeit.agents.Agent;

public class App {

    private App() {
        // static application class
    }

    public static void benchmarkGames(int gameCount, Agent... agents) {
        Instant start = Instant.now();
        new GameDispatcher(gameCount, agents);
        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println("Total runtime: " + duration.toString().substring(2));
    }

}
