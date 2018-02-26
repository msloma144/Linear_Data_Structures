import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michael Sloma on 9/1/2017.
 */
public class SecretNumber {
    static int generateRandNum(){
        Random rand = new Random();

        return rand.nextInt(100) + 1;
    }

    static int checkInput(String guessString){
        int guessInt;

        try {
            guessInt = Integer.parseInt(guessString);
        }
        catch (java.lang.NumberFormatException e){
            System.out.println("The number you entered is not an integer! \nPlease enter another guess!");
            guessInt = -1;
        }
        return guessInt;
    }

    static void processInput(int guess, int secretNumber){
        int intDifference = Math.abs(secretNumber - guess);

        if(guess == 0){
            System.out.println("Goodbye!");
            System.exit(0);
        }
        else if (intDifference == 0){
            System.out.println("Congratulations! " + guess + " was my secret number!");
            System.exit(0);
        }
        else if(intDifference > 30){
            System.out.println("Way Too High or Way Too Low");
        }
        else if(intDifference < 10){
            System.out.println("A Little High or A Little Low");
        }
        else {
            System.out.println("High or Low");
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String guessString;
        int guessInt = -1;
        int secretNum = generateRandNum();

        while (true) {
            while (guessInt == -1) {
                System.out.print("Enter your guess: ");
                guessString = in.nextLine();
                guessInt = checkInput(guessString);
            }
            processInput(guessInt, secretNum);
            guessInt = -1;
        }
    }
}
