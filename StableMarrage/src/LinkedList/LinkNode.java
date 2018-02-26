package LinkedList;

/**
 * Created by SlomaM on 5/31/2017.
 */
class LinkNode<T> {
    private LinkNode<T> link;
    private T data;

    LinkNode(){
        this.link = null;
        this.data = null;
    }

    LinkNode(LinkNode<T> link, T data){
        this.link = link;
        this.data = data;
    }

    //Setter methods
    void setLink(LinkNode<T> link){
        this.link = link;
    }

    void setData(T data){
        this.data = data;
    }

    //Getter methods

    LinkNode<T> getLink(){
        return this.link;
    }

    T getData(){
        return this.data;
    }
}
