import java.util.List;

public class BlackJackGame extends CardGame<BlackJackPlayer>{



    public BlackJackGame(BlackJackPlayer... players) {
        super(players);
    }

    public BlackJackGame(List<BlackJackPlayer> players) {
        super(players);
    }

    public BlackJackGame() {
        super();
    }

    @Override
    public void nextTurn() {
        for (var player : players){

        }
    }
}
