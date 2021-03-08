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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isWon ? 1231 : 1237);
        result = prime * result + winner.hashCode();
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
        WinState other = (WinState) obj;
        if (isWon != other.isWon)
            return false;
        return winner.equals(other.winner);
    }

}
