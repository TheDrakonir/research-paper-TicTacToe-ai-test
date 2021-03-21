package de.opicht.ft.facharbeit;

import de.opicht.ft.facharbeit.agents.Agent;
import de.opicht.ft.facharbeit.agents.MonteCarloTreeSearchAgent;
import de.opicht.ft.facharbeit.agents.RandomAgent;

public class App {

    public static void main(String[] args) {
        Agent[] agents = new Agent[] { new RandomAgent(), new MonteCarloTreeSearchAgent() };

        new GameDispatcher(100, agents);
    }

}
