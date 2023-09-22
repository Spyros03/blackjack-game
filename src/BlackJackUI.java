import java.util.Scanner;

public class BlackJackUI {

    private static final Scanner in = new Scanner(System.in);

    public static void handleInputError(Exception e){
        System.out.println(e.toString());
    }

    public static void printTable(BlackJackGame game){
        System.out.println("                       Black Jack Table.                   ");
        System.out.println("___________________________________________________________");
        System.out.println(game.getDealer());
        for (var player : game.getPlayers()){
            System.out.println(player);
            if (player.hasSecondHand()){
                System.out.println(player.secondHand);
            }
        }
        System.out.println("___________________________________________________________");
    }

    public static void printPlayers(BlackJackGame game, boolean showWinners){
        System.out.println("                          Players                          ");
        System.out.println("___________________________________________________________");
        for (var player : game.getPlayers()){
            String tellWinner = showWinners ? player.getBet().isWon() ? "has Won" : "" : "";
            System.out.printf("%s's account balance: %.2f %s%n", player.getName(), player.getPlayerBank().getAmount(),
                    tellWinner);
        }
        System.out.println("___________________________________________________________");
    }

    public static int numOfPlayers(){
        while (true){
            System.out.print("How many players will sit at the table:");
            try {
                String numOfPlayersString = in.nextLine();
                System.out.println();
                return Integer.parseInt(numOfPlayersString);
            }
            catch (Exception e){
                System.out.println("Invalid input please try again.");
            }
        }
    }

    public static BlackJackPlayer newPlayer(int playerNumber) {
        System.out.printf("Player %d enter your name:", playerNumber);
        String playerName = in.nextLine();
        System.out.println();
        while (true) {
            System.out.print("Choose buy in amount:");
            try {
                String amountString = in.nextLine();
                System.out.println();
                double initialAmount = Double.parseDouble(amountString);
                return new BlackJackPlayer(playerName, initialAmount);
            } catch (Exception e) {
                System.out.println("Invalid input please try again.");
            }
        }
    }

    public static void askBets(BlackJackPlayer player){
        System.out.printf("%s place your bet.%n", player.getName());
        while (true) {
            try {
                System.out.println("----Bet----");
                player.placeBet(Double.parseDouble(in.nextLine().trim()));
                break;
            } catch (Exception e) {
                System.out.println(e + "Please try again.");
            }
        }
    }

    public static BlackJackPlayer.Move getMove(int moveNumber, String playerName){
        if (moveNumber == 1){
            System.out.printf("%s play your move (Hit, Stand, Split, Double).%n", playerName);
        }else{
            System.out.printf("%s Play your move (Hit, Stand).%n", playerName);
        }
        while (true) {
            System.out.println("----Move----");
            switch (in.nextLine().trim().toUpperCase()) {
                case "HIT":
                    return BlackJackPlayer.Move.HIT;
                case "STAND":
                    return BlackJackPlayer.Move.STAND;
                case "SPLIT":
                    return BlackJackPlayer.Move.SPLIT;
                case "DOUBLE":
                    return BlackJackPlayer.Move.DOUBLE;
                default:
                    System.out.println("Invalid move try again.");
            }
        }
    }

    public static boolean wishToContinue(){
        System.out.println("Do you wish to continue?");
        while (true){
            System.out.println("----Y/N----");
            switch (in.nextLine().toUpperCase().charAt(0)){
                case 'Y':
                    return true;

                case 'N':
                    return false;
            }
            System.out.println("Invalid answer.");
        }
    }
}
