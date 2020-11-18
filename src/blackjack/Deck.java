package blackjack;

import constants.Constants;
import constants.Constants.Face;
import constants.Constants.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author quikks1lver
 */
public class Deck
{
    // member variables
    private Set<Card> deck;

    // Custom constructor
    public Deck()
    {
//        System.out.println("****************************");
//        System.out.println("Generating the deck of cards");
//        System.out.println("****************************");
        this.generateDeck();
        
//        System.out.println("****************************");
//        System.out.println("Displaying the deck of cards");
//        System.out.println("****************************");
//        this.displayDeck();
        
//        System.out.println("***************************");
//        System.out.println("Shuffling the deck of cards");
//        System.out.println("***************************");
        this.shuffleDeck();
        
//        System.out.println("*************************************");
//        System.out.println("Displaying the shuffled deck of cards");
//        System.out.println("*************************************");
//        this.displayDeck();
    }
    
    // Generates a deck of cards
    public void generateDeck()
    {
        deck = new HashSet<Card>(Constants.NUM_CARDS);
        
        for (Face face : Face.values())
        {
            for (Suit suit : Suit.values())
            {
                Card card = new Card();
                
                card.setFace(face);
                card.setSuit(suit);
                if (suit.equals(Suit.CLUBS) || suit.equals(Suit.SPADES)) card.setColor(Constants.Color.BLACK);
                else card.setColor(Constants.Color.RED);
                
                if (!this.deck.contains(card))
                    this.deck.add(card);
            }
        }
    }
    
    // Prints out deck
    public void displayDeck()
    {
        System.out.println("Deck size: " + this.deck.size() + " cards");
        System.out.println("Deck includes:");
        
        for (Card card : this.deck)
        {
            System.out.println("Card: " + card.getFace() + " of " + card.getSuit() + " is color " + card.getColor());
        }
    }
    
    // Shuffles deck around
    public void shuffleDeck()
    {
        ArrayList<Card> newDeck = new ArrayList<Card>(this.deck);
        Collections.shuffle(newDeck);
        this.deck = new HashSet<Card>(newDeck);
    }
    
    /**
     * @return the deck
     */
    public Set<Card> getDeck() {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Set<Card> deck) {
        this.deck = deck;
    }
}
