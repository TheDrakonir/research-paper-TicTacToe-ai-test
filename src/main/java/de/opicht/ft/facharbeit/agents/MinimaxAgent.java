package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;

public class MinimaxAgent implements Agent {

    @Override
    public String getAgentIdentifier() {
        return "Minimax";
    }

    @Override
    public Move determineNextMove(BoardState boardState, Players self) {
        Move bestMove = null;
        int bestMoveScore = Integer.MIN_VALUE;
        
        for (Move move : boardState.getPossibleMoves()) {
            BoardState newState = boardState.getBoardCopy();
            newState.applyMove(self, move);
            int score = minimax(newState, false, 1, self, Players.getOtherPlayer(self));

            if (score > bestMoveScore) {
                bestMove = move;
                bestMoveScore = score;
            }
        }

        return bestMove;
    }

    private int minimax(BoardState state, boolean isMaximizingPlayer, int depth, Players self, Players other) {
        if (state.isFull() || state.isWon()) {
            return state.getScore(self) + (depth * (isMaximizingPlayer ? 1 : -1));
        }

        if (isMaximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(self, move);
                value = Math.max(value, minimax(newState, false, depth + 1, self, other));
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(other, move);
                value = Math.min(value, minimax(newState, true, depth + 1, self, other));
            }
            return value;
        }
    }

    @Override
    public Agent getCopy() {
        return new MinimaxAgent();
    }
}
