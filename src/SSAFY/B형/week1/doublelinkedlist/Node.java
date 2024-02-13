package SSAFY.B형.week1.doublelinkedlist;

import java.util.LinkedList;

public class Node {
    int item;
    Node prev;
    Node next;
    public Node(int item, Node prev, Node next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
