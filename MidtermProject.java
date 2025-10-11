import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MidtermProject {
    public static Random rand = new Random();
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<String> Deck = new ArrayList<>();
    public static ArrayList<String> PlayerDeck = new ArrayList<>();
    public static ArrayList<String> ComputerDeck = new ArrayList<>();
    public static void main(String[] args) {
        NewDeck(Deck);
        int DealAgain = 1;
        int DealCondition = 2;
        do{
            DealCondition = DealCard(DealCondition);
            System.out.println(PlayerDeck);
            System.out.println(ComputerDeck);
            System.out.println(calculateScore(PlayerDeck));
            System.out.println(calculateScore(ComputerDeck));
            System.out.println("Deal Again? 1: Yes 2: No");
            DealAgain = input.nextInt();
        } while (DealAgain == 1);
    }
    // NewDeck: Wipes old deck and creates a new one; includes face, suit, and number
    public static void NewDeck(ArrayList<String> Deck){
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
    // calculateScore: Reads through given deck and returns total score of deck
    public static int calculateScore(ArrayList<String> GivenDeck){
        int Score = 0;
        for (int i = 0; i < GivenDeck.size(); i++){
            if (GivenDeck.get(i).contains("Ace"))
                Score += 1;
            for (int j = 2; j <= 9; j++)
                if (GivenDeck.get(i).contains(String.valueOf(j)))
                    Score += j;
            if (GivenDeck.get(i).contains("10") || GivenDeck.get(i).contains("Jack") || GivenDeck.get(i).contains("Queen") || GivenDeck.get(i).contains("King"))
                Score += 10;
        }
        return Score;
    }
    // DealCard: Chooses a random elements from ArrayList Deck and moves one to player and computer deck, repsectively
    // Runs twices initally to mimic initial deal, then only runs once until game ends
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

