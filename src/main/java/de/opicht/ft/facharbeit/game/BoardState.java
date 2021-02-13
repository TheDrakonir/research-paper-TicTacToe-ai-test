package de.opicht.ft.facharbeit.game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardState {

    public static final int BOARD_SIZE = 3;

    private final PositionState[][] board;

    public BoardState() {
        this.board = new PositionState[BOARD_SIZE][BOARD_SIZE];

        // initialize all positions with empty state
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = PositionState.EMPTY;
            }
        }
    }

    public PositionState[][] getBoardCopy() {
        return board.clone();
    }

    /**
     * Apply a given move to the board.
     * 
     * @return true if the move is valid and was applied to the board, otherwise
     *         false is returned
     */
    public boolean applyMove(Players player, Move move) {
        if (board[move.targetRow][move.targetColumn] != PositionState.EMPTY) {
            return false;
        }

        board[move.targetRow][move.targetColumn] = player.positionState;
        return true;
    }

    public boolean isWon() {
        return hasWonRow() || hasWonColumn() || hasWonDiagonal();
    }

    public boolean isFull() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == PositionState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        String output = Arrays.stream(board).map(row -> Arrays.stream(row)
                .map(positionState -> positionState.displaySymbol).collect(Collectors.joining(" | ")))
                .collect(Collectors.joining("\n----------\n"));

        System.out.println(output);
        System.out.println();
    }

    private boolean hasWonRow() {
        for (int y = 0; y < board.length; y++) {
            if (board[y][0] == PositionState.EMPTY) {
                continue;
            }
            for (int x = 1; x < board[y].length; x++) {
                if (board[y][x] != board[y][0]) {
                    break;
                }

                if (x == board[y].length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasWonColumn() {
        for (int x = 0; x < board[0].length; x++) {
            if (board[0][x] == PositionState.EMPTY) {
                continue;
            }
            for (int y = 1; y < board.length; y++) {
                if (board[y][x] != board[0][x]) {
                    break;
                }

                if (y == board.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasWonDiagonal() {
        return hasWonLeftToRightDiagonal() || hasWonRightToLeftDiagonal();
    }

    private boolean hasWonLeftToRightDiagonal() {
        if (board[0][0] != PositionState.EMPTY) {
            for (int i = 1; i < board.length; i++) {
                if (board[i][i] != board[0][0]) {
                    break;
                }

                if (i == board.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasWonRightToLeftDiagonal() {
        if (board[0][BOARD_SIZE - 1] != PositionState.EMPTY) {
            for (int i = 1; i < board.length; i++) {
                if (board[i][BOARD_SIZE - 1 - i] != board[0][BOARD_SIZE - 1]) {
                    break;
                }

                if (i == board.length - 1) {
                    return true;
                }
            }
        }

        return false;
    }
}
