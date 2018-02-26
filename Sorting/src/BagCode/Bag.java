import java.util.ArrayList;

/**
 * Created by Michael Sloma on 9/11/2017.
 */
public class Bag<T> {
    private ArrayList<T> contents;
    private int manyItems;

    public Bag(){
        this.contents = new ArrayList<>();
    }

    public Bag(ArrayList<T> input){
        this.contents = input;
    }

    public void add(T newEntry){
        this.contents.add(newEntry);
    }

    public void remove(T value){
        this.contents.remove(value);
    }

    public int size(){
        return this.contents.size();
    }

    public int countOccurances(T target){
        int counter = 0;

        for(T value: this.contents){
            if(value.equals(target)) counter++;
        }

        return counter;
    }
}
