package de.opicht.ft.facharbeit.agents;

import de.opicht.ft.facharbeit.agents.util.Node;
import de.opicht.ft.facharbeit.agents.util.Tree;
import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;

public class MonteCarloTreeSearchAgent implements Agent {

    private static final long TIME_CONSTRAINT_MILLIS = 5 * 1000L;

    @Override
    public String getAgentIdentifier() {
        return "Monte Carlo Tree Search - light play";
    }

    @Override
    public Move determineNextMove(BoardState boardState, Players self) {
        long startTime = System.currentTimeMillis();
        Tree tree = new Tree(new Node(null, boardState, Players.getOtherPlayer(self), null));

        while (startTime + TIME_CONSTRAINT_MILLIS > System.currentTimeMillis()) {
            // Selection
            Node leafNode = tree.selectLeafNode();
    
            // Expansion
            Node expandedNode = leafNode;
            if (!leafNode.boardState.isFinished()) {
                expandedNode = leafNode.createRandomChildNode();
            }
    
            // Simulation
            double simulationResult = simulateNode(expandedNode);
    
            // Backpropagation
            tree.updateTree(expandedNode, simulationResult);
        }

        return tree.getMostPromisingNode().move;
    }

    private double simulateNode(Node node) {
        BoardState currentState = node.boardState.getBoardCopy();
        Players currentPlayer = Players.getOtherPlayer(node.player);
        RandomAgent randomAgent = new RandomAgent();

        while(!currentState.isFinished()) {
            currentState.applyMove(currentPlayer, randomAgent.determineNextMove(currentState, currentPlayer));
            currentPlayer = Players.getOtherPlayer(currentPlayer);
        }

        if (!currentState.isWon()) {
            return .5;
        }

        return currentState.getScore(node.player) / 10D;
    }

    @Override
    public Agent getCopy() {
        return new MonteCarloTreeSearchAgent();
    }
}
