import java.util.*;

public class PlayingCardsDeck<T extends PlayingCard> implements Deck<T>{

    List<T> deckOfCards;

    public PlayingCardsDeck(List<T> deckCards) {
        this.deckOfCards = new LinkedList<>(deckCards);
    }

    public PlayingCardsDeck(T...Cards){
        this(List.of(Cards));
    }

    public PlayingCardsDeck() {
        super();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    @Override
    public void cut() {
        Collections.rotate(deckOfCards, deckOfCards.size()/2);
    }

    @Override
    public T drawCard() {
        T Card = deckOfCards.get(0);
        deckOfCards.remove(0);
        return Card;
    }

    @Override
    public List<T> drawMany(int numberOfCards) {
        List<T> drawnCards = new ArrayList<>(numberOfCards);
        for (int i = 0; i < numberOfCards; i++){
            drawnCards.set(i, deckOfCards.get(0));
            deckOfCards.remove(0);
        }
        return drawnCards;
    }

    @Override
    public void addDeck(Deck<T> otherDeck){
        deckOfCards.addAll(otherDeck.getListOfCards());
    }

    public List<T> getListOfCards(){
        return deckOfCards;
    }

    public static Deck<PlayingCard> getStandardDeck(){
        List<PlayingCard> returnedCards = new LinkedList<>();
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()){
            for (int i = 2; i <= 10; i++){
                try{
                    returnedCards.add(PlayingCard.getPlayingCard(suit, String.valueOf(i)));
                }catch (Exception e){
                    return null;
                }
            }
            try{
                returnedCards.add(PlayingCard.getPlayingCard(suit, "J"));
                returnedCards.add(PlayingCard.getPlayingCard(suit, "Q"));
                returnedCards.add(PlayingCard.getPlayingCard(suit, "K"));
                returnedCards.add(PlayingCard.getPlayingCard(suit, "A"));
            }catch (Exception e){
                return null;
            }
        }
        return new PlayingCardsDeck<>(returnedCards);
    }

    public static Deck<BlackJackPlayingCard> getSingleBlackJackDeck(){
        List<BlackJackPlayingCard> returnedCards = new LinkedList<>();
        for (BlackJackPlayingCard.Suit suit : BlackJackPlayingCard.Suit.values()){
            for (int i = 2; i <= 10; i++){
                try{
                    returnedCards.add(BlackJackPlayingCard.getBlackJackCard(suit, String.valueOf(i)));
                }catch (Exception e){
                    return null;
                }
            }
            try{
                returnedCards.add(BlackJackPlayingCard.getBlackJackCard(suit, "J"));
                returnedCards.add(BlackJackPlayingCard.getBlackJackCard(suit, "Q"));
                returnedCards.add(BlackJackPlayingCard.getBlackJackCard(suit, "K"));
                returnedCards.add(BlackJackPlayingCard.getBlackJackCard(suit, "A"));
            }catch (Exception e){
                return null;
            }
        }
        return new PlayingCardsDeck<>(returnedCards);
    }

    public static Deck<BlackJackPlayingCard> getBlackJackDeck(int numOfDecks){
        Deck<BlackJackPlayingCard> blackJackDeck = PlayingCardsDeck.getSingleBlackJackDeck();
        for (int i = 0; i < numOfDecks - 1; i++){
            blackJackDeck.addDeck(getSingleBlackJackDeck());
        }
        blackJackDeck.shuffle();
        return blackJackDeck;
    }

    public int getRemainingCards(){
        return deckOfCards.size();
    }

    public static <U extends PlayingCard> void printDeck(Deck<U> deckToPrint, int rows){
        List<U> listOfCards = deckToPrint.getListOfCards();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < listOfCards.size()/rows; j++){
                System.out.print(listOfCards.get((i)*listOfCards.size()/rows + j));
            }
            System.out.println();
        }
        for (int i = 0; i < listOfCards.size()%rows; i++){
            System.out.print(listOfCards.get(rows * (listOfCards.size()/rows) + i));
        }
    }
}
