package Q6Graph.T56;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    static int N, M;
    static List<Node>[] src;
    static boolean[] visited;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        src = new ArrayList[N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            src[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        StringTokenizer st;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            src[u].add(new Node(v,w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) ->{ // increasing
            return o1.weight - o2.weight;
        }); 
        dist[start] = 0;
        pq.add(new Node(start, dist[start])); // 노드 번호, 해당 노드까지 최단 경로
        
        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(!visited[current.vertex]){
                visited[current.vertex] = true;
                for(Node next : src[current.vertex]){
                    dist[next.vertex] = Math.min(dist[next.vertex], dist[current.vertex]+next.weight);
                    if(dist[next.vertex]==dist[current.vertex]+next.weight){
                        pq.add(new Node(next.vertex, dist[next.vertex]));
                    }
                }
            }
        }
        System.out.println(dist[end]);
        br.close();
    }
}

