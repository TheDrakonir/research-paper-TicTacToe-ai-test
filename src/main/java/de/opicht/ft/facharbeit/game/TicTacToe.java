package de.opicht.ft.facharbeit.game;

import java.util.Optional;

import de.opicht.ft.facharbeit.agents.Agent;

public class TicTacToe {

    private TicTacToe() {
    }

    /**
     * Starts and runs the game simulation for the agents given on initialization
     * 
     * @param agents Varargs of agents to play the game; must be exactly two!
     * @return the winning agent or an empty optional if it's a draw
     */
    public static Optional<Agent> start(Agent... agents) {
        if (agents.length != 2) {
            throw new IllegalArgumentException("Can only simulate a game with exactly two agents");
        }

        BoardState boardState = new BoardState();
        int nextAgentIndex = 0;

        while (!isFinished(boardState)) {
            playNextMove(agents[nextAgentIndex], boardState, nextAgentIndex);
            nextAgentIndex = (nextAgentIndex == 0 ? 1 : 0);
        }

        if (boardState.isWon()) {
            int finishingAgentIndex = (nextAgentIndex == 0 ? 1 : 0);
            return Optional.of(agents[finishingAgentIndex]);
        }

        // TODO: Remove
        boardState.printBoard();

        return Optional.empty();
    }

    private static void playNextMove(Agent agent, BoardState boardState, int playerIndex) {
        while (!boardState.applyMove(Players.values()[playerIndex],
                agent.determineNextMove(boardState.getBoardCopy()))) {
            System.out.println("Invalid move. Retrying...");
        }
    }

    private static boolean isFinished(BoardState boardState) {
        boardState.printBoard();
        return boardState.isWon() || boardState.isFull();
    }

}
