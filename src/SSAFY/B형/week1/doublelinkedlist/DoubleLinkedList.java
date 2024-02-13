package SSAFY.B형.week1.doublelinkedlist;

public interface DoubleLinkedList {
    public void linkFirst(int item);
    public void linkLast(int item);
    public void linkBefore(int item, Node x);

    public int unlinkFirst(Node f);
    public int unlinkLast(Node l);

    public int unlink(Node x);
}
