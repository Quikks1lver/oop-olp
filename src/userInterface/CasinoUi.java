package userInterface;

import casino.Casino;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author quikks1lver
 */
public class CasinoUi implements ActionListener
{
    // member variables
    private JFrame frame;
    private Casino casino;
    private PlayerUi playerUi;
    private BlackJackUi blackJackUi;
    private SlotsUi slotsUi;
    private ScratchOffsUi scratchOffsUi;
    private JPanel casinoPanel;
    private JPanel buttonPanel;
    private JPanel gamePanel;
    private JPanel blackJackPanel;
    private JPanel scratchOffPanel;
    private JPanel slotsPanel;
    private JButton slots;
    private JButton blackJack;
    private JButton scratchers;
    private final String SLOTS = "Slots";
    private final String BLACK_JACK = "Black Jack";
    private final String SCRATCH_OFFS = "Scratch Offs";
    private CardLayout cardLayout;
    
    // custom constructor
    public CasinoUi(Casino casino)
    {
        this.casino = casino;
        
        initObjects();
        initComponents();
    }
    
    // instantiates key member variables
    private void initObjects()
    {
        this.playerUi = new PlayerUi(this.casino.getPlayer());
        this.blackJackUi = new BlackJackUi(this.casino.getPlayer(), this);
        this.slotsUi = new SlotsUi(this.casino.getPlayer(), this);
        this.scratchOffsUi = new ScratchOffsUi(this.casino.getPlayer(), this);
        this.cardLayout = new CardLayout();
    }
    
    // starts up UI
    private void initComponents()
    {
        frame = new JFrame("Knights Casino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));
        
        this.buttonPanel = new JPanel();
        
        slots = new JButton(SLOTS);
        slots.addActionListener(this);
        blackJack = new JButton(BLACK_JACK);
        blackJack.addActionListener(this);
        scratchers = new JButton(SCRATCH_OFFS);
        scratchers.addActionListener(this);
        
//        buttonPanel.add(blackJack, BLACK_JACK);
//        buttonPanel.add(slots, SLOTS);
//        buttonPanel.add(scratchers, SCRATCH_OFFS);
        
        this.gamePanel = new JPanel();
        this.gamePanel.setLayout(cardLayout);
        this.gamePanel.setSize(new Dimension(300, 300));
        Border gpBorder = BorderFactory.createTitledBorder("Games");
        this.gamePanel.setBorder(gpBorder);
        gamePanel.add(this.blackJackUi, BLACK_JACK);
        gamePanel.add(this.slotsUi, SLOTS);
        gamePanel.add(this.scratchOffsUi, SCRATCH_OFFS);
        
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(playerUi, BorderLayout.WEST);
        frame.add(gamePanel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        cardLayout.show(gamePanel, (String) e.getActionCommand());
        frame.revalidate();
        frame.repaint();
    }
    
    public void updatePlayerUi()
    {
        playerUi.getCashBalance().setText(Integer.toString(playerUi.getPlayer().getCash()));
    }
}
