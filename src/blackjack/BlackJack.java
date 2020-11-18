package blackjack;

import casino.Player;
import constants.Constants;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author quikks1lver
 */
public class BlackJack
{
    // Member variables
    private Player player;
    private boolean play;
    private Scanner scan;
    private Deck deck;
    
    private Dealer dealer;
    private Card card;
    
    // Custom constructor
    public BlackJack(Player player)
    {
        this.player = player;
        this.scan = new Scanner(System.in);
        this.play = true;
        this.deck = new Deck();
        
        this.dealer = new Dealer();
        this.card = new Card();
    }
    
    // Displays BJ rules to user
    private void displayRules()
    {
        System.out.println("\nLet's play Black Jack!");
        System.out.println("Your cash balance is $" + this.player.getCash());
        System.out.println("The bet is $" + Constants.BJ_BET);
        System.out.println("Dealer must HIT if their score is 16 or less");
        System.out.println("Dealer must STAND if their score is 17 or higher");
        System.out.println("If the player wins the hand, they receive $" + Constants.BJ_WIN);
        System.out.println("If the dealer wins the hand, the $" + Constants.BJ_BET + " bet is lost");
        System.out.println("If it is a PUSH, the player keeps their $" + Constants.BJ_BET + " bet\n");
    }
    
    // Simulates a game of BJ
    public void play()
    {
        this.displayRules();
        
        if (this.player.getCash() < Constants.BJ_BET) {
            System.out.println("You are out of cash, you cannot play");
            this.play = false;
        }
        
        while (play) {
            System.out.println("\nPlayer's cash $" + this.player.getCash());
            
            if (this.player.getCash() < Constants.BJ_BET) {
                System.out.println("You are out of cash, you cannot play");
                this.play = false;
                break;
            }
            
            // deduct bet from player
            this.player.setCash(this.player.getCash() - Constants.BJ_BET);
            
            System.out.println("Dealing . . .");
            
            // reset player's fields
            this.player.setHand(new ArrayList<>());
            this.player.setScore(Constants.ZERO);
            this.player.setBusted(false);
            
            // reset dealer's fields
            this.dealer.setHand(new ArrayList<>());
            this.dealer.setScore(Constants.ZERO);
            
            // loop 2x to deal player 2 cards
            for (int i = 0; i < 2; i++) {
                card = this.dealer.deal(deck);
                this.player.getHand().add(card);
            }
            
            // display score
            System.out.println("Player's score " + this.player.getScore());
            
            // loop 2x to deal dealer 2 cards
            for (int i = 0; i < 2; i++) {
                card = this.dealer.deal(deck);
                this.dealer.getHand().add(card);
            }
            
            // Continue while player wants to hit
            while (this.player.hitOrStand() == Constants.HIT) {
                card = this.dealer.deal(deck);
                this.player.getHand().add(card);
                
                int playerScore = this.player.getScore();
                System.out.println("Player's score " + playerScore);
                
                if (playerScore > Constants.BUST) {
                    System.out.println("Player busted! You lost the hand!");
                    this.player.setBusted(true);
                    break;
                }
            }
            
            if (this.player.isBusted()) {
                this.play = this.player.playAgain();
                continue;
            } else {
                System.out.println("Dealer's score " + this.dealer.getScore());
                
                while (this.dealer.hitOrStand() == Constants.HIT) {
                    card = this.dealer.deal(deck);
                    this.dealer.getHand().add(card);
                    
                    int dealerScore = this.dealer.getScore();
                    System.out.println("Dealer's score " + dealerScore);
                    
                    if (dealerScore > Constants.BUST) {
                        System.out.println("Dealer busted!");
                        break;
                    }
                }
            }
            
            this.results();
            
            this.play = this.player.playAgain();
        } // end of giant while loop in play()
    } // end of play()
    
    // Outputs results of a bj game
    private void results() {
        int dealerScore = this.dealer.getScore();
        int playerScore = this.player.getScore();
        int winnings = 0;
        
        System.out.println("Player's score " + playerScore);
        System.out.println("Dealer's score " + dealerScore);
        
        if (dealerScore > Constants.BUST) {
            System.out.println("Dealer busted! Player wins $" + Constants.BJ_WIN);
            winnings = Constants.BJ_WIN;
        }
        else if (dealerScore == playerScore) {
            System.out.println("It was a PUSH! Player wins $10");
            winnings = 10;
        }
        else if (playerScore > dealerScore) {
            System.out.println("Player WINS the hand! Player wins $" + Constants.BJ_WIN);
            winnings = Constants.BJ_WIN;
        }
        else {
            System.out.println("Dealer WINS the hand!");
        }
        
        this.player.setCash(this.player.getCash() + winnings);
    }
}
