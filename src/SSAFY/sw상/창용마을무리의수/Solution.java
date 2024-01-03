package SSAFY.sw상.창용마을무리의수;

import java.io.*;
import java.util.*;

public class Solution {
    public static LinkedList<Integer>[] list;
    public static boolean[] visited;
    public static void dfs(int u){
        visited[u]=true;
        for(int v : list[u]){
            if(!visited[v]){
                dfs(v);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int ts=1; ts<=T; ts++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            list = new LinkedList[N+1];
            visited = new boolean[N+1];
            for(int i=1; i<=N; i++) list[i] = new LinkedList<>();
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                list[S].add(E);
                list[E].add(S);
            }

            int answer = 0;
            for(int i=1; i<=N; i++){
                if(!visited[i]){
                    answer++;
                    dfs(i);
                }
            }
            System.out.printf("#%d %d\n", ts, answer);
        }
        br.close();
    }
    
}
