package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;

public interface Agent {
    
    public String getAgentIdentifier();

    public Move determineNextMove(BoardState boardState);

}
