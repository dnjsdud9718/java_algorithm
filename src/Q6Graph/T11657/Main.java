package Q6Graph.T11657;

/*
 백준 11657 타임머신 벨만 포드 알고리즘

 N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있따. 각 버스는 A, B, C로 나타낼 수
 있는데, A는 시작도시, B는 도착도시,  C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우도 있다.
 C=0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우다.
 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

 Input
 첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 
 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 
 Output
 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 
 첫째 줄에 -1을 출력한다. 
 그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을
 순서대로 출력한다. 만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.

 */
import java.io.*;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int V = inputs[0];
        int E = inputs[1];
        long[] dist = new long[V+1];
        for(int i=0; i<=V; i++) dist[i] = Integer.MAX_VALUE;
        Edge[] edges = new Edge[E];
        for(int i=0; i<E; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = inputs[0];
            int v = inputs[1];
            int w = inputs[2];
            edges[i] = new Edge(u, v, w);

        }
        dist[1] = 0;
        boolean cycle = false;
        for(int i=1; i<=V; i++){
            for(int j=0; j<E; j++){
                Edge e = edges[j];
                if(dist[e.u]!=Integer.MAX_VALUE && dist[e.u]+e.w < dist[e.v]){
                    dist[e.v] = dist[e.u]+e.w;
                    if(i == V){ // 음수 사이클 발생
                        cycle = true;
                        break;
                    }
                }
            }
            if(cycle) break;
        }
        if(cycle) bw.write("-1");
        else{
            for(int i=2; i<=V; i++) {
                if(dist[i] == Integer.MAX_VALUE) bw.write(-1+"\n");
                else bw.write(dist[i]+"\n");
            }
        }
        bw.flush();
        bw.close();
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
