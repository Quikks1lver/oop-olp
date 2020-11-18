package casino;

import blackjack.BlackJack;
import java.util.Scanner;
import scratchOffs.ScratchOffs;
import slots.Slots;
import constants.Constants;

/**
 * @author quikks1lver / Adam F
 * COP 3330 w/Prof. Whiting
 */

// Class simulating a casino
public class Casino
{
    // Declaring instance variables
    private static BlackJack blackJack;
    private static ScratchOffs scratchers;
    private static Slots slots;
    private static Player player;
    private static Scanner scan;
    
    public static void main(String[] args)
    {
        // stores the player's game selection
        int game;
        
        // instantiate objects
        scan = new Scanner(System.in);
        player = new Player();
        
        game = displayMenu();
        
        // Only instantiate the game object that the user wants to play
        switch (game)
        {
            case Constants.BLACK_JACK:
                blackJack = new BlackJack(player);
                blackJack.play();
                break;
            case Constants.SCRATCH:
                scratchers = new ScratchOffs(player);
                scratchers.play();
                break;
            case Constants.SLOTS:
                slots = new Slots(player);
                slots.play();
                break;
            default:
                System.out.println("Invalid game selection, try again");
        }
    }
    
    // Helper method: displays menu prompt
    private static void displayMenuPrompt()
    {
        System.out.println("Welcome to Knights Casino!\n");
        System.out.println("\nSelect the game to play");
        System.out.println("1. Black Jack\n2. Scratch Off Tickets\n3. Slot Machines");
        System.out.println("Enter the number of your choice:");
    }
    
    // Displays menu for user, asking which game to play; returns game (as a number)
    private static int displayMenu()
    {
       int select;
       
       do
       {
           displayMenuPrompt();
           select = scan.nextInt();           
       } while (select < Constants.BLACK_JACK || select > Constants.SLOTS);
       
       return select;
    }
}
