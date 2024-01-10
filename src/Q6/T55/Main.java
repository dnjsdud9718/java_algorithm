package Q6.T55;
/*
 * 1753 최단 경로 구하기
 * 방향 그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단경로를 구하는 프로그램을 작성. 모든 간선의 가중치는 10이하의 자연수
 * 
 * 입력
 * 첫째 줄에는 정점의 개수 V와 간선의 개수 E가 주어진다(1 <= V <= 20,000, 1<= E <= 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 
 * 있다고 가정. 둘째 줄에는 시작 정점의 번호 K(1 <= K <= V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수(
 * u,v,w)가 순서대로 주어진다. u -> v 로 가는 가중치 w(u != v)5 6
    1
    5 1 1
    1 2 2
    1 3 3
    2 3 4
    2 4 5
    3 4 6

    1. 출발 노드 택
    2. 출발 노드 기준 각 노드의 최소비용 저장
    3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드 택
    4. 해당 노드를 거쳐서 특정한 노드로 가는 경우 고려하여 최소비용 갱신
    5. 3~4번 과정을 노드의 수만큼 반복

    1. 출발 노드(vertext, weight 로 구성) 택
    2. 최소 비용 배열 초기화(무한대)
    3. 시작 노드 우선순위 큐에 삽입(우선순위 큐는 weight 오름차순)
    4. 큐가 빌 때까지...
        4.1 pop()
        4.2 방문여부 판단. 방문하지 않았던 경우 아래로 진행
        4.3 방문처리
        4.4 인접노드 최소비용 갱신(갱신 후 우선순위 큐에 삽입)
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int V = inputs[0];
        int E = inputs[1];
        int K = Integer.parseInt(br.readLine()); // start node number
        LinkedList<Node>[] list = new LinkedList[V+1];
        for(int i=0; i<=V; i++) list[i] = new LinkedList<>();

        for(int i=0; i<E; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[inputs[0]].add(new Node(inputs[1], inputs[2]));
        }

        int[] dist = new int[V+1];
        boolean[] visited = new boolean[V+1];
        for(int i=1; i<=V; i++) dist[i] = Integer.MAX_VALUE; // 최소비용배열 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight; // weight 오름차순 정렬
        });
        pq.add(new Node(K, 0)); //시작 노드 삽입
        dist[K] = 0; //최소비용 0
        while(!pq.isEmpty()){
            Node u = pq.poll(); // 현재 최소경로를 가진 노드를 가져온다. 
            if(!visited[u.vertex]){ // 방문 여부 판단.
                visited[u.vertex] = true;
                for(Node v : list[u.vertex]){
                    // 최소 경로 갱신
                    dist[v.vertex] = Math.min(dist[v.vertex], dist[u.vertex]+v.weight);
                    if(dist[v.vertex] == dist[u.vertex] + v.weight) pq.add(new Node(v.vertex, dist[v.vertex]));
                }
            }
        }

        for(int i=1; i<= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(dist[i]+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
class Node{
        int vertex;
        int weight;
        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
