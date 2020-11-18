package slots;

import casino.Player;
import java.util.Random;
import java.util.Scanner;
import constants.Constants;

/**
 *
 * @author quikks1lver
 */
public class Slots
{
    private final int SLOTS = 3;
    private final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private char spins[];
    private Random rand;
    private Player player;
    private boolean play;
    private Scanner scan;
    
    // Custom constructor
    public Slots(Player player)
    {
        this.player = player;
        this.scan = new Scanner(System.in);
        this.play = true;
        this.rand = new Random();
        this.spins = new char[3];
    }
    
    // Helper method to display slots game prompt
    private void displayGamePrompt()
    {
        System.out.println("\nLet's play the slot machines!");
        System.out.println("Your cash balance is $" + this.player.getCash());
        System.out.println("The bet is $" + Constants.BET);
        System.out.println("Match two symbols to win $" + Constants.PAIR_PAYOUT);
        System.out.println("Match all three symbols to win $" + Constants.TRIPLE_PAYOUT);
        System.out.println("");
    }
    
    // Generates a random character symbol
    private char randomSymbol()
    {
        int num;
        char symbol = ' ';
        
        num = this.rand.nextInt(this.SYMBOLS.length);
        
        switch (num)
        {
            case 0:
                symbol = this.SYMBOLS[0];
                break;
            case 1:
                symbol = this.SYMBOLS[1];
                break;
            case 2:
                symbol = this.SYMBOLS[2];
                break;
            case 3:
                symbol = this.SYMBOLS[3];
                break;
            case 4:
                symbol = this.SYMBOLS[4];
                break;
            case 5:
                symbol = this.SYMBOLS[5];
                break;
            case 6:
                symbol = this.SYMBOLS[6];
                break;
            default:
                symbol = ' ';
        }
        
        return symbol;
    }
    
    // Plays slots game
    public void play()
    {
        int input;
        
        this.displayGamePrompt();
        
        // Player cannot play if they have less than $5
        if (this.player.getCash() < 5)
        {
            System.out.println("The player, " + this.player.getName() + ", doesn't have enough cash.");
            this.play = false;
        }
        
        int numMatches = 0;
        // Continue playing while the user wants to play
        while (play == true)
        {
            this.player.setCash(this.player.getCash() - Constants.BET);
            System.out.println("\nSpinning...");
            
            for (int i = 0; i < this.spins.length; i++)
            {
                this.spins[i] = this.randomSymbol();
                System.out.printf("%c %s", this.spins[i], (i == this.spins.length - 1) ? "\n" : "");
            }
            
            // Check results of slots
            if (this.spins[0] == this.spins[1] && this.spins[1] == this.spins[2])
                numMatches = 3;
            else if (this.spins[0] == this.spins[1])
                numMatches = 2;
            else if (this.spins[1] == this.spins[2])
                numMatches = 2;
            else if (this.spins[0] == this.spins[2])
                numMatches = 2;
            else
                numMatches = 0;
            
            // Payout screen
            if (numMatches == 3)
            {
                System.out.printf("Three symbols matched, you won $%d!\n", Constants.TRIPLE_PAYOUT);
                this.player.setCash(this.player.getCash() + Constants.TRIPLE_PAYOUT);
            }
            else if (numMatches == 2)
            {
                System.out.printf("Two symbols matched, you won $%d!\n", Constants.PAIR_PAYOUT);
                this.player.setCash(this.player.getCash() + Constants.PAIR_PAYOUT);
            }
            else
                System.out.println("No symbols matched. Better luck next time.");
            
            // Print cash balance
            System.out.println("\nCASH = $" + this.player.getCash());
            
            if (this.player.getCash() >= 5)
            {
                System.out.println("Would you like to spin again (Yes = 1, No = 0)?");
                input = this.scan.nextInt();
                
                this.play = (input == 1) ? true : false;
                if (this.play == false)
                    break;
            }
            else
            {
                System.out.println("You are out of cash, you cannot play");
                break;
            }
        }
        
        System.out.println("\nThank you for playing slots at Knights Casino! Your cash out is $" + this.player.getCash());
    }
}
