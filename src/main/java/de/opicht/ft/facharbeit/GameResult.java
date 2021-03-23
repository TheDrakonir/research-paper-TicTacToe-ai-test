package de.opicht.ft.facharbeit;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

import de.opicht.ft.facharbeit.agents.Agent;

public class GameResult {
    
    public final Optional<Agent> winningAgent;
    public final Map<String, Duration> agentAvarageMoveTimes;

    public GameResult(Optional<Agent> winningAgent, Map<String, Duration> agentAvarageMoveTimes) {
        this.winningAgent = winningAgent;
        this.agentAvarageMoveTimes = agentAvarageMoveTimes;
    }
}
