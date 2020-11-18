package blackjack;

import constants.Constants.Color;
import constants.Constants.Face;
import constants.Constants.Suit;

/**
 * @author quikks1lver
 */
public class Card
{
    // member variables
    private Color color;
    private Face face;
    private Suit suit;
    
    private int value;

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the face
     */
    public Face getFace() {
        return face;
    }

    /**
     * @param face the face to set
     */
    public void setFace(Face face) {
        this.face = face;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    // Generates hash codes for the cards
    public int hashCode()
    {
        int hashcode = 0;
        return hashcode;
    }
    
    // Determines whether parameter is equal to the instantiated card object
    public boolean equals(Object obj)
    {
        if (obj instanceof Card)
        {
            Card card = (Card) obj;
            
            return (card.face.equals(this.face) && card.color.equals(this.color) && card.suit.equals(this.suit));
        }
        else
            return false;
    }

    /**
     * @return the value
     */
    public int getValue() {
        value = 0;
        if (face == Face.TEN || face == Face.JACK || face == Face.QUEEN || face == Face.KING) value = 10;
        else if (face == Face.ACE) value = 11;
        else if (face == Face.TWO) value = 2;
        else if (face == Face.THREE) value = 3;
        else if (face == Face.FOUR) value = 4;
        else if (face == Face.FIVE) value = 5;
        else if (face == Face.SIX) value = 6;
        else if (face == Face.SEVEN) value = 7;
        else if (face == Face.EIGHT) value = 8;
        else value = 9;
        
        return value;
    }
    
    // String output for Card
    public String toString() {
        String card = face + " " + suit;
        return card;
    }
}
