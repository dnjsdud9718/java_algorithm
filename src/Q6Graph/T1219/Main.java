package Q6Graph.T1219;

import java.io.*;
import java.util.stream.Stream;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0]; // # of city
        int S = inputs[1]; // start
        int E = inputs[2]; // end
        int M = inputs[3]; // # of road
        Edge[] edges = new Edge[M];
        for(int i=0; i<M; i++) {
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = inputs[0];
            int v = inputs[1];
            int w = inputs[2];
            edges[i] = new Edge(u, v, w);
        }
        int[] revenue = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); //수익
        long[] cost = new long[N];
        for(int i=0; i<N; i++){
            cost[i] = Integer.MIN_VALUE; // 최댓값을 구하고 싶다.
        }
        cost[S] = revenue[S];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<N-1; i++){
            for(Edge e : edges){
                if(cost[e.u] != Integer.MIN_VALUE && cost[e.u]+revenue[e.v]-e.w > cost[e.v]){
                    cost[e.v] = cost[e.u]+revenue[e.v]-e.w;
                }
            }
            // for(int j=0; j<N; j++) System.out.printf("%d ", cost[j]);
            // System.out.println();
        }
        boolean[] visited = new boolean[N];
        for(Edge e : edges){
            if(cost[e.u] != Integer.MIN_VALUE && cost[e.u]+revenue[e.v]-e.w > cost[e.v]){
                visited[e.u] = true; // 큐에 삽입할 때 방문 처리를 해야 한다...BFS의 기본!!!!
                queue.add(e.u); // e.v를 넣으면 왜 안 되는지 모르겠다. u와 v모두 양수 사이클에 포함되는데...(해결!!!)
                
            }
        }
        if(cost[E]==Integer.MIN_VALUE) System.out.println("gg"); // 목적지로 도달 불가.
        else if(!queue.isEmpty()){
            //BFS를 통해 사이클에 포함되는 노드에서 E까지 도달가능한지 판단.
            // boolean[] visited = new boolean[N]; 큐에 이미 여러 개의 노드가 들어와 있을 수 있기 때문에 이렇게 하면 안 된다!!!!
            // visited[queue.peek()] = true; 
            while(!queue.isEmpty()){
                int c = queue.poll();
                if(c == E) {
                    break;
                }
                for(Edge e : edges){
                    if(e.u == c && !visited[e.v]){
                        visited[e.v] = true;
                        queue.add(e.v);
                    }
                }
            }
            if(!visited[E]) System.out.println(cost[E]);
            else System.out.println("Gee");
        }else{
            System.out.println(cost[E]);
        }
        br.close();
    }
}
class Edge{
    int u;
    int v;
    int w;
    public Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

