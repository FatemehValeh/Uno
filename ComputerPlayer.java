package com.company;

/**
 * Represents a computer player. Computer choose card.
 */
public class ComputerPlayer extends Player{


    public ComputerPlayer(){
        super();
        name = "Computer";
    }

    @Override
    public UnoCard play() {
        System.out.println("Computer turn:");
        //showPlayerCards();
        //finding a card that match with top card in player cards
        for (UnoCard foo : cards){
            if (isValidPlay(Uno.topCard,foo)){
                removeCard(foo);
                return foo;
            }
        }
        //if there is no match, draw from draw pile
        return drawFromDrawPile(Uno.drawPile.get(0));

    }

    @Override
    public String chooseColor() {
        String color = GameCards.RED;
        for (UnoCard foo : cards){
            if (foo.getColor() != null){
                color = foo.getColor();
                break;
            }
        }
        System.out.println("Color changed to " + color + "color" +  "\u001B[0m");
        return color;

    }
}
