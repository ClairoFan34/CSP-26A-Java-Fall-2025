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
        String PlayAgain;
        do{
            System.out.println("\nStart!");
            NewDeck(Deck);
            DealCardPlayer(2);
            DealCardComputer(2);
            if (NextMove() == true){
                ComputerMove();
                // Display Computers final hand and score for clarity
                System.out.println("Computers Hand: " + ComputerDeck);
                if (ComputerScore >= 0)
                    System.out.println("Computers Score: " + ComputerScore);
                else if(ComputerScore == -1)
                    System.out.println("Computers Score: BLACKJACK");
                WinLogic();
            }
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
    // NewDeck: Wipes all old decks along with scores and create a new deck; includes face, suit, and number
    public static void NewDeck(ArrayList<String> Deck){
        PlayerScore = 0;
        ComputerScore = 0;
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
    // Value -1 means Blackjack
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
    // DealCardX: Chooses a random element from ArrayList Deck and adds it to player or computer deck, repsectively
    // also removes card from deck so it isn't dealt to both the player and computer during next hand
    // Condition used to determine amonunt of cards dealt. Ran twice initally to mimic initial deal, then only runs once until round ends
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
    // NextMove: All info relating to players next move 
    // Asks for players next move, hit or stand
    // Also displays current hand, current score, as well as prevents the Player from hiiting if they scored a Blackjack or Busted
    // returns true if player is still in the game and false if theyre not
    public static boolean NextMove(){
        String DealAgain = "1";
        do {
            System.out.println("Current Hand: " + PlayerDeck);
            PlayerScore = CalculateScore(PlayerDeck);
            if(PlayerScore >= 0)
                System.out.println("Current Score: " + PlayerScore);
            if (PlayerScore > 21){
                System.out.println("Bust! Player loses!");
                return false;
            }
            else if (PlayerScore == -1){
                System.out.println("BLACKJACK!!!");
                return true;
            }
            System.out.println("Deal Again? 1. Yes 2. No");
            DealAgain = input.next().toLowerCase();
            if (DealAgain.equals("1") || DealAgain.equals("yes")) 
                DealCardPlayer(1);
        } while (DealAgain.equals("1") || DealAgain.equals("yes"));
        return true;
    }
    // ComputerMove: Functions the same as NextMove, but for computer and doesn't return
    // Forces the computer to hit if their score isnt over 17
    public static void ComputerMove(){
        ComputerScore = CalculateScore(ComputerDeck);
        while (ComputerScore < 17){
            DealCardComputer(1);
            ComputerScore = CalculateScore(ComputerDeck);
        }
    }
    //WinLogic: If player does not bust, checks for a ties, who's closer to 21, or if computer scored Blackjack
    public static void WinLogic(){
            if (PlayerScore == -1 && ComputerScore == -1)
                System.out.println("Tie! Both Player and Computer scored BLACKJACK");
            else if (PlayerScore == -1)
                System.out.println("BLACKJACK!!! Player wins!");
            else if (ComputerScore == -1)
                System.out.println("Computer scored BLACKJACK!!! Computer wins!");
            else if (PlayerScore > 21 && ComputerScore > 21)
                System.out.println("Both players bust! Tie!");
            else if (PlayerScore > 21)
                System.out.println("Computer Wins!");
            else if (ComputerScore > 21)
                System.out.println("Computer bust! Player wins!");
            else if (21 - PlayerScore < 21 - ComputerScore)
                System.out.println("Player closer 21, Player Wins!");
            else if (21 - PlayerScore > 21 - ComputerScore)
                System.out.println("Computer closer 21, Computer Wins!");
            else if (PlayerScore == ComputerScore)
                System.out.println("Both players scored the same, Tie!");
            else
                System.out.println("How even");
    }
}

