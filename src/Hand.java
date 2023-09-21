import java.util.List;

public interface Hand<T extends PlayingCard> {

    List<T> getHand();

    int calculateHand();

    void printHand();
}
