package machine;

import machine.enumeration.State;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            if (machine.getState() == State.CHOOSING_ACTION) {
                System.out.println();
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
            }
            input = sc.nextLine();

            System.out.println();

            machine.processInput(input);
        } while (!input.equals("exit"));
    }
}
