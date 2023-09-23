package nikolaou.cards;

import nikolaou.cards.blackjack.BlackJackPlayer;

import java.util.function.Function;


public class Bet {

    private final BlackJackPlayer betOwner;
    private final double betAmount;
    private double multiplier;
    private boolean isWon;

    public Bet(double betAmount, double multiplier, BlackJackPlayer betOwner)
            throws PlayerBank.InvalidAccountBalanceException {
        betOwner.getPlayerBank().withdraw(betAmount);
        this.betAmount = betAmount;
        this.multiplier = multiplier;
        this.betOwner = betOwner;
        isWon = false;
    }

    public void processBet(Function<BlackJackPlayer,Integer> betLogic){
        int betResult = betLogic.apply(betOwner);
        switch (betResult){
            case 1 -> {betOwner.getPlayerBank().deposit(multiplier * betAmount);
                isWon = true;
            }
            case 0 -> betOwner.getPlayerBank().deposit((betAmount));
        }
    }

    public void setMultiplier(double multiplier){
        this.multiplier = multiplier;
    }

    public double getBetAmount(){
        return betAmount;
    }

    public boolean isWon(){
        return isWon;
    }
}
