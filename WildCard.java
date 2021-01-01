package com.company;

/**
 * It represents a Wild Card in Uno game.
 */
public class WildCard extends UnoCard {

    public WildCard(){
        score = 50;
    }
    /**
     * display the card in console
     */
    public void display(){
        displayTop();
        System.out.println("|      " + "wild" + "     |");
        displayBottom();
    }
}
