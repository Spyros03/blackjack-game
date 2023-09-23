package nikolaou.cards.blackjack;

import nikolaou.cards.*;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayer extends CardPlayer<BlackJackPlayingCard> implements Hand<BlackJackPlayingCard> {

    private final PlayerBank playerBank;
    private boolean canDraw = true;
    private boolean isBusted = false;
    private boolean hasBlackJack = false;
    private Deck<BlackJackPlayingCard> deck;
    public BlackJackPlayer secondHand = null;
    private Bet activeBet = null;

    public BlackJackPlayer(String name, List<BlackJackPlayingCard> playerCards, double initialAmount,
                           Deck<BlackJackPlayingCard> deck) throws PlayerBank.InvalidAccountBalanceException{
        super(name, playerCards);
        playerBank = new PlayerBank(initialAmount);
        this.deck = deck;
    }

    public BlackJackPlayer(String name, BlackJackPlayingCard playerCard, double initialAmount)
    throws PlayerBank.InvalidAccountBalanceException{
        super(name, playerCard);
        playerBank = new PlayerBank(initialAmount);
    }

    public BlackJackPlayer(String name, double initialAmount)
    throws PlayerBank.InvalidAccountBalanceException{
        super(name);
        playerBank = new PlayerBank(initialAmount);
    }

    public void playMove(Move move, int moveNumber) throws InvalidMove, PlayerBank.InvalidAccountBalanceException {
        switch (move) {

            case HIT:
                if (canDraw) {
                    draw();
                    if (calculateHand() > 21) {
                        canDraw = false;
                        isBusted = true;
                    }
                } else {
                    throw new InvalidMove("%s cannot hit at the moment.".formatted(name));
                }
                break;

            case STAND:
                canDraw = false;
                break;

            case SPLIT:
                if (moveNumber == 1 && cards.get(0).equals(cards.get(1))) {
                    secondHand = new BlackJackPlayer(name + "second hand.", cards.get(1), 0);
                    playerBank.transfer(activeBet.getBetAmount(), secondHand.getPlayerBank());
                    secondHand.placeBet(activeBet.getBetAmount());
                    cards.remove(1);
                    draw();
                    secondHand.draw();

                }else {
                    throw new InvalidMove("Cannot double at this stage.");
                }
                break;
            case DOUBLE:
                if (moveNumber == 1){
                    canDraw = false;
                    cards.add(deck.drawCard());
                    activeBet.setMultiplier(4.0);
                    if (calculateHand() > 21) {
                        isBusted = true;
                    }
                }else {
                    throw new InvalidMove("Cannot double at this point.");
                }
        }
    }

    public Bet placeBet(double betAmount) throws PlayerBank.InvalidAccountBalanceException {
        activeBet = new Bet(betAmount, 2.0, this);
        return activeBet;
    }

    @Override
    public List<BlackJackPlayingCard> getHand() {
        return cards;
    }

    @Override
    public int calculateHand() {
        boolean hasAce = false;
        int handValue = 0;
        for (var card : cards){
            hasAce = card.getRank() == 11;
            handValue += card.getRank();
        }
        if (handValue > 21 && hasAce){
            handValue -= 10;
        }
        return handValue;
    }

    public void draw(){
        cards.add(deck.drawCard());
    }

    public void reset(){
        canDraw = true;
        isBusted = false;
        hasBlackJack = false;
        activeBet = null;
        secondHand = null;
        cards = new ArrayList<>();
    }

    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    public boolean canDraw() {
        return canDraw;
    }

    public boolean isBusted() {
        return isBusted;
    }

    public boolean hasSecondHand() {
        return secondHand != null;
    }

    public void changeDeck(Deck<BlackJackPlayingCard> deck){
        this.deck = deck;
    }

    public Bet getBet(){
        return activeBet;
    }

    public boolean checkForBlackJack(){
        hasBlackJack = cards.get(0).getRank() + cards.get(1).getRank() == 21;
        canDraw = !hasBlackJack;
        return hasBlackJack;
    }

    public boolean hasBlackJack(){
        return hasBlackJack;
    }

    @Override
    public String toString() {
        StringBuilder Cards = new StringBuilder();
        for (var card : cards){
            Cards.append(card).append(" ");
        }
        return "%s's hand: ".formatted(name) + Cards + ", Value: %d".formatted(calculateHand());
    }

    enum Move {
        HIT,
        STAND,
        SPLIT,
        DOUBLE,
    }
}
