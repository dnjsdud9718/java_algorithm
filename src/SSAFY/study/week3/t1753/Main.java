package SSAFY.study.week3.t1753;
// 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
// 단, 모든 간선의 가중치는 10 이하의 자연수이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// input
// 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다.
// (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
// 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다.
// 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
// 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다.
// 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

// output
// 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
// 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static List<Node>[] src;
    static int[] dist;
    static boolean[] visieted;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        src = new List[N+1];
        dist = new int[N+1];
        visieted = new boolean[N+1];
        for(int i=1; i<=N; i++){
            dist[i] = Integer.MAX_VALUE;
            src[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            src[u].add(new Node(v,w));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
            return o1.weigth - o2.weigth;
        });
        pq.add(new Node(K, 0));
        dist[K] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(!visieted[current.node]){
                visieted[current.node] = true;
                for(Node next : src[current.node]){
                    if(dist[next.node] > current.weigth+next.weigth){
                        dist[next.node] = current.weigth+next.weigth;
                        pq.add(new Node(next.node, dist[next.node]));
                    }
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(dist[i]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
class Node{
    int node;
    int weigth;
    public Node(int node, int weigth){
        this.node = node;
        this.weigth = weigth;
    }
}
