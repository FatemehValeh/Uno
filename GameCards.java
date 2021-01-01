package com.company;

import java.util.ArrayList;

/**
 * represents all cards in Uno game.
 *
 * @author Valeh
 */
public class GameCards {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[36m";
    public static final String SKIP = "\uD83D\uDEAB";
    public static final String REVERSE = "\uD83D\uDD03";

    ArrayList<UnoCard> drawPile;

    public GameCards(){
        drawPile = new ArrayList<UnoCard>();
        createCards();
    }

    /**
     * @return all generated cards
     */
    public ArrayList<UnoCard> getDrawPile() {
        return drawPile;
    }

    /**
     * create 108 cards
     */
    public void createCards(){
        //numeric cards in four colors
        newNumericCards(RED);
        newNumericCards(YELLOW);
        newNumericCards(GREEN);
        newNumericCards(BLUE);
        //action cards in four colors
        for (int i=0; i<2; i++){
            newActionCards(RED);
            newActionCards(YELLOW);
            newActionCards(GREEN);
            newActionCards(BLUE);
        }
        //wild cards and wild draw cards four of each
        for (int i=0; i<4; i++){
            newWildCards();
        }
    }

    /**
     * add a numeric card to drawPile field in game
     * @param color color of the card
     */
    public void newNumericCards(String color){
        for (int i=0; i<10; i++){
            drawPile.add(new NumericCard(color,i));
        }
        for (int i=1; i<10; i++){
            drawPile.add(new NumericCard(color, i));
        }
    }

    /**
     * add an action card to drawPile field in game
     * @param color color of the card
     */
    public void newActionCards(String color){
        drawPile.add(new ActionCard(color,SKIP));
        drawPile.add(new ActionCard(color,REVERSE));
        drawPile.add(new ActionCard(color,"draw+2"));
    }

    /**
     * add wild  and wild draw card to drawPile field in game
     */
    public void newWildCards(){
        drawPile.add(new WildCard());
        drawPile.add(new WildDrawCard());
    }

    public void show(){
        for (UnoCard foo: drawPile){
            foo.display();
        }
    }

}
