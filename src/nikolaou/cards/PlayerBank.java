package nikolaou.cards;

public class PlayerBank {

    private double amount;

    public PlayerBank(double amount) throws InvalidAccountBalanceException{
        if (amount < 0){
            throw new InvalidAccountBalanceException("Invalid amount");
        }
        this.amount = amount;
    }

    public PlayerBank(){
        this.amount = 1000.0;
    }

    public boolean deposit(double depositAmount){
        amount += depositAmount;
        return true;
    }

    public void withdraw(double withdrawAmount) throws InvalidAccountBalanceException {
        if (amount < withdrawAmount) {
            throw new InvalidAccountBalanceException("Invalid withdraw amount.");
        }
        else{
            amount -= withdrawAmount;
        }
    }

    public void transfer(double transferAmount, PlayerBank otherPlayerBank) throws InvalidAccountBalanceException{
        this.withdraw(transferAmount);
        otherPlayerBank.deposit(transferAmount);
    }

    public double getAmount(){
        return amount;
    }

    public static class InvalidAccountBalanceException extends Exception{
        public InvalidAccountBalanceException() {
        }

        public InvalidAccountBalanceException(String message) {
            super(message);
        }

        public InvalidAccountBalanceException(String message, Throwable cause) {
            super(message, cause);
        }

        public InvalidAccountBalanceException(Throwable cause) {
            super(cause);
        }
    }
}
