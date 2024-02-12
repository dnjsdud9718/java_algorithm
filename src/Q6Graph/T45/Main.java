package Q6Graph.T45;

import java.io.*;
import java.util.*;

// 백준 18352 특정 거리의 도시 찾기 실버 2
// BFS, 오름차순 정렬
public class Main {
    public static BufferedWriter wr;
    public static LinkedList<Integer>[] list;
    public static int[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // # of city
        int M = Integer.parseInt(st.nextToken()); // # of road
        int K = Integer.parseInt(st.nextToken()); // 거리 정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시
        list = new LinkedList[N+1];
        visited = new int[N+1];
        for(int i=1; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
        }

        bfs(X, K);
        
        br.close();
        wr.flush();
        wr.close();
    }
    public static void bfs(int x, int k) throws Exception{
        visited[x] = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        queue.add(x);
        while(!queue.isEmpty()){
            int i = queue.poll();
            for(int t : list[i]){
                if(t != x && visited[t] == 0){
                    visited[t] = visited[i]+1;
                    if(visited[t] == k) {pq.add(t); continue;}
                    queue.add(t);
                }
            }
        }
        if(pq.size() == 0) wr.write("-1");
        else{
            while(!pq.isEmpty()){
                int answer = pq.poll();
                wr.write(""+answer+"\n");
            }
        }
    }
    
}
