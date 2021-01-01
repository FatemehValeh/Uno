package com.company;

/**
 * Represents a colorful card in Uno game, it can has a number or an effect. Colors are blue, green, yellow and red.
 *
 * @author Valeh
 */
public class ColorCard extends UnoCard{
    //color of the card
    String color;

    public ColorCard(String color){
        this.color = color;
    }

    /**
     * @return color of the card
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Uno Card must shown in color
     */
    @Override
    public void displayTop() {
        System.out.print(color);
        super.displayTop();
    }

    @Override
    public void displayBottom() {
        System.out.print(color);
        super.displayBottom();

    }
}
