package casino;

import blackjack.Card;
import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author quikks1lver
 */
public class Player implements IPlayer
{
    // member variables    
    private String name;
    private int cash;
    private Scanner scan;
    
    private ArrayList<Card> hand;
    private int score;
    private boolean busted;
    private boolean play;
    
    // Custom constructor
    public Player()
    {
        scan = new Scanner(System.in);
        
        // local variables for scanning into
        String name;
        int money;
        
        System.out.println("Enter player name");
        name = scan.next();
        System.out.println("Enter amount of money to play (minimum $10)");
        money = scan.nextInt();
        
        setName(name);
        setCash(money);
    }
    
    @Override
    public int hitOrStand() {
        int choice;
        
        System.out.println("Player, another card? (0 = STAND, 1 = HIT)");
        choice = scan.nextInt();
        
        if (choice == Constants.HIT) return Constants.HIT;
        return Constants.STAND;
    }
    
    // Ask user if they want to play again
    public boolean playAgain() {
        int choice;
        
        System.out.println("\nPlayer, play another hand? (0 = No, 1 = Yes)");
        choice = scan.nextInt();
        
        if (choice == 1) return true;
        return false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cash
     */
    public int getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * @return the score
     */
    public int getScore() {
        this.score = 0;
        
        for (Card c : this.getHand()) {
            this.score += c.getValue();
        }
        
        return this.score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the busted
     */
    public boolean isBusted() {
        return busted;
    }

    /**
     * @param busted the busted to set
     */
    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    /**
     * @return the play
     */
    public boolean isPlay() {
        return play;
    }

    /**
     * @param play the play to set
     */
    public void setPlay(boolean play) {
        this.play = play;
    }
    
    
}
