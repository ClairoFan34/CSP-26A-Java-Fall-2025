import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MidtermProject {
    public static Random rand = new Random();
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<String> Deck = new ArrayList<>();
    public static ArrayList<String> PlayerDeck = new ArrayList<>();
    public static ArrayList<String> ComputerDeck = new ArrayList<>();
    public static int PlayerScore = 0;
    public static int ComputerScore = 0;
    public static void main(String[] args) {
        PrintRules();
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
            NextMove(DealCondtion, DealAgain);
            System.out.println("Play Again? 1. Yes 2. No");
            PlayAgain = input.next().toLowerCase();
        } while (PlayAgain.equals("1") || PlayAgain.equals("yes"));
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
            else if (GivenDeck.get(i).contains("10") || GivenDeck.get(i).contains("Jack") || GivenDeck.get(i).contains("Queen") || GivenDeck.get(i).contains("King")){
                Score += 10;
                Ten = true;
            }
            else {
                for (int j = 2; j <= 9; j++) {
                    if (GivenDeck.get(i).contains(String.valueOf(j)))
                    Score += j;
                }
            }
        }
        // Blackjack can only trigger on initial hand, checked by GivenDeck size only being 2 cards and if an Ace and Ten are presen
        if (Ace == true && Ten == true && GivenDeck.size() == 2 )
                return -1;
        return Score;
    }
    // DealCardX: Chooses a random element from ArrayList Deck and adds it to player and computer deck, repsectively
    // also removes card from deck so it isn't dealt to both the player and computer during next hand
    // Runs twices initally to mimic initial deal, then only runs once until round ends
    public static int DealCardPlayer(int Condition){
        int Index;
        for (int i = 0; i < Condition; i++){
            Index = rand.nextInt(Deck.size());
            PlayerDeck.add(Deck.get(Index));
            Deck.remove(Index);
        }
        return 1;
    }
    public static int DealCardComputer(int Condition){
        int Index;
        for (int i = 0; i < Condition; i++){
            Index = rand.nextInt(Deck.size());
            ComputerDeck.add(Deck.get(Index));
            Deck.remove(Index);
        }
        return 1;
    }   
    // NextMove: All task and info related to player's next move
    // Ask player if they want to hit again and does so if answered yes
    // Also displays current hand, current score, as well as prevents the Player or Computer hitting if they scored a Blackjack or Busted
    public static void NextMove(int DealCondition, String DealAgain){
        do {
            DealCardPlayer(DealCondition);
            DealCardComputer(DealCondition);
            DealCondition = 1;
            System.out.println("Current Hand:\n" + PlayerDeck);
            System.out.println("Computer Hand:\n" + ComputerDeck);
            PlayerScore = CalculateScore(PlayerDeck);
            ComputerScore = CalculateScore(ComputerDeck);
            System.out.println("Computer Score: " + ComputerScore);
            // All Logic for detecting Blackjack and Bust in either ComputerScore or PlayerScore
            if (PlayerScore > 21 && ComputerScore > 21){
                System.err.println("Complete Bust! Player and Computer lose!");
                break;
            }
            else if (PlayerScore > 21){
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
        } while (DealAgain.equals("1") || DealAgain.equals("yes"));
        if (!DealAgain.contains("1") && !DealAgain.contains("yes") && PlayerScore != -1 && ComputerScore != -1)
        while (ComputerScore < 17) {
            DealCardComputer(1);
            ComputerScore = CalculateScore(ComputerDeck);
        }
            WinLogic();
    }
    //WinLogic: If player or computer does not bust or blackjack, checks for a tie or who's closer to 21
    public static void WinLogic(){
            if (!(ComputerScore > 21) && !(PlayerScore > 21)){
                if (21 - ComputerScore < 21 - PlayerScore)
                    System.out.println("Computer wins! Computer Score: " + ComputerScore);
                else if (21 - ComputerScore == 21 - PlayerScore)
                    System.out.println("Tie!");
                else
                    System.out.println("Player wins!"); 
            }
    }
}

