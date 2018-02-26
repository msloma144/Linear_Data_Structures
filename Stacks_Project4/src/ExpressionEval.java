import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Michael Sloma on 10/18/2017.
 */
public class ExpressionEval {
    private static String input(){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter an expression: ");
        return in.nextLine();
    }

    private static boolean isInt(String value){
        try {
            Integer.parseInt(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private static int performOp(String op, int value1, int value2){
        switch (op) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            default:
                System.out.println("Invalid operator!"); System.exit(0); return 0;
        }
    }

    private static int parseInput(String expression){
        ArrayList<String> priority = new ArrayList<>(Arrays.asList("-", "+", "*"));
        Stack<String> operator = new Stack<>();
        Stack<Integer> value = new Stack<>();

        for(int i = 0; i < expression.length(); i++){
            String character = expression.substring(i, i + 1);

            if(character.equals("q")) System.exit(0);
            else if(character.equals(" "));
            else if(isInt(character)){
                value.push(Integer.parseInt(character));
            }
            else{
                if(operator.isEmpty()){
                    operator.push(character);
                }
                else {
                    while (!operator.isEmpty() && (priority.indexOf(operator.peek()) > priority.indexOf(character))) {
                        String op = operator.pop();
                        int value1 = value.pop();
                        int value2 = value.pop();

                        value.push(performOp(op, value1, value2));
                    }
                    operator.push(character);
                }
            }
        }

        while (!operator.isEmpty()){
            String op = operator.pop();
            int value1 = value.pop();
            int value2 = value.pop();

            value.push(performOp(op, value1, value2));
        }

        return value.pop();
    }

    public static void main(String[] args){
        while(1 == 1){
            String expression = input();
            System.out.println("Answer: " + parseInput(expression));
            //System.exit(0);
        }
    }
}
