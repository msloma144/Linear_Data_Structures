/**
 * Created by Michael Sloma on 9/1/2017.
 */
public class CreatePairOfDice {
    static void printDice(Die die1, Die die2){
        System.out.println("Die 1:");
        System.out.println(die1);
        System.out.println();
        System.out.println("Die 2:");
        System.out.println(die2);
    }

    public static void main(String[] args){
        Die die1 = new Die();
        Die die2 = new Die();

        die1.setDieValue(1 + (int)(Math.random() * ((6 - 1) + 1)));
        die2.setDieValue(1 + (int)(Math.random() * ((6 - 1) + 1)));

        printDice(die1, die2);
    }
}
