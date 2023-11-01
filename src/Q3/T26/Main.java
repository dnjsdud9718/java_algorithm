package Q3.T26;

/**
 * 1260
 */
import java.util.*;
import java.io.*;
public class Main {
    private static LinkedList<Integer>[] list;
    private static int visited[];
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        list = new LinkedList[N+1];
        visited = new int[N+1];
        for(int i=1; i<=N; i++){
            list[i] = new LinkedList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        for(int i=1; i<=N; i++) Collections.sort(list[i]);
        dfs(S);
        bw.write("\n");
        bw.flush();
        visited = new int[N+1];
        bfs(S);

        bw.flush();
        bw.close();
        br.close();
    }
    public static void dfs(int u) throws IOException{
        visited[u] = 1;
        bw.write(u + " ");
        for(Object x : list[u].toArray()){
            if(visited[(int)x] == 0) dfs((int)x);
        }
    }
    public static void bfs(int u) throws IOException{
        visited[u] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        bw.write(u+" ");
        while(!queue.isEmpty()){
            int w = queue.poll();
            for(Object x : list[w].toArray()){
                if(visited[(int)x] == 0){
                    bw.write((int)x+ " ");
                    visited[(int)x] = 1;
                    queue.add((int)x);
                }
            }
        }
    }
}
