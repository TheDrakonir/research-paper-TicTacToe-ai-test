package de.opicht.ft.facharbeit.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardState {

    public static final int BOARD_SIZE = 3;

    public final PositionState[][] board;

    public BoardState() {
        this.board = new PositionState[BOARD_SIZE][BOARD_SIZE];

        // initialize all positions with empty state
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                board[y][x] = PositionState.EMPTY;
            }
        }
    }

    public BoardState(PositionState[][] board) {
        if (board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Supplied board has invalid dimensions!");
        }

        this.board = board;
    }

    public BoardState getBoardCopy() {
        return new BoardState(Arrays.stream(board).map(PositionState[]::clone).toArray(PositionState[][]::new));
        
    }

    public Set<Move> getPossibleMoves() {
        Set<Move> result = new HashSet<>();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == PositionState.EMPTY) {
                    result.add(new Move(y, x));
                }
            }
        }

        return result;
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

    public int getScore(Players player) {
        WinState winState = getWinState();
        if (!winState.isWon) {
            return 0;
        }

        return winState.winner.orElseThrow(IllegalStateException::new) == player ? 1 : -1;
    }

    public Optional<Players> getWinner() {
        return getWinState().winner;
    }

    public boolean isWon() {
        return getWinState().isWon;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoardState other = (BoardState) obj;
        return Arrays.deepEquals(board, other.board);
    }

    private WinState getWinState() {
        return getWonRowState().combine(getWonColumnState()).combine(getWonDiagonalState());
    }

    private WinState getWonRowState() {
        for (int y = 0; y < board.length; y++) {
            if (board[y][0] == PositionState.EMPTY) {
                continue;
            }
            for (int x = 1; x < board[y].length; x++) {
                if (board[y][x] != board[y][0]) {
                    break;
                }

                if (x == board[y].length - 1) {
                    return new WinState(true, Players.getByPositionState(board[y][0]));
                }
            }
        }

        return new WinState(false, Optional.empty());
    }

    private WinState getWonColumnState() {
        for (int x = 0; x < board[0].length; x++) {
            if (board[0][x] == PositionState.EMPTY) {
                continue;
            }
            for (int y = 1; y < board.length; y++) {
                if (board[y][x] != board[0][x]) {
                    break;
                }

                if (y == board.length - 1) {
                    return new WinState(true, Players.getByPositionState(board[0][x]));
                }
            }
        }

        return new WinState(false, Optional.empty());
    }

    private WinState getWonDiagonalState() {
        return getWonLeftToRightDiagonalState().combine(getWonRightToLeftDiagonalState());
    }

    private WinState getWonLeftToRightDiagonalState() {
        if (board[0][0] != PositionState.EMPTY) {
            for (int i = 1; i < board.length; i++) {
                if (board[i][i] != board[0][0]) {
                    break;
                }

                if (i == board.length - 1) {
                    return new WinState(true, Players.getByPositionState(board[0][0]));
                }
            }
        }

        return new WinState(false, Optional.empty());
    }

    private WinState getWonRightToLeftDiagonalState() {
        if (board[0][BOARD_SIZE - 1] != PositionState.EMPTY) {
            for (int i = 1; i < board.length; i++) {
                if (board[i][BOARD_SIZE - 1 - i] != board[0][BOARD_SIZE - 1]) {
                    break;
                }

                if (i == board.length - 1) {
                    return new WinState(true, Players.getByPositionState(board[0][BOARD_SIZE - 1]));
                }
            }
        }

        return new WinState(false, Optional.empty());
    }
}
