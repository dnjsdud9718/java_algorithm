package SSAFY.B형.week1.linkedlist_b;

class LinkedList {
    int size;
    public class Node{
        int item;
        Node prev;
        Node next;

        public Node(){}
        public Node(int item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    Node first;
    Node last;
    public void linkFirst(int item) {
        Node f = first;
        Node newNode = new Node(item, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }else {
            f.prev = newNode;
        }
        size++;
    }
    public void linkLast(int item) {
        Node l = last;
        Node newNode = new Node(item, l, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public void linkBefore(int item, Node succ) {
        assert succ != null;
        Node pred = succ.prev;
        Node newNode = new Node(item, pred, succ);
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        succ.prev = newNode;
        size++;
    }

    public int unlinkFirst(Node f) {
        assert f == first && f != null;
        int item = f.item;
        Node next = f.next;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return item;
    }

    public int unlinkLast(Node l) {
        assert l == last && l != null;
        int item = l.item;
        Node prev = l.prev;
        l.prev = null;
        last = prev;
        if (last == null) {
            first = null;
        }else {
            prev.next = null;
        }
        size--;
        return item;
    }

    public int unlink(Node x) {
        assert x != null;
        int item = x.item;
        Node prev = x.prev;
        Node next = x.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        size--;
        return item;
    }

    public void concat(Node x, Node y, int s) {

        Node l = last;
        l.next = x;
        x.prev = l;
        last = y;
        y.next = null;
        size += s;
    }
}
