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
        PrintRules();
        // Stores Computers point total
        int ComputerScore = 0;
        // Stores Players point total
        int PlayerScore = 0;
        // Initilized DealConiditon for later use
        int DealCondtion = 0;
        String DealAgain = "1";
        String PlayAgain;
        do{
            System.out.println("\nStart!");
            // New deck created, old decks cleared
            NewDeck(Deck);
            // var DealCondtion is used for func DealCard, every first go around it's set to 2 so 2 cards are dealt. After that it's set to 1 so only one card is dealt
            // Reset to 2 when saying yes to play again
            DealCondtion = 2;
            NextMove(DealCondtion, PlayerScore, ComputerScore, DealAgain);
            System.out.println("Play Again? 1. Yes 2. No");
            PlayAgain = input.next().toLowerCase();
        } while (PlayAgain.contains("1") || PlayAgain.contains("yes"));
        
    }
    // PrintRules: Just prints the rules of the game :P
    public static void PrintRules(){
        System.out.println("Welcome to Blackjack!");
        System.out.println("Rules: ");
        System.out.println("1. A total score over 21 results in a bust \n2. Closest score to 21 wins\n3. Blackjack is highest hand, consisting of an Ace and any 10-point card");
        System.out.println("Scoring(Lowest to Highest):");
        System.out.println("Ace = 1; 2-9 = Pip Value; 10 and Face Cards = 10; Blackjack = Ace and any Face card");
        System.out.println("Note: Dealer will always hit as long as their total is less than 17");
    }
    // NewDeck: Wipes old Deck, PLayerDeck, and ComputerDeck and creates a new ones; includes face, suit, and number
    public static void NewDeck(ArrayList<String> Deck){
        Deck.clear();
        PlayerDeck.clear();
        ComputerDeck.clear();
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
    // CalculateScore: Reads through given deck and returns total score of deck
    // Done by reading the string at given index and adding corresponding value of it to total score using if statments
    // Value 0 means Blackjack
    public static int CalculateScore(ArrayList<String> GivenDeck){
        int Score = 0;
        boolean Ace = false;
        boolean Ten = false;
        for (int i = 0; i < GivenDeck.size(); i++){
            if (GivenDeck.get(i).contains("Ace")){
                Score += 1;
                Ace = true;
            }
            for (int j = 2; j <= 9; j++)
                if (GivenDeck.get(i).contains(String.valueOf(j)))
                    Score += j;
            if (GivenDeck.get(i).contains("10") || GivenDeck.get(i).contains("Jack") || GivenDeck.get(i).contains("Queen") || GivenDeck.get(i).contains("King")){
                Score += 10;
                Ten = true;
            }
            // Blackjack can only trigger on initial hand, checked by GivenDeck size only being 2 cards and if an Ace and Ten are present
            if (Ace == true && Ten == true && GivenDeck.size() == 2 )
                return -1;
        }
        return Score;
    }
    // DealCard: Chooses a random element from ArrayList Deck and adds it to player and computer deck, repsectively
    // also removes card from deck so it isn't dealt to both the player and computer during next hand
    // Runs twices initally to mimic initial deal, then only runs once until round ends
    // Makes sure the same index isn't chosen so the same card isn't dealt twice during current deal
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
            if (CalculateScore(ComputerDeck) < 17 && CalculateScore(ComputerDeck) >= 0) {
                ComputerDeck.add(Deck.get(Index2));
                Deck.remove(Index2);
            }
        }
        return 1;
    }   
    // NextMove: All task and info related to player's next move
    // Ask player if they want to hit again and does so if answered yes
    // Also displays current hand, current score, as well as prevents the Player or Computer hitting if they scored a Blackjack or Busted
    public static void NextMove(int DealCondtion, int PlayerScore, int ComputerScore, String DealAgain){
        do {
            DealCard(DealCondtion);
            DealCondtion = 1;
            System.out.println("Current Hand:\n" + PlayerDeck);
            System.out.println("Computer Hand:\n" + ComputerDeck);
            PlayerScore = CalculateScore(PlayerDeck);
            ComputerScore = CalculateScore(ComputerDeck);
            System.out.println("Computer Score: " + ComputerScore);
            // All Logic for detecting Blackjack and Bust in either ComputerScore or PlayerScore
            if (PlayerScore > 21){
                System.out.println("Current Score: " + PlayerScore);
                System.out.println("Bust! Player Loses");
                break;
            }
            else if (ComputerScore > 21){
                System.out.println("Computer Score: " + ComputerScore);
                System.out.println("Bust! Computer Loses. Computer Score: " + ComputerScore);
                break;
            }
            else if (PlayerScore == -1) {
                if (ComputerScore == -1) {
                    System.out.println("Computer also scored Blackjack, Tie!");
                    break;
                } 
                else {
                    System.out.println("BLACKJACK!!! Player Wins!");
                    break;
                }
            } 
            else if (ComputerScore == -1) {
                System.out.println("Computer scored Blackjack");
                break;
            } 
            else {
                if (PlayerScore >= 0)
                    System.out.println("Current Score: " + PlayerScore);
            }
            System.out.println("Deal Again? 1. Yes 2. No");
            DealAgain = input.next().toLowerCase();
        } while (DealAgain.contains("1") || DealAgain.contains("yes"));
        if (!DealAgain.contains("1") || !DealAgain.contains("yes") && PlayerScore != -1 || ComputerScore == -1)
            WinLogic(ComputerScore, PlayerScore);
    }
    //WinLogic: If player or computer does not bust or blackjack, checks for a tie or who's closer to 21
    public static void WinLogic(int ComputerScore, int PlayerScore){
            if (21 - ComputerScore < 21 - PlayerScore)
                System.out.println("Computer wins! Computer Score:" + ComputerScore);
            else if (21 - ComputerScore == 21 - PlayerScore)
                System.out.println("Tie!");
            else
                System.out.println("Player wins!"); 
    }
}

