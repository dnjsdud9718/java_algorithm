package Q3.T22;

/**
 * 11754 연결요소의 개수 구하기
 * DFS
 */
import java.io.*;
import java.util.*;
class Main{
    private static LinkedList<Integer>[] list;
    private static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        list = new LinkedList[N+1];

        for(int i=1; i<=N; i++) {
            list[i] = new LinkedList<>();
        }

        visited = new int[N+1];
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        int result =0;
        for(int i=1; i<=N; i++){
            if(visited[i] != 1){
                dfs(i);
                result++;
            }
        }

        System.out.println(result);
        br.close();
    }   
    public static void dfs(int i) {
        visited[i] = 1;
        for(Object x : list[i].toArray()) {
            if(visited[(int)x] == 0){
                visited[(int)x] = 1;
                dfs((int)x);
            } 
        }   
    }
}