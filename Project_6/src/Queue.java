import java.util.ArrayList;

/**
 * Created by Michael Sloma on 2/24/2017.
 */
public class Queue<T> {
    private ArrayList<T> queue;
    private int MAXSIZE;
    private int front, rear, size;

    public Queue(){
        this.queue = new ArrayList<>();
        this.MAXSIZE = -1;
        this.front = 0;
        this.rear = MAXSIZE - 1;
        this.size = 0;
    }

    public Queue(int MAXSIZE){
        this.queue = new ArrayList<>();
        this.MAXSIZE = MAXSIZE;
        for(int i = 0; i < this.MAXSIZE; i++){
            queue.add(null);
        }
        this.front = 0;
        this.rear = MAXSIZE - 1;
        this.size = 0;
    }

    public void enqueue(T element){
        if (isFull()) {
            System.out.println("Queue is full!");
        }
        else if(MAXSIZE == -1){
            queue.add(0, element);
            size++;
        }
        else {
            rear = (rear + 1) % MAXSIZE;
            queue.set(rear, element);
            size++;
        }
    }

    public T dequeue(){
        if (isEmpty()) {
            System.out.println("Dequeue attempted on empty queue!");
            return null;
        }
        else if(MAXSIZE == -1){
            int endQueue = queue.size() - 1;

            T temp = queue.get(endQueue);
            queue.remove(endQueue);
            size--;
            return temp;
        }
        else {
            T tmp = queue.get(front);
            this.front = (this.front + 1) % MAXSIZE;
            this.size--;
            return tmp;
        }
    }

    boolean isEmpty(){
        return this.size == 0;
    }

    boolean isFull(){
        return this.size == MAXSIZE;
    }

    public int size() {
        return size;
    }
}
