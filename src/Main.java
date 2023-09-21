import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deck<BlackJackPlayingCard> blackJackDeck = PlayingCardsDeck.getBlackJackDeck(4);
//        PlayingCardsDeck.printDeck(blackJackDeck, 16);
        blackJackDeck.shuffle();
        PlayingCardsDeck.printDeck(blackJackDeck, 16);
    }
}