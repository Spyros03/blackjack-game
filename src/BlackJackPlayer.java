import java.util.List;

public class BlackJackPlayer extends CardPlayer<BlackJackPlayingCard> implements Hand<BlackJackPlayingCard>{

    private PlayerBank playerBank;
    private boolean canDraw = true;

    public BlackJackPlayer(String name, List<BlackJackPlayingCard> playerCards, double initialAmount)
    throws PlayerBank.InvalidAccountBalanceException{
        super(name, playerCards);
        playerBank = new PlayerBank(initialAmount);
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

    @Override
    public void playMove(String move) {
    }

    @Override
    public List<BlackJackPlayingCard> getHand() {
        return null;
    }

    @Override
    public int calculateHand() {
        return 0;
    }

    @Override
    public void printHand() {

    }
}
