package Q6.T47;
//1707 이분 그래프 판별하기 골드
//그래프 이론에서 이분 그래프(bipartite graph)란 모든 꼭짓점을 빨강과 파랑으로 색칠하되,
//모든 변이 빨강과 파랑 꼭짓점을 포함하도록 색칠할 수 있는 그래프이다.

import java.io.*;
import java.util.*;
public class Main {
    public static int[] color;
    public static boolean[] visited;
    public static LinkedList<Integer>[] list;
    public static boolean flag;

    public static void dfs(int x){
        visited[x] = true;
        for(int c : list[x]){
            if(!visited[c]){
                color[c] = (color[x]+1)%2;
                dfs(c);
            }
            else if(color[x] == color[c]){
                flag = false;
                break;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int ts = 0; ts<T; ts++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            list = new LinkedList[V+1];
            for(int i=0; i<=V; i++) list[i] = new LinkedList<>();
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(e);
                list[e].add(s);
            }
            
            color = new int[V+1];
            flag = true;
            visited = new boolean[V+1];
            for(int i=1; i<=V; i++){ // connected component고려
                if(!visited[i]) dfs(i);
                if(!flag) break;
            }
            if(!flag) bw.write("NO\n");
            else bw.write("YES\n");


        }
        br.close();
        bw.flush();
        bw.close();
    }
}
