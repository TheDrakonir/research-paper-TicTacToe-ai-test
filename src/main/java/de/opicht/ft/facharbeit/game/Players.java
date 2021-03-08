package de.opicht.ft.facharbeit.game;

import java.util.Optional;
import java.util.stream.Stream;

public enum Players {
    
    PLAYER_X(PositionState.OCCUPIED_BY_X),
    PLAYER_O(PositionState.OCCUPIED_BY_O);

    public final PositionState positionState;

    private Players(PositionState positionState) {
        this.positionState = positionState;
    }

    public static Optional<Players> getByPositionState(PositionState positionState) {
        return Stream.of(Players.values()).filter(player -> player.positionState == positionState).findFirst();
    }

}
