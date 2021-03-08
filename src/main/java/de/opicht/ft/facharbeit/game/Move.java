package de.opicht.ft.facharbeit.game;

public class Move {

    public final int targetRow;
    public final int targetColumn;

    /**
     * @param targetRow (zero-indexed field)
     * @param targetColumn (zero-indexed field)
     */
    public Move(int targetRow, int targetColumn) {
        this.targetRow = targetRow;
        this.targetColumn = targetColumn;
    }
    
}
