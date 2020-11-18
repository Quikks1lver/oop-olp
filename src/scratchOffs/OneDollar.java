package scratchOffs;

import casino.Player;
import constants.Constants;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author quikks1lver
 */
public class OneDollar
{
    // member fields
    private char winningBonus;
    private int winningNumber;
    private ArrayList<Integer> playerNumbers;
    private ArrayList<Double> prizes;
    private ArrayList<Character> bonus;
    private Player player;
    private Random rand;
    
    // constants
    final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    final int BASE = 20;
    final int NUMS = 5;
    final int SYMS = 2;
    final double BONUS = 10.00;
    
    // custom constructor
    public OneDollar(Player player)
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
        this.winningNumber = randomNumber();
        this.winningBonus = randomSymbol();
        
        this.playerNumbers = new ArrayList<Integer>();
        this.prizes = new ArrayList<Double>();
        this.bonus = new ArrayList<Character>();
        
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
        
        for (int i = 0; i < this.playerNumbers.size(); i++)
        {
            if (this.playerNumbers.get(i).intValue() == this.winningNumber)
                cash += this.prizes.get(i);
        }
        
        for (int i = 0; i < this.bonus.size(); i++)
        {
            if (this.bonus.get(i).charValue() == this.winningBonus)
                cash += this.BONUS;
        }
        
        System.out.println("Bonus symbol is " + this.winningBonus);
        System.out.println("Bonus is worth $" + BONUS);
        System.out.println("Your One Dollar Scratch Off won you $" + cash);
        
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
        System.out.printf("| WINNING NUMBER     %-22d|\n", this.winningNumber);
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
        }
        
        printPipeLine();
        System.out.printf("| SYMBOLS                                  |\n");
        printPipeLine();
        System.out.printf("|\t%c\t%c                          |\n", this.bonus.get(0), this.bonus.get(1));

        printStartEndLine();
    }
}
