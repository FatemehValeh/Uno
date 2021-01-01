package com.company;

/**
 * Represents a general card in Uno game.
 *
 * @author Valeh
 */
public  class UnoCard {

    protected int score; //score of each card

    /**
     * set the score each card has
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public  void displayTop(){
        System.out.println("|$$$$$$$$$$$$$$$|");
        System.out.println("|               |");
    }
    public  void displayBottom(){
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|");
        System.out.println("\u001B[0m");
    }

    public void display(){}

    public String getColor(){
        return null;
    }

}
