import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class BlackJackGame extends CardGame<BlackJackPlayer>{

    public static void main(String[] args) {
        int numOfPlayers = BlackJackUI.numOfPlayers();
        List<BlackJackPlayer> players = new ArrayList<>();
        for (int i = 1; i <= numOfPlayers; i++){
            players.add(BlackJackUI.newPlayer(i));
        }
        BlackJackGame game  = new BlackJackGame(players);
        players.forEach(player -> player.changeDeck(game.getDeck()));
        do {
            round(game);
        } while (BlackJackUI.wishToContinue());
    }

    public static void round(BlackJackGame game){
        int moveNumber = 1;
        game.startBetting();
        game.startRound();
        while (!game.isOver()){
            BlackJackUI.printTable(game);
            game.checkTheDeck();
            game.nextTurn(moveNumber);
            moveNumber++;
        }
        game.dealer.dealerPlay();
        BlackJackUI.printTable(game);
        game.handleBets();
        game.resetHands();
    }

    private final BlackJackDealer dealer;
    private Deck<BlackJackPlayingCard> deck = PlayingCardsDeck.getBlackJackDeck(4);

    public void startBetting(){
        players.forEach(BlackJackUI::askBets);
    }

    public BlackJackGame(BlackJackPlayer... players) {
        super(players);
        dealer = new BlackJackDealer(deck);
    }

    public BlackJackGame(List<BlackJackPlayer> players) {
        super(players);
        dealer = new BlackJackDealer(deck);
    }

    public BlackJackGame() {
        super();
        dealer = new BlackJackDealer(deck);
    }

    public void startRound(){
        for (int i = 0; i < 2; i++){
            for (var player : players){
                player.draw();
            }
            dealer.draw();
        }
    }

    @Override
    public void nextTurn(int moveNumber) {
        for (var player : players){
            if (player.hasSecondHand() && player.secondHand.canDraw()){
                while(true){
                    try{
                        player.playMove(BlackJackUI.getMove(moveNumber), moveNumber);
                        break;
                    }catch (Exception e){
                        BlackJackUI.handleInputError(e);
                    }
                }
            }
            if (player.canDraw()){
                while(true){
                    try{
                        player.playMove(BlackJackUI.getMove(moveNumber), moveNumber);
                        break;
                    }catch (Exception e){
                        BlackJackUI.handleInputError(e);
                    }
                }
            }
        }
    }

    public void checkTheDeck(){
        if (deck.getRemainingCards() < 20){
            Deck<BlackJackPlayingCard> newDeck = PlayingCardsDeck.getBlackJackDeck(4);
            for (var player : players){
                if (player.hasSecondHand()){
                    player.secondHand.changeDeck(newDeck);
                }
                player.changeDeck(newDeck);
            }
            dealer.changeDeck(newDeck);
        }
    }

    public boolean isOver(){
        for (var player : players){
            if (player.hasSecondHand()){
                if (player.secondHand.canDraw()) {
                    return false;
                }
            }
            if (player.canDraw()){
                return false;
            }
        }
        return true;
    }

    public void handleBets(){
        int dealersHand = dealer.calculateHand();
        Function<BlackJackPlayer,Integer> betLogic = player -> {
            if (dealer.isBusted()){
                if (!player.isBusted()){
                    return 1;
                } else {
                    return -1;
                }
            }else if(dealer.hasBlackJack()){
                if (player.hasBlackJack()){
                    return 0;
                }
                return -1;
            }else {
                if (player.isBusted()){
                    return -1;
                }else if (player.calculateHand() < dealersHand) {
                    return -1;
                }else if (player.calculateHand() == dealersHand && player.hasBlackJack()){
                    return 1;
                }else if (player.calculateHand() == dealersHand){
                    return 0;
                }else{
                    return 1;
                }
            }
        };

        for (var player : players){
            if (player.hasSecondHand()){
                if (player.hasBlackJack()){
                    player.getBet().setMultiplier(2.5);
                }
                player.secondHand.getBet().processBet(betLogic);
            }
            player.getBet().processBet( betLogic);
        }
    }

    public void resetHands(){
        for (var player : players){
            if (player.hasSecondHand()){
                try {
                    player.secondHand.getPlayerBank().transfer(player.secondHand.getPlayerBank().getAmount(),
                            player.getPlayerBank());
                }catch (Exception e){}
                player.secondHand = null;
            }
            player.reset();
        }
        dealer.reset();
    }

    public BlackJackDealer getDealer() {
        return dealer;
    }

    public List<BlackJackPlayer> getPlayers(){
        return players;
    }

    public Deck<BlackJackPlayingCard> getDeck() {
        return deck;
    }
}
