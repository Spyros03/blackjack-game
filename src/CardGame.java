import java.util.ArrayList;
import java.util.List;

public abstract class CardGame<T extends CardPlayer<? extends Card>>{

    protected List<T> players;

    public CardGame(T...players){
        this.players = new ArrayList(List.of(players));
    }

    public CardGame(List<T> players){
        this.players = new ArrayList<>(players);
    }

    public CardGame(){
        this.players = new ArrayList<>();
    }

    public void addPlayer(T newPlayer){
        players.add(newPlayer);
    }

    public abstract void nextTurn();
}
