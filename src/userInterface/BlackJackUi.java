package userInterface;

import casino.Player;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author quikks1lver
 */
public class BlackJackUi extends JPanel
{
    // member vars
    private CasinoUi casinoUi;
    private Player player;
    private JLabel data;
    
    // custom constructor
    public BlackJackUi(Player player, CasinoUi casinoUi)
    {
        this.player = player;
        this.casinoUi = casinoUi;
        
        initComponents();
    }
    
    private void initComponents()
    {
        this.data = new JLabel("Welcome to Knights Casino Black Jack");
        this.add(data);
    }
}
