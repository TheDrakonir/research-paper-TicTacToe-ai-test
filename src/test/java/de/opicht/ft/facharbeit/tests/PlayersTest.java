package de.opicht.ft.facharbeit.tests;

import static org.junit.Assert.assertSame;

import java.util.Optional;

import org.junit.Test;

import de.opicht.ft.facharbeit.game.Players;
import de.opicht.ft.facharbeit.game.PositionState;

public class PlayersTest {

    @Test
    public void getByPositionState_playerPositions() {
        assertSame(Players.PLAYER_X,
                Players.getByPositionState(PositionState.OCCUPIED_BY_X).orElseThrow(IllegalStateException::new));
        assertSame(Players.PLAYER_O,
                Players.getByPositionState(PositionState.OCCUPIED_BY_O).orElseThrow(IllegalStateException::new));
    }

    @Test
    public void getByPositionState_emptyPosition() {
        assertSame(Optional.empty(), Players.getByPositionState(PositionState.EMPTY));
    }

    @Test
    public void getByPositionState_null() {
        assertSame(Optional.empty(), Players.getByPositionState(null));
    }

    @Test
    public void getOtherPlayer_X() {
        assertSame(Players.PLAYER_O, Players.getOtherPlayer(Players.PLAYER_X));
    }

    @Test
    public void getOtherPlayer_O() {
        assertSame(Players.PLAYER_X, Players.getOtherPlayer(Players.PLAYER_O));
    }

}
