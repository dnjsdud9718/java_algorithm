package SSAFY.study.t1197;
/*백준 1197 MST 단순 구현 문제 */
// Kruskal로 해결
// MST -> N개 노드를 연결하는 N-1개의 Edge의 가중치 합이 최소가 되도록 하는 알고리즘
import java.io.*;
import java.util.stream.Stream;
import java.util.*;
class Node{
    int u;
    int v;
    int w;
    public Node(){}
    public Node(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
public class Main {
    public static int[] parent; // union-find : 같은 집합에 속하는지 판단 -> 크러스컬 알고리즘에서 사이클이 생기는 지 판단한다.
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int V = inputs[0];
        int E = inputs[1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
            return o1.w - o2.w; // 가중치가 낮은 놈이 우선순위가 높다!
        });
        parent = new int[V+1];
        for(int i=1; i<=V; i++){
            parent[i] = i; // 부모를 자기 자신으로 초기화
        }
        for(int i=0; i<E; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(new Node(inputs[0], inputs[1], inputs[2]));
        }
        long sum = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            int x = find(current.u);
            int y = find(current.v);
            int w = current.w;
            if(x!=y){ // 사이클 여부 판단 -> 사이클이 생기지 않으면 트리에 추가.
                sum += w;
                union(x, y);
            }
        }
        System.out.println(sum);
        br.close();
    }
    public static int find(int x){
        if(parent[x] == x) return x;
        return (parent[x] = find(parent[x])); // 경로 압축
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y) parent[y] = x;
        else parent[x] = y;
    }
}
