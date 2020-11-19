package userInterface;

import casino.Player;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author quikks1lver
 */
public class PlayerUi extends JPanel
{
    // member vars
    private Player player;
    private JLabel cashLabel;
    private JLabel cashBalance;

    // custom constructor
    public PlayerUi(Player player)
    {
        this.player = player;
        
        initComponents();
    }
    
    private void initComponents()
    {
        String name = JOptionPane.showInputDialog(null, "Enter the name of player");
        player.setName(name);
        
        String cash = JOptionPane.showInputDialog(null, "Enter cash to play");
        player.setCash(Integer.parseInt(cash));
        
        this.setSize(new Dimension(200, 200));
        
        this.setBorder(BorderFactory.createTitledBorder("Player Info"));
        
        this.cashLabel = new JLabel("Cash Balance: $");
        
        this.cashBalance = new JLabel(Integer.toString(player.getCash()));
        
        this.add(cashLabel);
        this.add(cashBalance);
    }
    
    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the cashLabel
     */
    public JLabel getCashLabel() {
        return cashLabel;
    }

    /**
     * @param cashLabel the cashLabel to set
     */
    public void setCashLabel(JLabel cashLabel) {
        this.cashLabel = cashLabel;
    }

    /**
     * @return the cashBalance
     */
    public JLabel getCashBalance() {
        return cashBalance;
    }

    /**
     * @param cashBalance the cashBalance to set
     */
    public void setCashBalance(JLabel cashBalance) {
        this.cashBalance = cashBalance;
    }
    
    
}
