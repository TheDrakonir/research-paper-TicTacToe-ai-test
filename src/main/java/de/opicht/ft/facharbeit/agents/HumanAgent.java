package de.opicht.ft.facharbeit.agents;

import java.util.Scanner;

import de.opicht.ft.facharbeit.game.BoardState;
import de.opicht.ft.facharbeit.game.Move;

public class HumanAgent implements Agent {

    private final Scanner inputScanner;

    public HumanAgent() {
        this.inputScanner = new Scanner(System.in);
    }

    @Override
    public String getAgentIdentifier() {
        return "Human player";
    }

    @Override
    public Move determineNextMove(BoardState boardState) {
        System.out.println("Input the number of the field you want to choose. (1-9, left to right, top to bottom)");
        int targetField = 0;
        do {
            try {
                targetField = Integer.parseInt(inputScanner.nextLine());

                if (targetField < 1 || targetField > 9) {
                    targetField = 0;
                    System.out.println("Input a valid field number, please!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Input a valid field number, please!");
            }
        } while (targetField == 0);

        return new Move((targetField - 1) / 3, (targetField - 1) % 3);
    }
    
}
