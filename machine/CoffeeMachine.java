package machine;

import machine.enumeration.State;

import java.util.Scanner;

public class CoffeeMachine {

    private Scanner sc = new Scanner(System.in);
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;
    private State state;

    public CoffeeMachine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        this.state = State.CHOOSING_ACTION;
    }

    public void processInput(String input) {
        switch (state) {
            case CHOOSING_ACTION -> {
                switch (input) {
                    case "buy" -> sellCoffee();
                    case "fill" -> promptWater();
                    case "take" -> giveMoney();
                    case "remaining" -> getInfo();
                }
            }
            case CHOOSING_DRINK -> {
                switch (input) {
                    case "1" -> makeEspresso();
                    case "2" -> makeLatte();
                    case "3" -> makeCappuccino();
                    case "back" -> state = State.CHOOSING_ACTION;
                }
            }
            case WAITING_FOR_WATER -> {
                water += Integer.parseInt(input);
                promptMilk();
            }
            case WAITING_FOR_MILK -> {
                milk += Integer.parseInt(input);
                promptCoffee();
            }
            case WAITING_FOR_COFFEE -> {
                coffee += Integer.parseInt(input);
                promptCups();
            }
            case WAITING_FOR_CUPS -> {
                cups += Integer.parseInt(input);
                state = State.CHOOSING_ACTION;
            }
        }
    }

    private void sellCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        state = State.CHOOSING_DRINK;
    }

    private void promptWater() {
        System.out.println("Write how many ml of water you want to add:");
        state = State.WAITING_FOR_WATER;
    }

    private void promptMilk() {
        System.out.println("Write how many ml of milk you want to add:");
        state = State.WAITING_FOR_MILK;
    }

    private void promptCoffee() {
        System.out.println("Write how many grams of coffee beans you want to add:");
        state = State.WAITING_FOR_COFFEE;
    }

    private void promptCups() {
        System.out.println("Write how many disposable cups you want to add:");
        state = State.WAITING_FOR_CUPS;
    }

    public void giveMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void getInfo() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffee + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    private void makeEspresso() {
        if (water < 250) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee < 16) {
            System.out.println("Sorry, not enough coffee beans!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 250;
            coffee -= 16;
            money += 4;
            cups--;
        }
        state = State.CHOOSING_ACTION;
    }

    private void makeLatte() {
        if (water < 350) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < 75) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffee < 20) {
            System.out.println("Sorry, not enough coffee beans!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 350;
            milk -= 75;
            coffee -= 20;
            money += 7;
            cups--;
        }
        state = State.CHOOSING_ACTION;
    }

    private void makeCappuccino() {
        if (water < 200) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < 100) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffee < 12) {
            System.out.println("Sorry, not enough coffee beans!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= 200;
            milk -= 100;
            coffee -= 12;
            money += 6;
            cups--;
        }
        state = State.CHOOSING_ACTION;
    }

    public State getState() {
        return state;
    }
}
