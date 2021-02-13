package de.opicht.ft.facharbeit.game;

public enum PositionState {
    
    OCCUPIED_BY_X("X"),
    OCCUPIED_BY_O("O"),
    EMPTY(" ");

    public final String displaySymbol;

    private PositionState(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

}
