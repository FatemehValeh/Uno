package com.company;

/**
 * Represents a numeric colorful card in Uno game. Numbers vary from 0 to 9.
 */
public class NumericCard extends ColorCard{
    //number on the card
    int number;

    public NumericCard(String color , int number) {
        super(color);
        this.number=number;
        score = number;

    }


    /**
     * @return number on the card
     */
    public int getNumber() {
        return number;
    }

    /**
     * display the card
     */
    public void display() {
        super.displayTop();
        System.out.println("|       " + number + "       |");
        super.displayBottom();
    }


}
