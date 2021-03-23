package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;

public class MinimaxWithPruningAgent implements Agent {

    @Override
    public String getAgentIdentifier() {
        return "Minimax with alpha-beta pruning";
    }

    @Override
    public Move determineNextMove(BoardState boardState, Players self) {
        Move bestMove = null;
        int bestMoveScore = Integer.MIN_VALUE;
        
        for (Move move : boardState.getPossibleMoves()) {
            BoardState newState = boardState.getBoardCopy();
            newState.applyMove(self, move);
            int score = minimaxAlphabeta(newState, false, 1, self, Players.getOtherPlayer(self), Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (score > bestMoveScore) {
                bestMove = move;
                bestMoveScore = score;
            }
        }

        return bestMove;
    }

    private int minimaxAlphabeta(BoardState state, boolean isMaximizingPlayer, int depth, Players self, Players other, int alpha, int beta) {
        if (state.isFull() || state.isWon()) {
            return state.getScore(self) + (depth * (isMaximizingPlayer ? 1 : -1));
        }

        if (isMaximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(self, move);
                value = Math.max(value, minimaxAlphabeta(newState, false, depth + 1, self, other, alpha, beta));
                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
            return value;
        } else {
            int value = Integer.MAX_VALUE;
            for (Move move : state.getPossibleMoves()) {
                BoardState newState = state.getBoardCopy();
                newState.applyMove(other, move);
                value = Math.min(value, minimaxAlphabeta(newState, true, depth + 1, self, other, alpha, beta));
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        }
    }
    
    @Override
    public Agent getCopy() {
        return new MinimaxWithPruningAgent();
    }
}
