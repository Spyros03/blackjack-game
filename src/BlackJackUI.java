import java.util.Scanner;

public class BlackJackUI {

    private static final Scanner in = new Scanner(System.in);

    public static void handleInputError(Exception e){
        System.out.println(e.toString());
    }

    public static void printTable(BlackJackGame game){
        System.out.println("                    Black Jack Board.                 ");
        System.out.println("______________________________________________________");
        System.out.println(game.getDealer());
        for (var player : game.getPlayers()){
            System.out.println(player);
        }
        System.out.println("-------------------------------------------------------");
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
        System.out.printf("Player %d enter your name: ", playerNumber);
        String playerName = in.nextLine();
        System.out.println();
        while (true) {
            System.out.print("Choose initial bet Amount:");
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

    public static BlackJackPlayer.Move getMove(int moveNumber){
        if (moveNumber == 1){
            System.out.println("Play your move (Hit, Stand, Split, Double).");
        }else{
            System.out.println("Play your move (Hit, Stand).");
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
