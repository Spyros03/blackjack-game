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

    public abstract void playMove(String move);

    public List<T> getCards(){
        return cards;
    }

    public boolean isEmptyHanded(){
        return (cards.size() == 0);
    }
}
