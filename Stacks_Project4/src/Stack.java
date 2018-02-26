import java.util.ArrayList;

/**
 * Created by Michael Sloma on 2/24/2017.
 */
public class Stack<T> {
    private ArrayList<T> stack;

    public Stack(){
        this.stack = new ArrayList<>();
    }

    public T pop(){
        //returns top element
        if(!isEmpty()){
            T temp = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return temp;
        }
        else {
            System.out.println("Pop attempted on an empty stack!");
            return null;
        }
    }

    public T peek(){
        //Returns the value on top of the stack
        if(!isEmpty()){
            return stack.get(stack.size()-1);
        }
        else {
            System.out.println("Peek attempted on an empty stack!");
            return null;
        }
    }

    public void push(T element){
        //Add element to the stack
        this.stack.add(element);
    }

    boolean isEmpty(){
        if(stack.size() == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
