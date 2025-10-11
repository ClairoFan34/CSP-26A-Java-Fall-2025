import java.util.ArrayList;
import java.util.Random;

public class MidtermProject {
    public static Random rand = new Random();
    public static ArrayList<Object> Deck = new ArrayList<>();
    public static ArrayList<Object> PlayerDeck = new ArrayList<>();
    public static ArrayList<Object> ComputerDeck = new ArrayList<>();
    public static void main(String[] args) {
        
        NewDeck(Deck);
        int DealCondition = 2;
        DealCard(Deck, PlayerDeck, ComputerDeck);
        System.out.println(PlayerDeck);
        System.out.println(ComputerDeck);
    }
    // NewDeck: Wipes old deck and creates a new one; includes face, suit, and number
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
    // InitialCard: Chooses a random elements from ArrayList Deck and moves them, runs twice to deal two cards for both player and computer. 
    // Makes sure the same index isn't chosen so the same card isn't dealt twice
    // Removes card from deck(Element from array) and adds it to either the Players or Computers deck
    public static int DealCard(int Condition){
        int Index1;
        int Index2;
        for (int i = 0; i < Condition; i++){
            do {
                Index1 = rand.nextInt(Deck.size() - 1);
                Index2 = rand.nextInt(Deck.size() - 1);
            } while (Index1 == Index2);
            PlayerDeck.add(Deck.get(Index1));
            Deck.remove(Index1);
            ComputerDeck.add(Deck.get(Index2));
            Deck.remove(Index2);
        }
        return 1;
    }   
}

