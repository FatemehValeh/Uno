package com.company;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[36m";

    public static void main(String[] args) {

        Uno uno = new Uno();
       /* uno.giveInitialCards(player1);
        uno.giveInitialCards(player2);*/

        uno.playUno();
    }
}
