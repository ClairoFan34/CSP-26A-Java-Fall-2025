import java.util.ArrayList;
import java.util.Random;

public class MidtermProject {
    public static void main(String[] args) {
        ArrayList<Object> Deck = new ArrayList<>();
        Deck.add(0);

    }
    public static void NewDeck(ArrayList<Object> Deck){
        Deck.clear();
        String Suit = "";
        for (int j = 0; j < 4; j++){
            if (j == 0)
                Suit = "Diamonds";
            else if (j == 1)
                Suit = "Clubs";
            else if (j == 2)
                Suit = "Hearts";
            else if (j == 3)
                Suit = "Spades";
            for (int i = 0; i < 9; i++)
                Deck.add(i + " of " + Suit);
        }
    }
}

