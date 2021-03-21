package de.opicht.ft.facharbeit.agents.util;

import java.util.Comparator;
import java.util.Optional;

public class Tree {

    public final Node rootNode;

    public Tree(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Node selectLeafNode() {
        Node currentNode = rootNode;

        while (!currentNode.childNodes.isEmpty()) {
            currentNode = UpperConfidenceBound.getBestNodeWithUct(currentNode);
        }

        return currentNode;
    }

    public void updateTree(Node leafNode, double simulationResult) {
        Node targetNode = leafNode;
        while (targetNode != null) {
            targetNode.increaseVisitCount(1);
            targetNode.increaseWinCount(simulationResult);
            targetNode = targetNode.parentNode;
            simulationResult = Math.abs(simulationResult - 1);
        }
    }

    public Node getMostPromisingNode() {
        Optional<Node> promisingNode = rootNode.childNodes.stream().max(Comparator.comparingDouble(Node::getWinChance));
        return promisingNode.orElseThrow(IllegalStateException::new);
    }

}
