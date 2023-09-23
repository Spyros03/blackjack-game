package nikolaou.cards.blackjack;

import nikolaou.cards.Deck;
import nikolaou.cards.Hand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlackJackDealer implements Hand<BlackJackPlayingCard> {

    private List<BlackJackPlayingCard> dealerHand;
    private Deck<BlackJackPlayingCard> cardDeck;
    private boolean isBusted = false;
    private boolean hasBlackJack = false;
    private boolean showCards = false;

    public BlackJackDealer(Deck<BlackJackPlayingCard> cardDeck){
        dealerHand = new LinkedList<>();
        this.cardDeck = cardDeck;
    }

    public int dealerPlay(){
        while (calculateHand() < 17){
            dealerHand.add(cardDeck.drawCard());
        }
        if (calculateHand() > 21){
            isBusted = true;
        }
        showCards = true;
        return calculateHand();
    }

    @Override
    public List<BlackJackPlayingCard> getHand() {
        return dealerHand;
    }

    @Override
    public int calculateHand() {
        boolean hasAce = false;
        int handValue = 0;
        for (var card : dealerHand){
            if (card.getRank() == 11){}
                hasAce = true;
            handValue += card.getRank();
        }
        if (handValue > 21 && hasAce){
            handValue -= 10;
        }
        return handValue;
    }

    public void draw(){
        dealerHand.add(cardDeck.drawCard());
    }

    public void reset(){
        isBusted = false;
        hasBlackJack = false;
        dealerHand = new ArrayList<>();
        showCards = false;
    }

    public void changeDeck(Deck<BlackJackPlayingCard> deck){
        this.cardDeck = deck;
    }

    public boolean isBusted(){
        return isBusted;
    }

    public boolean checkForBlackJack(){
        hasBlackJack = dealerHand.get(0).getRank() + dealerHand.get(1).getRank() == 21;
        return hasBlackJack;
    }

    public boolean hasBlackJack(){
        return hasBlackJack;
    }

    @Override
    public String toString() {
        if(showCards) {
            StringBuilder Cards = new StringBuilder();
            for (var card : dealerHand) {
                Cards.append(card).append(" ");
            }
            return "Dealer's hand: " + Cards + ", Value: %d".formatted(calculateHand());
        }else {
            return "Dealer's hand: " + "?????? " + dealerHand.get(1) + ", Value: %d".formatted(
                    dealerHand.get(1).getRank());
        }
    }
}
