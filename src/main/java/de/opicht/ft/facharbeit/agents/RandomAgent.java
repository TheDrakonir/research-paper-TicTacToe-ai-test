package de.opicht.ft.facharbeit.agents;

import java.util.concurrent.ThreadLocalRandom;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;
import de.opicht.ft.facharbeit.game.PositionState;

public class RandomAgent implements Agent {

    @Override
    public String getAgentIdentifier() {
        return "Random";
    }

    @Override
    public Move determineNextMove(BoardState boardState, Players self) {
        while (true) {
            int[] randomIndecies = ThreadLocalRandom.current().ints(2, 0, BoardState.BOARD_SIZE).toArray();
            
            if (boardState.board[randomIndecies[0]][randomIndecies[1]] == PositionState.EMPTY) {
                return new Move(randomIndecies[0], randomIndecies[1]);
            }
        }
    }

}
