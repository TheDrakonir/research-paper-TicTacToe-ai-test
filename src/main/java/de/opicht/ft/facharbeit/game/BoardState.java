package de.opicht.ft.facharbeit.game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardState {

    public static final int BOARD_SIZE = 3;

    private final PositionState[][] board;

    public BoardState() {
        this.board = new PositionState[BOARD_SIZE][BOARD_SIZE];

        //initialize all positions with empty state
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

    public void printBoard() {
        String output = Arrays.stream(board).map(row -> Arrays.stream(row)
                .map(positionState -> positionState.displaySymbol).collect(Collectors.joining(" | ")))
                .collect(Collectors.joining("\n----------\n"));

        System.out.println(output);
        System.out.println();
    }

}
