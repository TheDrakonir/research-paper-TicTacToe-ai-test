package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;

public interface Agent {
    
    public String getAgentIdentifier();

    public Move determineNextMove(BoardState boardState, Players self);

    public Agent getCopy();

}
