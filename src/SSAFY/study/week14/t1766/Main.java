package SSAFY.study.week14.t1766;

import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[] inDegree;
    static List<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list=new List[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }
        inDegree = new int[N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            inDegree[v]++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for(int next : list[cur]){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        System.out.println(sb);
        br.close();
    }


}