package de.opicht.ft.facharbeit.tests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import de.opicht.ft.facharbeit.game.Players;
import de.opicht.ft.facharbeit.game.WinState;

public class WinStateTest {

    @Test
    public void combine_oneWin() {
        assertEquals(new WinState(true, Optional.of(Players.PLAYER_X)),
                new WinState(true, Optional.of(Players.PLAYER_X)).combine(new WinState(false, Optional.empty())));
    }

    @Test
    public void combine_noWin() {
        assertEquals(new WinState(false, Optional.empty()),
                new WinState(false, Optional.empty()).combine(new WinState(false, Optional.empty())));
    }
}
