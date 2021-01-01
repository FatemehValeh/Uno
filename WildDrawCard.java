package com.company;

/**
 * It represents a Wild Draw card in Uno game.
 */
public class WildDrawCard extends WildCard {
    /**
     * display the card
     */
    public void display(){
        displayTop();
        System.out.println("|     " + "wild+4" + "    |");
        displayBottom();
    }

}
