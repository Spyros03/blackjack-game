public class PlayingCard extends Card{

    final private Suit suit;
    final private String face;
    final private int rank;

    public PlayingCard(Suit suit, String face, int rank) {
        this.suit = suit;
        this.face = face;
        this.rank = rank;
    }

    public PlayingCard(Suit suit, String face) throws Exception{
        this(suit, face, standardFaceToRank(face));
    }

    public static PlayingCard getPlayingCard(Suit suit, String face) throws Exception{
        return new PlayingCard(suit, face);
    }

    private static int standardFaceToRank(String face) throws Exception{
        try{
            return Integer.parseInt(face) - 2;
        } catch (NumberFormatException e){
            return switch (face){
                case "J" -> 9;
                case "Q" -> 10;
                case "K" -> 11;
                case "A" -> 12;
                default -> throw new Exception("Invalid Value");
            };
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayingCard card){
            return face.equals(card.getFace()) && suit.equals(card.getSuit()) && rank == card.getRank();
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "%s%c(%d)".formatted(face, suit.getSuitIcon(), rank);
    }

    public Suit getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    public int getRank() {
        return rank;
    }

    enum Suit {
        SPADES,
        HEARTS,
        CLUB,
        DIAMONDS;

        private char getSuitIcon() {
            char[] suitChars = {9824, 9825, 9827, 9826};
            return suitChars[this.ordinal()];
        }
    }
}
