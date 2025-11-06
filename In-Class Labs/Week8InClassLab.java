import java.util.Random;
import java.util.Scanner;

public class Week8InClassLab {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int UserChoice;
        int ComputerChoice;
        String PlayAgain = "";
        // Main game loop
        do {
            UserChoice = userChoice();
            ComputerChoice = computerChoice();
            checkWinner(ComputerChoice, UserChoice);
            System.out.println("Would you like to play again? 1. Yes 0. No");
            PlayAgain = input.next().toLowerCase();
    } while (PlayAgain.equals("yes") || PlayAgain.equals("1"));
}
    // Randomly generates a number betweeen 1 - 3, for the computer's choice
    public static int computerChoice(){
        Random rand = new Random();
        int choice = rand.nextInt(3) + 1;
        String ChoiceName = "";
        switch (choice){
            case 1:
                ChoiceName = "Rock";
                break;
            case 2:
                ChoiceName = "Paper";
                break;
            case 3:
                ChoiceName = "Scissors";
                break;
        }
        System.out.println("Computers Choice: " + ChoiceName);
        return choice;
    }
    // Ask the user for their choice of 1 - 3
    public static int userChoice(){
        String choice;
        String ChoiceName = "";
        int choiceNum = 0;
        do {
            System.out.println("Enter your choice");
            System.out.println("Choose: 1. Rock 2. Paper 3. Scissors");
            choice = input.next().toLowerCase();
            if (choice.equals("rock") || choice.equals("1")){
                ChoiceName = "Rock";
                choiceNum = 1;
            }
            else if (choice.equals("paper") || choice.equals("2")) {
                ChoiceName = "Paper";
                choiceNum = 2;
            }
            else if (choice.equals("scissors") || choice.equals("3")) {
                ChoiceName = "Scissors";
                choiceNum = 3;
            }
            else
                System.out.println("Invalid input, try again");
        } while (choiceNum < 1 || choiceNum > 3);
        System.out.println("You chose: " + ChoiceName);
        return choiceNum;
    }
    public static void checkWinner(int Computer, int User){
        if (Computer == User)
            System.out.println("It's a tie!");
        else if ((User == 1 && Computer == 3) || (User == 2 && Computer == 1) || (User == 3 && Computer == 2))
            System.out.println("You win!");
        else
            System.out.println("Computer wins!");
    }
}
