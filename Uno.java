package com.company;


import java.util.*;

/**
 * Represents Uno game based on Uno rules.
 *
 * @author Valeh
 */
public class Uno {

    static ArrayList<UnoCard> drawPile;
    GameCards gameCards;
    static UnoCard topCard;
    private UnoCard firstCard;
    private ArrayList<Player> players;
    private int direction;//shows the direction of game, 1 for clockwise, 0 for counter clockwise.
    private int turn;//shows turn of players, who must play based on index of array list
    private int flag;
    static String colorChose;

    public Uno(){
        gameCards = new GameCards();
        drawPile = gameCards.getDrawPile();
        players = new ArrayList<Player>();
        direction = 1; //game starts clockwise

    }


    /**
     * give each player in the game 7 cards at first
     * @param players players in the game
     */
    public void giveInitialCards(ArrayList<Player> players){
        for (Player player: players) {

            for (int i = 0; i <7; i++) {
                Random rand = new Random();
                //cards give randomly
                int tmp = rand.nextInt(drawPile.size());
                //card add to player's cards
                player.addCard(drawPile.get(tmp));
                //update draw pile
                drawPile.remove(tmp);
            }
        }
    }

    /**
     * when a player puts a card, top card must change and last top card is added to draw pile.
     * @param lastCard the card last player put
     */
    public void updateTopCard(UnoCard lastCard){
        drawPile.add(topCard);
        topCard = lastCard;
    }

    static void updateDrawPile(UnoCard card){
        drawPile.remove(card);
    }

    /**
     * display top card
     */
    public void displayTopCard(){
        if (direction == 1){
            System.out.println("clockwise");
        }
        else
            System.out.println("counter clockwise");
        displayNumOfCards();
        System.out.println("\u001B[30m" + "    TOP CARD:" +  "\u001B[0m");
        topCard.display();
        System.out.println("-----------------");
    }

    /**
     * first player who starts the game determines randomly
     * @return index of starter player in player array list between 0 and array list's size - 1.
     */
    public int starterPlayer(){
        return new Random().nextInt(players.size());
    }

    public void topCardEffect(){
        //apply effects of top card if it is an action card
        if (flag==0 && topCard instanceof ActionCard){
            String action = ((ActionCard) topCard).getAction();
            //effects of action cards
            if (action.equals(GameCards.SKIP)){
                manageTurn();

            }
            if (action.equals(GameCards.REVERSE)){
                reverse();//direction of changing turns change

            }
            if (action.equals("draw+2")){
                displayTopCard();
                int numOfCardToAdd = 2;
                manageTurn();
                while (players.get(turn).putDraw() != null){
                    UnoCard tmp = players.get(turn).putDraw();
                    players.get(turn).removeCard(tmp);
                    updateTopCard(tmp);
                    displayTopCard();
                    manageTurn();
                    numOfCardToAdd += 2;
                }
                System.out.println("1 turn= " + turn );
                players.get(turn).getCardFromDrawPile(numOfCardToAdd);

            }

        }

        if (flag==0 && topCard instanceof WildCard){
            colorChose = players.get(turn).chooseColor();

            if (topCard instanceof WildDrawCard){
                manageTurn();
                players.get(turn).getCardFromDrawPile(4);
            }

        }

        manageTurn();

    }

    /**
     * apply effect of first card in game
     */
    public void firstCardEffect(){

        if ( firstCard instanceof ActionCard){
            String action = ((ActionCard) firstCard).getAction();
            //effects of action cards
            if (action.equals(GameCards.SKIP)){

            }
            if (action.equals(GameCards.REVERSE)){
                reverse();//direction of changing turns change

            }
            if (action.equals("draw+2")){
                System.out.println("1 turn= " + turn );
                players.get(turn).getCardFromDrawPile(2);

            }
            manageTurn();
        }

    }

    /**
     * checks if a player won, so the game is over
     * @return
     */
    public boolean noWinner(){
        for (Player foo : players){
            if (foo.getNumberOfCards()==0)
                return false;
        }
        return true;
    }

    /**
     * a method that determine whose player is turn
     */
    public void manageTurn(){
        if (direction == 1){ //turn is clockwise

            if (turn<0 || turn>=players.size() - 1){
                turn = 0;
            }
            else
                turn++;
        }
        else { //turn is counter clockwise
            if (turn==0){
                turn = players.size() - 1;
            }
            else
                turn--;
        }
    }

    public void reverse(){
        if (direction == 1){
            direction = 0;
        }
        else
            direction = 1;
    }

    /**
     * show number of cards players have
     */
    public void displayNumOfCards(){
        for (int i=0 ; i<players.size(); i++){
            System.out.print("player" + (i+1) + ": " + players.get(i).getNumberOfCards() + " cards     ");
        }
        System.out.println();
    }

    public void displayPlayerTable(){
        int[] scores = new int[players.size()];

        for (int i=0 ; i< players.size(); i++){
            int sum = 0;
            for (UnoCard tmp : players.get(i).getCards()){
                sum += tmp.getScore();
            }
            scores[i] = sum;
        }

        int temp;
        //sort array ascending
        for (int i=0; i< players.size(); i++){
            for (int j = i + 1; j< players.size(); j++){

                if (scores[i] > scores[j]){
                    temp = scores[i];
                    scores[i] = scores[j];
                    scores[j] = temp;
                }

            }
        }
        int sum = 0;
        String winner = null;
        for (int i=0; i<players.size(); i++){
            for (Player tmp : players){
                for (UnoCard foo : tmp.getCards()){
                    sum += foo.getScore();
                }

                if (sum == scores[i]) {
                    System.out.println(tmp.getName() + ": " + scores[i] + " scores.");
                    if (i==0){
                        winner = tmp.getName();
                    }
                }
                sum = 0;

            }

        }

        System.out.println("Winner is " + winner);


    }

    public void playUno(){

        System.out.println("Welcome to Uno :)" + "\n" + "How many players do you want to have?");
        Scanner scan = new Scanner(System.in);
        int numOfPlayers = scan.nextInt();
        System.out.println("Do you want to play with computer?" + "\n" + "1)Yes            2)No");
        int gameType = scan.nextInt();
        System.out.println(gameType);
        scan.nextLine();




        if (gameType == 1){

            System.out.println("Enter your name:");
            String playerName = scan.nextLine();
            players.add(new HumanPlayer(playerName)); //add single human player

            for (int i=0 ; i< numOfPlayers-1 ; i++){
                players.add(new ComputerPlayer()); //add computer players
            }

        }

        if (gameType == 2){
            System.out.println("Enter players' names:");
            for (int i=0 ; i<numOfPlayers; i++){
                String name = scan.nextLine();
                players.add(new HumanPlayer(name));
            }
        }



        giveInitialCards(players); // giving 7 cards to all players

        players.get(0).addCard(new ActionCard(GameCards.RED, "draw+2"));
        players.get(1).addCard(new ActionCard(GameCards.RED, "draw+2"));



        firstCard = new WildCard();
        //first top card must be a colorful card and chosen randomly
        while (firstCard instanceof WildCard){
            int firstCardi = new Random().nextInt(drawPile.size());
            firstCard = drawPile.get(firstCardi);
        }



        updateDrawPile(firstCard); //top card must be removed from draw pile

        turn = starterPlayer();//index of player in array list which must start the game



        firstCardEffect();

        flag = 0;



        topCard = firstCard;
        displayTopCard();


        while (noWinner()){


            System.out.println("turn player= " + (turn+1) );
            UnoCard currentPlaying = players.get(turn).play();
            if (currentPlaying != null){
                updateTopCard(currentPlaying);
                flag = 0;
            }
            else {
                flag = 1;
            }

            topCardEffect();
            displayTopCard();



        }
        displayPlayerTable();












    }
}
