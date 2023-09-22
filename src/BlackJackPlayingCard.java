public class BlackJackPlayingCard extends PlayingCard {

    public BlackJackPlayingCard(Suit suit, String face) throws Exception {
        super(suit, face, blackJackFaceToRank(face));
    }

    public static BlackJackPlayingCard getBlackJackCard(Suit suit, String face) throws Exception{
        return new BlackJackPlayingCard(suit, face);
    }

    private static int blackJackFaceToRank(String face) throws Exception{
        try{
            return Integer.parseInt(face);
        } catch (NumberFormatException e ){
            return switch (face){
                case "J","Q","K" -> 10;
                case "A" -> 11;
                default -> throw new Exception("Invalid face.");
            };
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlackJackPlayingCard card) {
            return (this.getRank() == card.getRank());
        } else {
            return false;
        }
    }
}
