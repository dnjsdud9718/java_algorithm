package SSAFY.study.week13.t1647;

import java.io.*;
import java.util.*;
//mst
public class Main
{

    static int N, M, sum;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> {
        return o1.w - o2.w;
    });
    static int[] parents;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for(int i=1; i<=N; i++) parents[i] = i;
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u,v,w));
        }
        sum = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            if(cnt == N-2) break;
            Edge e = pq.poll();
            if(union(e.u, e.v)){
                sum += e.w;
                cnt++;
            }
        }
        System.out.println(sum);
        br.close();
    }
    static int find(int x){
        if(parents[x] == x) return x;
        return (parents[x] = find(parents[x]));
    }
    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        if(x > y){
            parents[x] = y;
        }else{
            parents[y] = x;
        }
        return true;
    }
    static class Edge{
        int u,v,w;
        public Edge(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}

