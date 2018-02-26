package LinkedList;

/**
 * Created by SlomaM on 5/31/2017.
 */
public class LinkedList<T> {
    private LinkNode<T> startNode;
    private LinkNode<T> endNode;
    private int size;

    public LinkedList(){
        this.startNode = null;
        this.endNode = null;
        this.size = 0;
    }

    //checks if list is empty
    public boolean isEmpty(){
        return startNode == null;
    }

    //Insert node at start of link-list
    public void insertAtStart(T dataVal){
        LinkNode<T> newNode = new LinkNode<>(null,dataVal);
        size++;
        if(startNode == null){
            startNode = newNode;
            endNode = startNode;
        }

        else{
            newNode.setLink(startNode);
            startNode = newNode;
        }
    }

    //Insert node at end of link-list
    public void insertAtEnd(T dataVal){
        LinkNode<T> newNode = new LinkNode<>(null, dataVal);
        if(startNode == null){
            startNode = newNode;
            endNode = startNode;
        }
        else{
            endNode.setLink(newNode);
            endNode = newNode;
        }
        size++;
    }

    //Insert a new node at position
    public void insertAtPos(T dataVal, int pos){
        LinkNode<T> newNode = new LinkNode<>(null, dataVal);
        if(startNode == null){
            startNode = newNode;
            endNode = startNode;
            System.out.println("Entered at beginning");
        }
        else if(pos >= (size)){
            insertAtEnd(dataVal);
            System.out.println("Entered at the end of the list");
        }
        else {
            LinkNode<T> currentNode = this.startNode;
            pos--;
            for (int i = 0; i < size; i++) {
                if (i == pos) {
                    LinkNode<T> tmp = currentNode.getLink();
                    currentNode.setLink(newNode);
                    newNode.setLink(tmp);
                    break;
                }
                currentNode = currentNode.getLink();
            }
        }
        size++;
    }

    public void add(T dataVal){
        LinkNode<T> newNode = new LinkNode<T>(null, dataVal);
        if(startNode == null){
            startNode = newNode;
            endNode = startNode;
        }
        else {
            LinkNode<T> currentNode = this.startNode;
            // loop through all nodes till the end is reached
            for (int i = 0; i < size; i++) {
                if (i == (size - 1)) {
                    LinkNode<T> tmp = currentNode.getLink();
                    currentNode.setLink(newNode);
                    newNode.setLink(null);
                    endNode = newNode;
                    break;
                }
                // change to next node
                currentNode = currentNode.getLink();
            }
        }
        size++;
    }

    public T get(int index){
        if (size == 0) {
            System.out.println("Linked list is empty");
        }
        else if(index > size){
            System.out.println("Index out of bounds!");
        }
        else {
            LinkNode<T> ptr = startNode;

            //loop through nodes till index is reached
            for(int i = 0; i < index; i++){
                ptr = ptr.getLink();
            }
            return ptr.getData();
        }
        return null;
    }

    public void search(T item){
        if (size == 0) {
            System.out.println("Linked list is empty");
        }
        else {
            boolean found = false;
            LinkNode<T> ptr = startNode;

            //loop through all nodes and look for item
            while (ptr.getLink() != null){
                if(ptr.getData().equals(item)) {System.out.println(item + " found!"); found = true; break; }
                else{
                    ptr = ptr.getLink();
                }
            }
            // check end node for item
            if(endNode.getData().equals(item)) {System.out.println(item + " found!"); found = true;}
            if(!found) System.out.println(item + " not found!");
        }
    }

    public void display() {
        System.out.println("Singly Linked List: ");
        if (size == 0) {
            System.out.println("empty");
        } else if (startNode.getLink() == null) {
            System.out.println(startNode.getData());
        } else {
            LinkNode<T> ptr = startNode;
            System.out.print(startNode.getData() + "->");
            ptr = startNode.getLink();
            while (ptr.getLink() != null) {
                System.out.print(ptr.getData() + "->");
                ptr = ptr.getLink();
            }
            System.out.print(ptr.getData() + "\n");
        }
    }

    public int size(){
        return size;
    }
}
