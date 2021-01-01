package com.company;


import java.util.Scanner;

/**
 * Represents a human player who choose his card himself.
 *
 * @author Valeh
 */
public class HumanPlayer extends Player {


    public HumanPlayer(String name){
        super();
        this.name = name;
    }


    @Override
    public void showPlayerCards() {
        System.out.println("You cards '" + name + "':");
        super.showPlayerCards();
    }

    @Override
    public UnoCard play() {
        showPlayerCards();
        System.out.println("\u001B[100m" + "Choose one of your cards or enter 0 for draw a card from draw pile" + "\u001B[0m");
        Scanner scan = new Scanner(System.in);
        int numCard = scan.nextInt() - 1; //index starts from 0

        if (numCard == -1){ //player wants to draw a card
            return drawFromDrawPile(Uno.drawPile.get(0));
        }

        UnoCard card = getCardToPlay(numCard); //card that player chose
        if (isValidPlay(Uno.topCard,card)){
            removeCard(card);
        }
        else {
            System.out.println("You can't put this card :/");
            return null;
        }
        return card;
    }

    /**
     * ask player witch color wants
     * @return
     */
    @Override
    public String chooseColor() {
        System.out.println("Choose color: Red Blue Green Yellow");
        Scanner scan = new Scanner(System.in);
        String colorChose = null;
        char tmp = scan.nextLine().charAt(0);
        switch (tmp){
            case 'R' :
                colorChose = GameCards.RED;
                break;
            case 'B' :
                colorChose = GameCards.BLUE;
                break;
            case 'G' :
                colorChose = GameCards.GREEN;
                break;
            case 'Y' :
                colorChose = GameCards.YELLOW;
                break;
        }
        System.out.println("Color changed to " + colorChose + "color" +  "\u001B[0m");
        return colorChose;
    }

   /* @Override
    public UnoCard putDraw() {

        System.out.println("\u001B[100m" + "Choose one of your cards" + "\u001B[0m");
        showPlayerCards();
        Scanner scan = new Scanner(System.in);
        int numCard = scan.nextInt() - 1; //index starts from 0
        UnoCard tmp = cards.get(numCard);
        if (tmp instanceof ActionCard && ((ActionCard) tmp).getAction().equals("draw+2") ){
            return tmp;
        }
        else {
            return null;
        }

    }*/
}
