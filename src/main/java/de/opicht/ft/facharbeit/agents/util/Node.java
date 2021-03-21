package de.opicht.ft.facharbeit.agents.util;

import java.util.HashSet;
import java.util.Set;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;
import de.opicht.ft.facharbeit.game.Players;

public class Node {

    public final Node parentNode;
    public final Set<Node> childNodes;

    public final BoardState boardState;
    public final Players player;
    public final Move move;

    private double winCount;
    private int visitCount;

    public Node(Node parentNode, BoardState boardState, Players player, Move move) {
        this.parentNode = parentNode;
        this.childNodes = new HashSet<>();
        this.boardState = boardState;
        this.player = player;
        this.move = move;
    }

    public boolean isLeafNode() {
        return childNodes.isEmpty();
    }

    public Node createRandomChildNode() {
        Players appliedPlayer = Players.getOtherPlayer(player);

        boardState.getPossibleMoves().forEach(possibleMove -> {
            BoardState alteredState = boardState.getBoardCopy();
            alteredState.applyMove(appliedPlayer, possibleMove);
            childNodes.add(new Node(this, alteredState, appliedPlayer, possibleMove));
        });

        return childNodes.iterator().next();
    }

    public double getWinCount() {
        return winCount;
    }

    public void increaseWinCount(double amount) {
        this.winCount += amount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void increaseVisitCount(int amount) {
        this.visitCount += amount;
    }

    public double getWinChance() {
        return winCount / visitCount;
    }

}
