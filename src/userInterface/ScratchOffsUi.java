package userInterface;

import casino.Player;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author quikks1lver
 */
public class ScratchOffsUi extends JPanel
{
    // member vars
    private CasinoUi casinoUi;
    private Player player;
    private JLabel data;
    
    // custom constructor
    public ScratchOffsUi(Player player, CasinoUi casinoUi)
    {
        this.player = player;
        this.casinoUi = casinoUi;
        
        initComponents();
    }
    
    private void initComponents()
    {
        data = new JLabel("Welcome to Knights Casino Scratch Offs");
        
        this.add(data);
    }
}
