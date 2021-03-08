package de.opicht.ft.facharbeit.game;

import java.util.Optional;

public class WinState {

    public final boolean isWon;
    public final Optional<Players> winner;

    public WinState(boolean isWon, Optional<Players> winner) {
        this.isWon = isWon;
        this.winner = winner;
    }

    public WinState combine(WinState other) {
        return new WinState(isWon || other.isWon, winner.or(() -> other.winner));
    }

}
