import java.util.Scanner;
import java.util.ArrayList;

public class Week10_11InClassLab {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Calls checkExam and getStuAnswers
        checkExam(getStuAnswers());
    }

    // checkExam: Compares the given list of answers determined in getStuAnswers
    // Correct answers are hard-coded
    // Amount of answers incorrect are tracked by decrementing answers correct when the two arrays dont match, starting at 20
    // Index is also added to an arraylist, to track what exact question were wrong
    // After arrays are compared, total score is displayed along with what questions were wrong
    // If score greater than or equal to 15, pass message is displayed, otherwise fail message is
    public static void checkExam(char[] studentAnswers) {
        ArrayList<Integer> answersWrong = new ArrayList<>();
        int answersCorrect = 20;
        int i = 0;
        char[] correctAnswers = {'b','d','a','a','c','a','b','a','c','d','b','c','d','a','d','c','c','b','d','a'};
        for (i = 0; i < correctAnswers.length; i++){
            if (correctAnswers[i] != studentAnswers[i]){
                answersWrong.add(i + 1);
                answersCorrect--;
            }
        }
        System.out.println("Total score: " + answersCorrect + " / 20");
        System.out.println("Questions wrong: " + answersWrong);
        if (answersCorrect >= 15)
            System.out.println("You passed!");
        else
            System.out.println("You failed!");
    }

    // getStuAnswers: Displays question message and tracks what the user inputs for each question
    // Any letter others than A through D is invalid, reprompting the user to enter a letter
    // Returns the array with student answers at the end of the loop
    public static char[] getStuAnswers() {
        char [] studentAnswers = new char[20];
        System.out.println("Exam Start!");
        for (int i = 0; i < studentAnswers.length;){
            System.out.println("Enter your answer (A,B,C,D) - Question " + (i + 1));
            studentAnswers[i] = input.nextLine().toLowerCase().charAt(0);
            switch (studentAnswers[i]){
                case 'a':
                    i++;
                    break;
                case 'b':
                    i++;
                    break;
                case 'c':
                    i++;
                    break;
                case 'd':
                    i++;
                    break;
                default:
                    System.out.println("Only characters A through D are accepted!!!");
                    break;
            }
        }
        return studentAnswers;
    }
}
