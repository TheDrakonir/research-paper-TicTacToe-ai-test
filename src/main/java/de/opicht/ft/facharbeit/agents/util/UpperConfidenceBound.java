package de.opicht.ft.facharbeit.agents.util;

import java.util.Comparator;

public class UpperConfidenceBound {

    // approximation for sqrt(2)
    private static final double EXPLORATION_PARAMETER = 1.41D;

    private UpperConfidenceBound() {
        // Utility class
    }

    /**
     * @return the best evaluated node based on the Upper Confidence Bound applied
     *         to trees (UCT)
     */
    public static Node getBestNodeWithUct(Node node) {
        return node.childNodes.stream().max(Comparator.comparingDouble(UpperConfidenceBound::computeUct)).orElseThrow(IllegalStateException::new);
    }

    private static double computeUct(Node node) {
        return computeUct(node.parentNode.getVisitCount(), node.getVisitCount(), node.getWinCount());
    }

    private static double computeUct(int parentVisits, int nodeVisits, double nodeWins) {
        if (nodeVisits == 0) {
            return Double.MAX_VALUE;
        }

        return (nodeWins / nodeVisits) + EXPLORATION_PARAMETER * Math.sqrt(Math.log(parentVisits) / nodeVisits);
    }

}
