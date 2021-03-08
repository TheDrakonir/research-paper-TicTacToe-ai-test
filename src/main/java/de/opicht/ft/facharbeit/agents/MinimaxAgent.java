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
        double bestMoveScore = Integer.MIN_VALUE;
        
        for (Move move : boardState.getPossibleMoves()) {
            BoardState newState = boardState.getBoardCopy();
            newState.applyMove(self, move);
            double score = minimax(newState, false, 1, self, Players.getOtherPlayer(self));

            if (score > bestMoveScore) {
                bestMove = move;
                bestMoveScore = score;
            }
        }

        return bestMove;
    }

    private double minimax(BoardState state, boolean isMaximizingPlayer, int depth, Players self, Players other) {
        if (state.isFull() || state.isWon()) {
            return state.getScore(self) * 1.0 / depth;
        }

        if (isMaximizingPlayer) {
            double value = Double.MIN_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(self, move);
                value = Math.max(value, minimax(newState, false, depth + 1, self, other));
            }
            return value;
        } else {
            double value = Double.MAX_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(other, move);
                value = Math.min(value, minimax(newState, true, depth + 1, self, other));
            }
            return value;
        }
    }

}
