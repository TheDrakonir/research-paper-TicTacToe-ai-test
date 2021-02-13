package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.PositionState;

public interface Agent {
    
    public String getAgentIdentifier();

    public Move determineNextMove(PositionState[][] board);

}
