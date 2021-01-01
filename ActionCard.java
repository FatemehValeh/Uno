package com.company;

/**
 * It represents a card that has an effect. Effects are block, reverse and draw+2.
 *
 * @author Valeh
 */
public class ActionCard extends ColorCard {
    //action of the card
    private String action;

    public ActionCard(String color, String action) {
        super(color);
        this.action = action;
        score = 20;
    }

    /**
     * to show properly the effect of card, used in display method
     * @return action of card
     */
    public String getNiceAction(){
        String res = null;
        if (action.equals(GameCards.SKIP))
            res = "  " + action + "   ";
        else if (action.equals(GameCards.REVERSE))
            res = "  " + action + "   ";
        else
            res = action + " ";
        return res;
    }

    /**
     * @return action field
     */
    public String getAction() {
        return action;
    }

    /**
     * display the card
     */
    public void display(){
        super.displayTop();
        System.out.println("|    " + getNiceAction() + "    |");
        super.displayBottom();
    }
}
