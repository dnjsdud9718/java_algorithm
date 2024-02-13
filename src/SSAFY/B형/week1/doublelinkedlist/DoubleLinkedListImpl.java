package SSAFY.B형.week1.doublelinkedlist;

import java.util.NoSuchElementException;

public class DoubleLinkedListImpl implements DoubleLinkedList{
    private int size;
    private Node first;
    private Node last;

    public DoubleLinkedListImpl() {
        this.size = 0;
        first = last = null;
    }

    public int getSize() {
        return size;
    }

    public Node getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first;
    }

    public Node getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last;
    }

    @Override
    public void linkFirst(int item) {
        Node f = first;
        Node newNode = new Node(item, null, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    @Override
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

    @Override
    public void linkBefore(int item, Node x) {
        assert (x != null);
        Node prev = x.prev;
        Node newNode = new Node(item, prev, x);
        x.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public int unlinkFirst(Node f) {
        assert (f == first && first != null);
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

    @Override
    public int unlinkLast(Node l) {
        assert (l == last && l != null);
        int item = l.item;
        Node prev = l.prev;
        l.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return item;
    }

    @Override
    public int unlink(Node x) {
        assert (x != null);
        Node prev = x.prev;
        Node next = x.next;
        int item = x.item;

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

    public int getFirstItem() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return getFirst().item;
    }

    public int getLastItem() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return getLast().item;
    }

    public int removeFirst() {
        Node f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }
    public int removeLast() {
        Node l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }

    public int remove(Node x) {
        if (x == null) {
            throw new NoSuchElementException();
        }
        return unlink(x);
    }

    public void addFirst(int item) {
        linkFirst(item);
    }

    public void addLast(int item) {
        linkLast(item);
    }

    public boolean add(int item) {
        linkLast(item);
        return true;
    }

    public boolean addAll(Node f2, Node l2, int s2) {
        assert (s2 > 0);
        if (last == null) {
            first = f2;
        } else {
            last.next = f2;
        }
        last = l2;
        size += s2;
        return true;
    }

}
