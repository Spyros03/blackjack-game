package nikolaou.cards;

import java.util.List;

public interface Deck<T extends Card> {

    void shuffle();

    void cut();

    T drawCard();

    List<T> drawMany(int numberOfCards);

    void addDeck(Deck<T> otherDeck);

    List<T> getListOfCards();

    int getRemainingCards();
}
