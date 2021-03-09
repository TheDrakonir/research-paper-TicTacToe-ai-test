package de.opicht.ft.facharbeit.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;
import de.opicht.ft.facharbeit.game.PositionState;

public class BoardStateTest {

    private static final PositionState E = PositionState.EMPTY;
    private static final PositionState O = PositionState.OCCUPIED_BY_O;
    private static final PositionState X = PositionState.OCCUPIED_BY_X;

    private final PositionState[][] ongoingBoard = new PositionState[][] { { O, E, O }, { E, X, E }, { E, E, X } };
    private final PositionState[][] fullBoardDraw = new PositionState[][] { { O, X, X }, { X, X, O }, { O, O, X } };
    private final PositionState[][] fullBoardWinX = new PositionState[][] { { X, O, O }, { X, X, O }, { O, X, X } };

    @Test
    public void getBoardCopy_referenceTest() {
        BoardState boardState = new BoardState();
        assertNotSame(boardState, boardState.getBoardCopy());
    }

    @Test
    public void applyMove_validMove() {
        BoardState boardState = new BoardState();
        boardState.applyMove(Players.PLAYER_X, new Move(0, 0));
        assertEquals(new BoardState(new PositionState[][] { { X, E, E }, { E, E, E }, { E, E, E } }), boardState);
    }

    @Test
    public void applyMove_invalidMove() {
        assertFalse(new BoardState(fullBoardDraw).applyMove(Players.PLAYER_X, new Move(1, 1)));
    }

    @Test
    public void getScore_ongoingBoard() {
        assertEquals(0, new BoardState(ongoingBoard).getScore(Players.PLAYER_X));
    }

    @Test
    public void getScore_drawBoard() {
        assertEquals(0, new BoardState(fullBoardDraw).getScore(Players.PLAYER_X));
    }

    @Test
    public void getScore_wonPlayer() {
        assertEquals(10, new BoardState(fullBoardWinX).getScore(Players.PLAYER_X));
    }

    @Test
    public void getScore_lostPlayer() {
        assertEquals(-10, new BoardState(fullBoardWinX).getScore(Players.PLAYER_O));
    }

    @Test
    public void getWinner_wonX() {
        assertEquals(Optional.of(Players.PLAYER_X), new BoardState(fullBoardWinX).getWinner());
    }

    @Test
    public void getWinner_ongoingBoard() {
        assertEquals(Optional.empty(), new BoardState(ongoingBoard).getWinner());
    }

    @Test
    public void isWon_wonBoard() {
        assertTrue(new BoardState(fullBoardWinX).isWon());
    }

    @Test
    public void isWon_drawBoard() {
        assertFalse(new BoardState(fullBoardDraw).isWon());
    }

    @Test
    public void isWon_ongoingBoard() {
        assertFalse(new BoardState(ongoingBoard).isWon());
    }

    @Test
    public void isFull_fullBoard() {
        assertTrue(new BoardState(fullBoardWinX).isFull());
    }

    @Test
    public void isFull_ongoingBoard() {
        assertFalse(new BoardState(ongoingBoard).isFull());
    }

    @Test
    public void isFull_emptyBoard() {
        assertFalse(new BoardState().isFull());
    }
}
