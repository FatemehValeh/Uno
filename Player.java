package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Player {

    protected ArrayList<UnoCard> cards;
    protected String name;


    public Player(){
        cards = new ArrayList<UnoCard>();
    }

    public String getName() {
        return name;
    }

    public void addCard(UnoCard cardToAdd){
        cards.add(cardToAdd);
    }

    public void removeCard(UnoCard cardToRemove){
        cards.remove(cardToRemove);
    }

    public int getNumberOfCards(){
        int res = cards.size();
        return res;
    }

    public UnoCard getCardToPlay(int num){
        return cards.get(num);
    }

    /**
     * @return cards a player has
     */
    public ArrayList<UnoCard> getCards() {
        return cards;
    }

    /**
     * check if the card player wants to put matches with last top card in terms of Uno rules
     * @param topCard last top card played
     * @param cardToPlay card player wants to put
     * @return true if player can play the cart, false if not
     */
    public boolean isValidPlay(UnoCard topCard, UnoCard cardToPlay){
        //you can put top card over each card
        if (cardToPlay instanceof WildCard){
            return true;
        }
        else if (topCard instanceof WildCard){
            if (cardToPlay.getColor().equals(Uno.colorChose)){
                return true;
            }
        }
        //if colors match return true
        else if (cardToPlay.getColor().equals(topCard.getColor())){
            return true;
        }
        //if numbers match
        else if (cardToPlay instanceof NumericCard && topCard instanceof NumericCard){
            if ( ((NumericCard) topCard).getNumber() == ((NumericCard) cardToPlay).getNumber() ){
                return true;
            }
        }
        //if actions on cards match
        else if (cardToPlay instanceof ActionCard && topCard instanceof ActionCard){
            if (  ((ActionCard) topCard).getAction().equals( ( (ActionCard) cardToPlay ).getAction() ) )
                return true;
        }
        return false;
    }

    /**
     * if one can't match any of his cards to the top card, must draw from draw pile then try to put that card.
     * @param cardToGet
     */
    public UnoCard drawFromDrawPile(UnoCard cardToGet){

        Uno.updateDrawPile(cardToGet);
        if (isValidPlay(Uno.topCard,cardToGet)){
            return cardToGet;
        }
        else {
            addCard(cardToGet);
            return null;
        }


    }

    /**
     * used when draw+2 or wild draw happens
     * @param numOfCardsToAdd
     */
    public void getCardFromDrawPile(int numOfCardsToAdd){
        for (int i=0; i<numOfCardsToAdd; i++){
            int tmp = new Random().nextInt(Uno.drawPile.size());
            UnoCard foo = Uno.drawPile.get(tmp);
            addCard(foo);
            Uno.updateDrawPile(foo);
        }
    }


    public UnoCard play(){
        return null;
    }

    public void showPlayerCards(){

        int i=1;
        for (UnoCard foo : cards){
            System.out.println(i + ")");
            foo.display();
            i++;
        }
        System.out.println("-----------------");
        System.out.println();
    }

    /**
     * choose a color when put a wild card
      * @return color
     */
    public String chooseColor(){
        return null;
    }

    /**
     * check if a player can put a draw card after another draw card
     * @return true if one can, false if can not
     */
    public UnoCard putDraw(){
        UnoCard res = null;
        for (UnoCard foo: cards){
            if (foo instanceof ActionCard && ((ActionCard) foo).getAction().equals("draw+2")){
                res = foo;
                break;
            }
        }
        return res;
    }

}
