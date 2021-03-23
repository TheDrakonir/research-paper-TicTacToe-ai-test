package de.opicht.ft.facharbeit;

import java.time.Duration;

public class SimulationResult {

    public final int gameCount;
    public final int draws;

    public final String startingPlayerId;
    public final int startingPlayerWins;
    public final Duration startingPlayerAvarageMoveTimeSum;

    public final String secondPlayerId;
    public final int secondPlayerWins;
    public final Duration secondPlayerAvarageMoveTimeSum;

    public SimulationResult(int gameCount, int draws, String startingPlayerId, int startingPlayerWins,
            Duration startingPlayerAvarageMoveTimeSum, String secondPlayerId, int secondPlayerWins,
            Duration secondPlayerAvarageMoveTimeSum) {
        this.gameCount = gameCount;
        this.draws = draws;
        this.startingPlayerId = startingPlayerId;
        this.startingPlayerWins = startingPlayerWins;
        this.startingPlayerAvarageMoveTimeSum = startingPlayerAvarageMoveTimeSum;
        this.secondPlayerId = secondPlayerId;
        this.secondPlayerWins = secondPlayerWins;
        this.secondPlayerAvarageMoveTimeSum = secondPlayerAvarageMoveTimeSum;
    }

    public SimulationResult combine(SimulationResult other) {
        if (!startingPlayerId.equals(other.startingPlayerId) || !secondPlayerId.equals(other.secondPlayerId)) {
            throw new IllegalArgumentException("The two objects can't be combined.");
        }

        return new SimulationResult(gameCount + other.gameCount, draws + other.draws, startingPlayerId,
                startingPlayerWins + other.startingPlayerWins,
                startingPlayerAvarageMoveTimeSum.plus(other.startingPlayerAvarageMoveTimeSum), secondPlayerId,
                secondPlayerWins + other.secondPlayerWins,
                secondPlayerAvarageMoveTimeSum.plus(other.secondPlayerAvarageMoveTimeSum));
    }

    public void printStats() {
        System.out.printf(
                "Played games: %d%nAmount of draws: %d%nID starting agent: %s%nStarting agent wins: %d%nAvarage move time: %s%nID second agent: %s%nSecond agent wins: %d%nAvarage move time: %s%n",
                gameCount, draws, startingPlayerId, startingPlayerWins,
                startingPlayerAvarageMoveTimeSum.dividedBy(gameCount).toString().substring(2), secondPlayerId, secondPlayerWins,
                secondPlayerAvarageMoveTimeSum.dividedBy(gameCount).toString().substring(2));
    }
}
