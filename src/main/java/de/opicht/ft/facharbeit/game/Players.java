package de.opicht.ft.facharbeit.game;

public enum Players {
    
    PLAYER_X(PositionState.OCCUPIED_BY_X),
    PLAYER_O(PositionState.OCCUPIED_BY_O);

    public final PositionState positionState;

    private Players(PositionState positionState) {
        this.positionState = positionState;
    }

}
