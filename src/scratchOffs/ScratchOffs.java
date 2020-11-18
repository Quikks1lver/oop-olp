package scratchOffs;

import casino.Player;
import constants.Constants;
import java.util.Scanner;

/**
 *
 * @author quikks1lver
 */
public class ScratchOffs
{
    // member variables
    private Player player;
    private OneDollar one;
    private FiveDollar five;
    private TenDollar ten;
    private boolean play;
    private Scanner scan;
    
    // custom constructor
    public ScratchOffs(Player player)
    {
        this.player = player;
        this.play = true;
        this.scan = new Scanner(System.in);
    }
    
    // Helper method which displays user's cash
    private void displayCash()
    {
        System.out.println("CASH = $" + this.player.getCash());
    }
    
    // simulates playing scratch offs
    public void play()
    {
        // user input
        int type, quantity, input;
              
        // display game rules
        System.out.println("Let's play scratch off tickets!");
        System.out.println("Players can select from One Dollar, Five Dollar, and Ten Dollars tickets");
        System.out.println("Prizes are based on the ticket selected");
        
        // check cash     
        if (this.player.getCash() < Constants.SCRATCHOFF_ONE_DOLLAR)
        {
            System.out.println("You do not have enough cash to play scratchoffs.");
            this.play = false;
        }
        
        while (play)
        {
            // get type and quantity of scratch off
            System.out.printf("What type of scratch off would you like (%d = One Dollar, %d = Five Dollar, %d = Ten Dollar)?\n", Constants.SCRATCHOFF_ONE_DOLLAR, Constants.SCRATCHOFF_FIVE_DOLLAR, Constants.SCRATCHOFF_TEN_DOLLAR);
            type = this.scan.nextInt();
            System.out.println("How many scratch offs would you like?");
            quantity = this.scan.nextInt();
            System.out.println("Getting your scratch offs...");
            
            displayCash();
            
            for (int i = 0; i < quantity; i++)
            {
                switch (type)
                {
                    case Constants.SCRATCHOFF_ONE_DOLLAR:
                        this.player.setCash(this.player.getCash() - Constants.SCRATCHOFF_ONE_DOLLAR);
                        this.one = new OneDollar(player);
                        break;
                    case Constants.SCRATCHOFF_FIVE_DOLLAR:
                        this.player.setCash(this.player.getCash() - Constants.SCRATCHOFF_FIVE_DOLLAR);
                        this.five = new FiveDollar(player);
                        break;
                    case Constants.SCRATCHOFF_TEN_DOLLAR:
                        this.player.setCash(this.player.getCash() - Constants.SCRATCHOFF_TEN_DOLLAR);
                        this.ten = new TenDollar(player);
                        break;
                    default:
                        System.out.println("Invalid scratch off type.");
                        break;
                }
            }
            
            System.out.println("");
            displayCash();
            
            // check cash     
            if (this.player.getCash() >= Constants.SCRATCHOFF_ONE_DOLLAR)
            {
                System.out.println("Player, would you like to play again? (Yes = 1, No = 0)");
                input = this.scan.nextInt();
                
                this.play = (input == 1) ? true : false;                
            }
            else break;
        }
        
        displayCash();
    }
}
