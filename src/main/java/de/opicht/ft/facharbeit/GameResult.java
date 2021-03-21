package de.opicht.ft.facharbeit;

public class GameResult {

    public final int gameCount;
    public final int draws;

    public final String startingPlayerId;
    public final int startingPlayerWins;

    public final String secondPlayerId;
    public final int secondPlayerWins;

    public GameResult(int gameCount, int draws, String startingPlayerId, int startingPlayerWins, String secondPlayerId,
            int secondPlayerWins) {
        this.gameCount = gameCount;
        this.draws = draws;
        this.startingPlayerId = startingPlayerId;
        this.startingPlayerWins = startingPlayerWins;
        this.secondPlayerId = secondPlayerId;
        this.secondPlayerWins = secondPlayerWins;
    }

    public GameResult combine(GameResult other) {
        if (!startingPlayerId.equals(other.startingPlayerId) || !secondPlayerId.equals(other.secondPlayerId)) {
            throw new IllegalArgumentException("The two objects can't be combined.");
        }

        return new GameResult(gameCount + other.gameCount, draws + other.draws, startingPlayerId,
                startingPlayerWins + other.startingPlayerWins, secondPlayerId,
                secondPlayerWins + other.secondPlayerWins);
    }

    public void printStats() {
        System.out.printf(
                "Played games: %d%nAmount of draws: %d%nID starting agent: %s%nStarting agent wins: %d%nID second agent: %s%nSecond agent wins: %d%n",
                gameCount, draws, startingPlayerId, startingPlayerWins, secondPlayerId, secondPlayerWins);
    }
}
