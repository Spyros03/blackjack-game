import java.util.ArrayList;
import java.util.List;

public abstract class CardPlayer<T extends Card>{

    protected final String name;
    protected List<T> cards;

    public CardPlayer(String name, List<T> playerCards){
        this.name = name;
        cards = new ArrayList<>(playerCards);
    }

    public CardPlayer(String name, T playerCard){
        this(name,List.of(playerCard));
    }

    public CardPlayer(String name){
        this.name = name;
        cards = new ArrayList<>();
    }

    public List<T> getCards(){
        return cards;
    }

    public boolean isEmptyHanded(){
        return (cards.size() == 0);
    }

    public String getName(){
        return name;
    }

    static class InvalidMove extends Exception{
        public InvalidMove() {
        }

        public InvalidMove(String message) {
            super(message);
        }

        public InvalidMove(String message, Throwable cause) {
            super(message, cause);
        }

        public InvalidMove(Throwable cause) {
            super(cause);
        }
    }
}
