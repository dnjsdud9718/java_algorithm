package SSAFY.study.week2.t1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
// 트리 순회 1991
// 순회의 기준 -> root의 위치
// 전위 순회(Preorder) -> root -> left child -> right child
// 중위 순회(Inorder) -> left child -> root -> right child
// 후위 순회(PostOrder) -> left child -> right child -> root
// 이 문제는 배열로 하면 메모리 초과가 나네...
// 노드 객체를 생성해서 처리
// Inner Class에 대해 공부 좀 해야겠다.
public class Main {
    static int N;
    static Node[] addr = new Node[26];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 사용할 노드 미리 생성해 두자
        for (int i = 0; i < 26; i++) { // 노드의 개수 1 <= N <= 26
            addr[i] = new Node();
            addr[i].alpha = i + 'A';
        }


        for (int i = 0; i < N; i++) {
            int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(c -> c.charAt(0)).toArray();
            Node parent = addr[inputs[0] - 'A']; // 레퍼런스 찾아오기
            parent.lChild = inputs[1] == 46 ? null : addr[inputs[1] - 'A'];
            parent.rChild = inputs[2] == 46 ? null : addr[inputs[2] - 'A'];
        }
        Node.preOrder(addr[0]);
        sb.append("\n");
        Node.inOrder(addr[0]);
        sb.append("\n");
        Node.postOrder(addr[0]);
        System.out.println(sb.toString());
        br.close();
    }
    static class Node{
        int alpha;
        Node  lChild;
        Node rChild;
        public Node(){}
        public static void preOrder(Node x){
            if(x == null) return;
            sb.append((char)x.alpha);
            preOrder(x.lChild);
            preOrder(x.rChild);
        }
        public static void inOrder(Node x){
            if(x == null) return;
            inOrder(x.lChild);
            sb.append((char)x.alpha);
            inOrder(x.rChild);
        }
        public static void postOrder(Node x){
            if(x == null) return;
            postOrder(x.lChild);
            postOrder(x.rChild);
            sb.append((char)x.alpha);
        }
    }
}


