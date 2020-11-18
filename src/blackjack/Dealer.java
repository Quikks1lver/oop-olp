/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import constants.Constants;
import java.util.Iterator;

/**
 *
 * @author quikks1lver
 */
public class Dealer implements IDealer {

    // member variables
    private ArrayList<Card> hand;
    private int score;
    
    @Override
    public int hitOrStand() {
        if (this.getScore() <= Constants.DEALER_HIT) return Constants.HIT;
        return Constants.STAND;
    }

    @Override
    public Card deal(Deck deck) {
        Card card = new Card();
        
        Iterator<Card> it = deck.getDeck().iterator();
        
        // Deal out cards and remove from deck using iterator of hash set
        if (it.hasNext())
            card = it.next();
        
        deck.getDeck().remove(card);

        return card;
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * @return the score
     */
    public int getScore() {
        score = 0;
        
        for (Card c : hand) {
            score += c.getValue();
        }
        
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
    
}
