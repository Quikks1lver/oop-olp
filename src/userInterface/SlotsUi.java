/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import casino.Player;
import constants.Constants;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author quikks1lver
 */
public class SlotsUi extends JPanel
{
    // member vars
    private CasinoUi casinoUi;
    private Player player;
    private JLabel data;
    private JLabel bet;
    private JButton spin;
    private JPanel headerPanel;
    private JPanel slotsPanel;
    private ArrayList<ImageIcon> images;
    private Random rand;
    private ArrayList<Integer> spinNums;
    
    // i added some extra functionality -- if you don't have enough money,
    // you cannot go into the negatives. These constants helps with this.
    private static final int SPIN_SUCCESS = 1;
    private static final int SPIN_FAILURE = 0;
    
    // custom constructor
    public SlotsUi(Player player, CasinoUi casinoUi)
    {
        this.player = player;
        
        this.casinoUi = casinoUi;
        
        this.rand = new Random();
        
        initImages();
        
        initComponents();
    }
    
    private void initComponents()
    {
        this.data = new JLabel("Welcome to Knights Casino Slots");
        this.bet = new JLabel("The bet is $ " + Constants.BET);
        
        this.spin = new JButton("Spin");
        spin.addActionListener(new SpinListener());
        
        this.headerPanel = new JPanel();
        this.headerPanel.setLayout(new GridLayout(3, 1));
        this.headerPanel.add(data);
        this.headerPanel.add(bet);
        this.headerPanel.add(spin);
        
        this.slotsPanel = new JPanel();
        this.slotsPanel.setLayout(new GridLayout(1, 3));
        this.slotsPanel.setBorder(BorderFactory.createTitledBorder("Your spin"));
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.add(this.headerPanel);
        this.add(this.slotsPanel);
    }
    
    /**
     * Helper method: joins the relative path to the filename for ease of use
     * @param fileName 
     */
    private String createFilePath(String fileName)
    {
        String RELATIVE_PATH = "../images/slots/";
        return RELATIVE_PATH + fileName;
    }
    
    private void initImages()
    {
        this.images = new ArrayList<>();
        
        images.add(createImageIcon(createFilePath("Banana.png"), "Banana"));
        images.add(createImageIcon(createFilePath("Bar.png"), "Bar"));
        images.add(createImageIcon(createFilePath("Bell.png"), "Bell"));
        images.add(createImageIcon(createFilePath("Cherry.png"), "Cherry"));
        images.add(createImageIcon(createFilePath("Chip-100.png"), "Chip-100"));
        images.add(createImageIcon(createFilePath("Chip-25.png"), "Chip-25"));
        images.add(createImageIcon(createFilePath("Chip-5.png"), "Chip-5"));
        images.add(createImageIcon(createFilePath("Chip-50.png"), "Chip-50"));
        images.add(createImageIcon(createFilePath("Clover.png"), "Clover"));
        images.add(createImageIcon(createFilePath("Coin.png"), "Coin"));
        images.add(createImageIcon(createFilePath("Diamond.png"), "Diamond"));
        images.add(createImageIcon(createFilePath("Gold.png"), "Gold"));
        images.add(createImageIcon(createFilePath("Money.png"), "Money"));
        images.add(createImageIcon(createFilePath("Plum.png"), "Plum"));
        images.add(createImageIcon(createFilePath("Seven.png"), "Seven"));
        images.add(createImageIcon(createFilePath("Trollface.png"), "Trollface"));
        images.add(createImageIcon(createFilePath("Watermelon.png"), "Watermelon"));
    }
    
    private ImageIcon createImageIcon(String path, String description)
    {
        java.net.URL url = getClass().getResource(path);
        
        ImageIcon imageIcon;
        
        if (url != null)
        {
            imageIcon = new ImageIcon(url, description);
            imageIcon.setDescription(description);
            imageIcon = imageResize(imageIcon);
            return imageIcon;
        }
        else
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private ImageIcon imageResize(ImageIcon icon)
    {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 75, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }
    
    private class SpinListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (spin() == SPIN_SUCCESS) results();
        }
    }
    
    // Spins the slots. If successful, returns a success flag, otherwise fail flag
    private int spin()
    {
        if (player.getCash() < Constants.BET)
        {
            JOptionPane.showMessageDialog(null, "Not enough cash, sorry :/");
            return SPIN_FAILURE;
        }
        
        int num;
        
        player.setCash(player.getCash() - Constants.BET);
        casinoUi.updatePlayerUi();
        
        this.spinNums = new ArrayList<>();
        this.slotsPanel.removeAll();
        
        JLabel one = new JLabel();
        num = rand.nextInt(images.size());
        ImageIcon imageOne = images.get(num);
        one.setIcon(imageOne);
        spinNums.add(num);
        
        JLabel two = new JLabel();
        num = rand.nextInt(images.size());
        ImageIcon imageTwo = images.get(num);
        two.setIcon(imageTwo);
        spinNums.add(num);
        
        JLabel three = new JLabel();
        num = rand.nextInt(images.size());
        ImageIcon imageThree = images.get(num);
        three.setIcon(imageThree);
        spinNums.add(num);
        
        slotsPanel.add(one);
        slotsPanel.add(two);
        slotsPanel.add(three);
        
        slotsPanel.revalidate();
        slotsPanel.repaint();
        
        return SPIN_SUCCESS;
    }
    
    private void results()
    {
        // all match
        if (spinNums.get(0) == spinNums.get(1) && spinNums.get(0) == spinNums.get(2))
        {
            JOptionPane.showMessageDialog(null, "Three symbols matched, you won $50!");
            player.setCash(player.getCash() + Constants.TRIPLE_PAYOUT);
        }
        
        // two match
        else if (spinNums.get(0) == spinNums.get(1) ||
                 spinNums.get(0) == spinNums.get(2) ||
                 spinNums.get(1) == spinNums.get(2))
        {
            JOptionPane.showMessageDialog(null, "Two symbols matched, you won $5!");
            player.setCash(player.getCash() + Constants.PAIR_PAYOUT);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No symbols matched ;/");
        }
        
        casinoUi.updatePlayerUi();
    }
}
