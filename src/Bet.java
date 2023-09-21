import java.util.function.UnaryOperator;

public class Bet {

    private final PlayerBank betOwner;
    private final double betAmount;
    private final double multiplier;
    private boolean isWon;

    public Bet(double betAmount, double multiplier, PlayerBank betOwner) throws PlayerBank.InvalidAccountBalanceException {
        betOwner.withdraw(betAmount);
        this.betAmount = betAmount;
        this.multiplier = multiplier;
        this.betOwner = betOwner;
        isWon = false;
    }

    public void processBet(UnaryOperator<Integer> betLogic, int handValue){
        int betResult = betLogic.apply(handValue);
        switch (betResult){
            case 1 -> {betOwner.deposit(multiplier * betAmount);
                isWon = true;}
            case 0 -> betOwner.deposit((betAmount));
        }
    }
}
