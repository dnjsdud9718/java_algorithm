package Q6Graph.T57;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;
// 방문 표기를 하면 안되네...
public class Main2 {
    static int N, M, K;
    static List<Vertex> src[];
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        K = inputs[2];
        src = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            src[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = inputs[0];
            int v = inputs[1];
            int w = inputs[2];
            src[u].add(new Vertex(v, w));
        }
        // 1에서 다른 모든 노드의 최단 경로 중 K번째 경로를 구해야 한다.
        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1,o2)->{
            return o1.weight - o2.weight;
        });
        // 시작 노드에서 i노드까지 가는 K개의 최단경로를 내림차순으로 저장
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N+1];
        for(int i=1; i<=N; i++) distQueue[i] = new PriorityQueue<>((o1,o2)->{
            return o2 - o1;
        });
        pq.add(new Vertex(1, 0)); // 원래 다익스트라와 같다 -> 시작 노드까지의 최단 경로는 0
        distQueue[1].add(0);
        while(!pq.isEmpty()){
            Vertex current = pq.poll();
            int curVertex = current.vertex;
            int curWeight = current.weight;
            for(Vertex next : src[curVertex]){
                if(distQueue[next.vertex].size() < K){
                    distQueue[next.vertex].add(curWeight+next.weight);
                    pq.add(new Vertex(next.vertex, curWeight+next.weight));
                }else if(distQueue[next.vertex].peek() > curWeight+next.weight){
                    distQueue[next.vertex].poll();
                    distQueue[next.vertex].add(curWeight+next.weight);
                    pq.add(new Vertex(next.vertex, curWeight+next.weight));
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(distQueue[i].size() < K) System.out.println(-1);
            else System.out.println(distQueue[i].peek());
        }
        br.close();
    }
}

class Vertex{
    int vertex;
    int weight;
    public Vertex(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}
