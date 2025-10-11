import java.util.ArrayList;
import java.util.Random;

public class MidtermProject {
    public static void main(String[] args) {
        ArrayList<Object> Deck = new ArrayList<>();
        NewDeck(Deck);
        System.out.println(Deck);
    }
    public static void NewDeck(ArrayList<Object> Deck){
        Deck.clear();
        String Suit = "";
        String Face = "";
        for (int j = 1; j <= 4; j++){
            if (j == 1)
                Suit = "Diamonds";
            else if (j == 2)
                Suit = "Clubs";
            else if (j == 3)
                Suit = "Hearts";
            else if (j == 4)
                Suit = "Spades";
            for (int k = 1; k <= 3; k++){
                if (k == 1)
                    Face = "Jack";
                else if (k == 2)
                    Face = "Queen";
                else if (k == 3)
                    Face = "King";
                Deck.add(Face + " of " + Suit);
            }
            for (int i = 1; i <= 10; i++)
                if (i == 1)
                    Deck.add("Ace of " + Suit);
                else
                    Deck.add(i + " of " + Suit);
        }
    }
}

