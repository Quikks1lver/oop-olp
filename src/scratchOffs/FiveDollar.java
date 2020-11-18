package scratchOffs;

import casino.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author quikks1lver
 */
public class FiveDollar
{
    // member fields
    private char winningBonus;
    private ArrayList<Integer> winningNumbers;
    private ArrayList<Integer> playerNumbers;
    private ArrayList<Double> prizes;
    private ArrayList<Character> bonus;
    private Player player;
    private Random rand;
    
    // constants
    final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    final int BASE = 40;
    final int NUMS = 10;
    final int SYMS = 2;
    final double BONUS = 20.00;
    
    // custom constructor
    public FiveDollar(Player player)
    {
        this.player = player;
        this.rand = new Random();
        createTicket();
        displayTicket();
        cashTicket();
    }
    
    // creates SO ticket
    private void createTicket()
    {
        this.winningBonus = randomSymbol();
        
        this.playerNumbers = new ArrayList<Integer>();
        this.prizes = new ArrayList<Double>();
        this.bonus = new ArrayList<Character>();
        this.winningNumbers = new ArrayList<Integer>();
        
        for (int i = 0; i < 2; i++)
        {
            this.winningNumbers.add(randomNumber());
        }
        
        for (int i = 0; i < NUMS; i++)
        {
            this.playerNumbers.add(randomNumber());
            this.prizes.add(randomPrize());
        }
        
        for (int i = 0; i < SYMS; i++)
        {
            this.bonus.add(randomSymbol());
        }
    }
    
    // cashes a ticket
    private void cashTicket()
    {
        int cash = 0;
        
        for (int i = 0; i < this.winningNumbers.size(); i++)
        {
            for (int j = 0; j < this.playerNumbers.size(); j++)
            {
                if (this.winningNumbers.get(i).intValue() == this.playerNumbers.get(j).intValue())
                {
                    cash += this.prizes.get(j);
//                    System.out.printf("~~~~~~~~~~~~~~~~ Got em. Yours: %d, winning: %d, won: %f\n", playerNumbers.get(j), winningNumbers.get(i), prizes.get(j));
                }
            }
        }
        
        for (int i = 0; i < this.bonus.size(); i++)
        {
            if (this.bonus.get(i).charValue() == this.winningBonus)
            {
                cash += this.BONUS;   
//                System.out.printf("~~~~~~~~~~~~~~~~ bonus. Yours: %c, winning: %c, won: %f\n", bonus.get(i).charValue(), winningBonus, BONUS);
            }
        }
        
        System.out.println("Bonus symbol is " + this.winningBonus);
        System.out.println("Bonus is worth $" + BONUS);
        System.out.println("Your Five Dollar Scratch Off won you $" + cash);
        
        this.player.setCash(this.player.getCash() + cash);
        System.out.println("CASH = $" + this.player.getCash());
    }
    
    // returns a rand number
    private int randomNumber()
    {
        int val = this.rand.nextInt(BASE) + 1;
        return val;
    }
    
    // returns a rand double
    private double randomPrize()
    {
        double val = (double)(this.rand.nextInt(BASE) + 1);
        return val;
    }
    
    // returns a random symbol
    private char randomSymbol()
    {
        int num;
        char symbol;
        
        num = this.rand.nextInt(SYMBOLS.length);
        
        switch (num)
        {
            case 0:
                symbol = SYMBOLS[0];
                break;
            case 1:
                symbol = SYMBOLS[1];
                break;
            case 2:
                symbol = SYMBOLS[2];
                break;
            case 3:
                symbol = SYMBOLS[3];
                break;
            case 4:
                symbol = SYMBOLS[4];
                break;
            case 5:
                symbol = SYMBOLS[5];
                break;
            default:
                // shouldn't happen
                symbol = SYMBOLS[0];
                break;
        }
        
        return symbol;
    }
    
    // prints a starting or ending line
    private void printStartEndLine()
    {
        System.out.println("+------------------------------------------+");
    }
    
    // prints a pipe line
    private void printPipeLine()
    {
        System.out.printf("|                                          |\n");
    }
    
    // displays a SO ticket
    private void displayTicket()
    {
        int count = 0;
        final int BREAK = 5;
        
        printStartEndLine();
        System.out.printf("| WINNING NUMBER     %8d %8d     |\n", this.winningNumbers.get(0), this.winningNumbers.get(1));
        printPipeLine();
        System.out.printf("| YOUR NUMBERS                             |\n|");
        
        for (int i = 0; i < this.playerNumbers.size(); i++)
        {
            count++;
            
            System.out.printf("%8d", this.playerNumbers.get(i));
            
            if ((count % BREAK) == 0)
            {
                System.out.printf("%2s|\n", " ");
            }
            if (i == (this.playerNumbers.size()/2) - 1) System.out.print("|");
        }
        
        printPipeLine();
        System.out.printf("| PRIZES                                   |\n");
        printPipeLine();
        
        count = 0;
        System.out.print("| ");
        for (int i = 0; i < this.prizes.size(); i++)
        {
            count++;
            
            System.out.printf("%8.2f", this.prizes.get(i));
            
            if ((count % BREAK) == 0)
            {
                System.out.printf("%s|\n", " ");
            }
            if (i == (this.prizes.size()/2) - 1) System.out.print("| ");
        }
        
        printPipeLine();
        System.out.printf("| SYMBOLS                                  |\n");
        printPipeLine();
        System.out.printf("|\t%c\t%c                          |\n", this.bonus.get(0), this.bonus.get(1));

        printStartEndLine();
    }
}
