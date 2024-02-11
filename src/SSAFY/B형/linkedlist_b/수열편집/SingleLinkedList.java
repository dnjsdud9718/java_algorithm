package SSAFY.B형.linkedlist_b.수열편집;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class SingleLinkedList {
    static final int NODE_POOL_SIZE = 100000;
    static Node[] nodes = new Node[NODE_POOL_SIZE];
    static int idx;
    static Node head;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int ts = 1; ts <= T; ts++) {
            int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N= inputs[0];
            int M= inputs[1];
            int L = inputs[2];
            idx = 0;
            head = makeNode(0);
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int i=N-1; i>=0; i--){
                insertFront(inputs[i]);
            }
            for(int i=0; i<M; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                switch (st.nextToken()) {
                    case "I":
                        insert(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
                        break;
                    case "D":
                        delete(Integer.parseInt(st.nextToken()));
                        break;
                    case "C":
                        change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    default: break;
                }
                print();
            }
            int answer = getKth(L) == null ? -1 : getKth(L).value;
            sb.append("#").append(ts).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
    // make node
    public static Node makeNode(int value)  {
        if(idx == NODE_POOL_SIZE) {
            return null;
        }
        nodes[idx] = new Node();
        nodes[idx].value = value;
        nodes[idx].prev = nodes[idx].next = null;
        return nodes[idx++];
    }
    // get kth Node from linked list
    public static Node getKth(int k) {
        Node node = head;
        for (int i = 0; i <= k; i++) {
            node = node.next;
            if(node == null) break;
        }
        return node;
    }
    // delete after kth node
    public static void delete(int k) {
        Node prev = getKth(k-1);
        if(prev == null) return;
        if(prev.next == null) return;
        prev.next = prev.next.next;
    }
    public static void insertFront(int val) {
        Node node = makeNode(val);
        if (node == null) return;
        node.next = head.next;
        head.next = node;
    }
    // insert after kth node
    public static void insert(int k, int val){
        Node prev = getKth(k);
        if(prev == null) return;
        Node node = makeNode(val);
        if(node == null) return;
        node.next = prev.next;
        prev.next = node;
    }

    public static void change(int k, int val) {
        Node node = getKth(k);
        if(node == null) return;
        node.value = val;
    }

    public static void print() {
        Node node = head;
        while (node.next != null) {
            node = node.next;
            System.out.print(node.value+" ");
        }
        System.out.println();
    }
}

